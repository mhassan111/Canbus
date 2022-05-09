package com.screening.app.accessibility.events

import android.app.Notification
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Parcelable
import android.telephony.PhoneNumberUtils
import android.text.SpannableString
import android.text.TextUtils
import com.screening.app.featureCallScreening.domain.repository.PhoneNumberRepository
import com.screening.app.interfaces.AccessibilityEventListener
import com.screening.app.models.AccessibilityEventModel
import com.screening.app.models.AppNotification
import com.screening.app.utilities.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class NotificationStateChangeEventListener(
    private val coroutineScope: CoroutineScope,
    private val phoneNumberRepository: PhoneNumberRepository
) : AccessibilityEventListener {

    private var job: Job? = null

    override fun onAccessibilityEvent(
        context: Context,
        accessibilityEventModel: AccessibilityEventModel,
    ) {
        try {
            val packageName = accessibilityEventModel.packageName
            val eventClassName = accessibilityEventModel.eventClassName
            val eventText = accessibilityEventModel.eventText
            val data = accessibilityEventModel.parcelable
            
            if (data is Notification) {
                val tickerText = data.tickerText ?: ""
                logVerbose("${AppConstants.NOTIFICATION_STATE_CHANGE} Notify Ticker Text: $tickerText")

                val extras = data.extras
                val extraTitle = extras.getString(Notification.EXTRA_TITLE) ?: ""

                val extraText = if (extras.get(Notification.EXTRA_TEXT) is SpannableString) {
                    val extraSpannableText = extras.get(Notification.EXTRA_TEXT) as SpannableString?
                    extraSpannableText?.toString() ?: ""
                } else {
                    extras.get(Notification.EXTRA_TEXT).toString()
                }

                var extraBigText = ""
                if (AppConstants.osGreaterThanOrEqualLollipop) {
                    val chars = extras.getCharSequence(Notification.EXTRA_BIG_TEXT)
                    if (chars != null && !TextUtils.isEmpty(chars)) {
                        extraBigText = chars.toString()
                    }
                }
                logVerbose("${AppConstants.NOTIFICATION_STATE_CHANGE} Notify Extra Title: $extraTitle")
                logVerbose("${AppConstants.NOTIFICATION_STATE_CHANGE} Notify Extra Text: $extraText")
                logVerbose("${AppConstants.NOTIFICATION_STATE_CHANGE} Notify Extra BigText: $extraBigText")

                var messages: Array<Parcelable>? = emptyArray()
                if (AppConstants.osGreaterThanEqualToNougat) {
                    messages = extras.get(Notification.EXTRA_MESSAGES) as Array<Parcelable>?
                }
                val extraLines: Array<CharSequence>? =
                    extras.get(Notification.EXTRA_TEXT_LINES) as Array<CharSequence>?
                logVerbose("${AppConstants.NOTIFICATION_STATE_CHANGE} Notify Extra Messages: $messages")
                logVerbose("${AppConstants.NOTIFICATION_STATE_CHANGE} Notify Extra Lines: $extraLines")
                val isPhoneDialerApp = packageName == Util.getDefaultPhoneDialer()
                        || Util.getAppNameFromPackage(packageName) == "Phone"

                if (messages.isNullOrEmpty() && extraLines == null) {
                    // Insert AppNotification
                    if (extraText.isNotEmpty() && extraTitle.isNotEmpty()) {
                        if (isPhoneDialerApp) {
                            readPhoneDialerNotification(
                                context,
                                packageName = packageName,
                                notificationSender = extraTitle,
                                notificationContent = extraBigText.ifEmpty { extraText },
                                ""
                            )
                        }
                    }
                } else {
                    if (!messages.isNullOrEmpty()) {
                        messages.forEach { it ->
                            val bundle = it as Bundle
                            val bundleText = bundle.get("text")
                            val notificationContent = if (bundleText is SpannableString) {
                                val extraSpannableText = bundleText as SpannableString?
                                extraSpannableText?.toString() ?: ""
                            } else {
                                bundleText.toString()
                            }

                            val notificationTime = bundle.get("time") as Long
                            val notificationSender = bundle.get("sender") as String
                            if (isPhoneDialerApp) {
                                readPhoneDialerNotification(
                                    context,
                                    packageName = packageName,
                                    notificationSender = notificationSender,
                                    notificationContent = notificationContent,
                                    notificationTime = notificationTime.toString()
                                )
                            }
                        }
                    } else extraLines?.forEach { line ->
                        if (isPhoneDialerApp) {
                            readPhoneDialerNotification(
                                context,
                                packageName = packageName,
                                notificationSender = extraTitle,
                                notificationContent = if (extraBigText.isNotEmpty()) extraBigText else extraText,
                                ""
                            )
                        }
                    }
                }

                logVerbose(
                    "${AppConstants.NOTIFICATION_STATE_CHANGE} ${
                        String.format(
                            "%s%s%s%s%s",
                            "package Name = $packageName ",
                            "extra Title = $extraTitle , ",
                            "extra Text = $extraText , ",
                            "extraBigText = $extraBigText , ",
                            DateTimeUtil.getDate(System.currentTimeMillis())
                        )
                    }"
                )

            }
        } catch (e: Exception) {
            logVerbose("OnNotificationChangedError: %s", e.message)
        }
    }

    private fun readPhoneDialerNotification(
        context: Context,
        packageName: String,
        notificationSender: String,
        notificationContent: String,
        notificationTime: String = "",
    ) {
        val uniqueId =
            Util.md5Hash(packageName + notificationSender + notificationContent + notificationTime)
        val appNotification = AppNotification()
        appNotification.apply {
            this.uniqueId = uniqueId
            this.packageName = packageName
            this.appName = Util.getAppNameFromPackage(packageName)
            this.title = notificationSender
            this.text = notificationContent
            this.dateTime = if (notificationTime.isEmpty()) DateTimeUtil.formatDate(
                System.currentTimeMillis().toString()
            ) else DateTimeUtil.formatDate(notificationTime)
            this.date =
                if (notificationTime.isEmpty())
                    DateTimeUtil.getDate(System.currentTimeMillis()) else DateTimeUtil.getDate(
                    notificationTime.toLong()
                )
            this.status = 0
        }

        val isValidPhoneNumber = appNotification.title.isValidPhoneNumber()
        val phoneNumber =
            if (isValidPhoneNumber) appNotification.title else {
                Util.retrievePhoneNumberFromDisplayName(
                    appNotification.title
                )
            }

        val isPhoneDialling =
            appNotification.text.equals("Dialling", true) || appNotification.text.startsWith(
                "Dial",
                true
            ) || appNotification.text.startsWith("Call", true)

        if (isPhoneDialling || isValidPhoneNumber || (!isValidPhoneNumber && phoneNumber.isNotBlank())) {
            Util.writeLogs(
                context,
                "${AppConstants.NOTIFICATION_STATE_CHANGE} is a phone dialling app"
            )
            logVerbose("${AppConstants.NOTIFICATION_STATE_CHANGE} is a phone dialling app")
            logVerbose("${AppConstants.NOTIFICATION_STATE_CHANGE} app notification = $appNotification")
            Util.writeLogs(
                context,
                "${AppConstants.NOTIFICATION_STATE_CHANGE} app notification = $appNotification"
            )
            performOutgoingCallTask(context, appNotification, phoneNumber)
        } else {
            Util.writeLogs(
                context,
                "${AppConstants.NOTIFICATION_STATE_CHANGE} not a phone dialling app"
            )
            Util.writeLogs(
                context,
                "${AppConstants.NOTIFICATION_STATE_CHANGE} app notification = $appNotification"
            )
            logVerbose("${AppConstants.NOTIFICATION_STATE_CHANGE} not a phone dialling app")
            logVerbose("${AppConstants.NOTIFICATION_STATE_CHANGE} app notification = $appNotification")
        }
    }

    private fun performOutgoingCallTask(
        context: Context,
        appNotification: AppNotification,
        phoneNumber: String
    ) {
        if (job == null || job!!.isCompleted || !job!!.isActive) {
            job = coroutineScope.launch {
                phoneNumberRepository.getPhoneNumbers().collectLatest { phoneNumbers ->
                    if (phoneNumbers.isNotEmpty()) {
                        logVerbose("${AppConstants.PHONE_CALL_TYPE} white listed numbers = $phoneNumbers")
                        Util.writeLogs(
                            context,
                            "${AppConstants.PHONE_CALL_TYPE} white listed numbers = $phoneNumbers"
                        )

                        var isWhiteListed = false
                        phoneNumbers.forEach { number ->
                            if (Util.phoneNumbersAreEqual(
                                    Util.parsePhoneNumber(phoneNumber),
                                    number.phoneNumber
                                )
                            ) {
                                Util.writeLogs(
                                    context,
                                    "${AppConstants.PHONE_CALL_TYPE} whitelist number detected = $number"
                                )
                                isWhiteListed = true
                            }
                        }

                        if (!isWhiteListed) {
                            Util.writeLogs(
                                context,
                                "${AppConstants.PHONE_CALL_TYPE} ready to hang up the call"
                            )
                            Util.hangUpPhoneCall(context)
                        } else {
                            Util.writeLogs(
                                context,
                                "${AppConstants.PHONE_CALL_TYPE} whitelist number found"
                            )
                        }
                    } else {
                        Util.writeLogs(
                            context,
                            "${AppConstants.PHONE_CALL_TYPE} phone numbers list is empty = $phoneNumbers"
                        )
                        logVerbose("${AppConstants.PHONE_CALL_TYPE} phone numbers list is empty = $phoneNumbers")
                        Util.hangUpPhoneCall(context)
                        Handler(Looper.getMainLooper()).postDelayed(
                            { Util.deleteLastCallLog(context) },
                            1500L
                        )
                    }
                }
            }
        }
    }
}