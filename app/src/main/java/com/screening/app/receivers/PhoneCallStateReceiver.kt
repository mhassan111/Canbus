package com.screening.app.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.telephony.PhoneStateListener
import android.telephony.TelephonyManager
import com.google.i18n.phonenumbers.PhoneNumberUtil
import com.screening.app.di.module.ApplicationScope
import com.screening.app.featureCallScreening.domain.useCase.PhoneNumberUseCases
import com.screening.app.utilities.AppConstants
import com.screening.app.utilities.Util
import com.screening.app.utilities.logException
import com.screening.app.utilities.logVerbose
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.inject.Inject

@AndroidEntryPoint
class PhoneCallStateReceiver : BroadcastReceiver() {

    @Inject
    lateinit var phoneNumberUseCases: PhoneNumberUseCases

    @Inject
    @ApplicationScope
    lateinit var coroutineScope: CoroutineScope
    private var job: Job? = null

    companion object {
        val numbers = listOf("")
        var mCallNumber: String = ""
        var mIsIncoming = false
        private var mIsOffHookStateMarked = false
        private var mIsRingingStateMarked = false
        private var mIsIdleStateMarked = false
        private var mIsOutgoingCallStateMarked = false
    }

    override fun onReceive(context: Context, intent: Intent) {
        logVerbose("${AppConstants.PHONE_CALL_TYPE} Call Recorder Receiver Called")
        Util.writeLogs(context, "${AppConstants.PHONE_CALL_TYPE} Call Recorder Receiver Called")

        if (intent.action == "android.intent.action.PHONE_STATE") {
            val callState = intent.getStringExtra(TelephonyManager.EXTRA_STATE)
            logVerbose("${AppConstants.PHONE_CALL_TYPE} Intent Action Call State = $callState")
            Util.writeLogs(
                context,
                "${AppConstants.PHONE_CALL_TYPE} Intent Action Call State = $callState"
            )

            val number = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER)
            number?.let {
                logVerbose("${AppConstants.PHONE_CALL_TYPE} Extra Incoming Call Number: $it")
                Util.writeLogs(
                    context,
                    "${AppConstants.PHONE_CALL_TYPE}  Extra Incoming Call Number: $it"
                )
                if (it.isNotBlank())
                    mCallNumber = it
            }

            val mExtraPhoneNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER)
            logVerbose("${AppConstants.PHONE_CALL_TYPE} Extra Phone Number : $mExtraPhoneNumber")
            Util.writeLogs(
                context,
                "${AppConstants.PHONE_CALL_TYPE} Extra Phone Number: $mExtraPhoneNumber"
            )
            mExtraPhoneNumber?.let {
                if (it.isNotBlank())
                    mCallNumber = it
            }

            try {
                val telephony =
                    context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager?
                telephony?.listen(object : PhoneStateListener() {
                    override fun onCallStateChanged(state: Int, incomingNumber: String?) {
                        super.onCallStateChanged(state, incomingNumber)

                        incomingNumber?.let {
                            logVerbose("${AppConstants.PHONE_CALL_TYPE} Telephony State Incoming Call Number: $it")
                            Util.writeLogs(
                                context,
                                "${AppConstants.PHONE_CALL_TYPE} Telephony State Incoming Call Number: $it"
                            )
                            if (it.isNotBlank())
                                mCallNumber = it

                            if (mCallNumber.isNotBlank() && state == TelephonyManager.CALL_STATE_RINGING
                                && !mIsRingingStateMarked
                            ) {
                                runCallScreening(context, mCallNumber)
                            } else if (mCallNumber.isNotBlank() && state == TelephonyManager.CALL_STATE_OFFHOOK
                                && !mIsOffHookStateMarked
                            ) {
                                runCallScreening(context, mCallNumber)
                            }

                            if (state == TelephonyManager.CALL_STATE_IDLE) {
                                mIsOffHookStateMarked = false
                                mIsIncoming = false
                                mIsRingingStateMarked = false
                                mIsOutgoingCallStateMarked = false
                                mIsIdleStateMarked = false
                            }
                        }
                    }
                }, PhoneStateListener.LISTEN_CALL_STATE)
            } catch (e: Exception) {
                Util.writeLogs(
                    context,
                    "${AppConstants.PHONE_CALL_TYPE} Error retrieving incoming call number: ${e.message}"
                )
                logException("${AppConstants.PHONE_CALL_TYPE} Error retrieving Telephony incoming call number: ${e.message}")
            }

            if (TelephonyManager.EXTRA_STATE_OFFHOOK == callState) {
                if (mCallNumber.isNotBlank() && !mIsOffHookStateMarked) {
                    try {
                        mIsOffHookStateMarked = true
                        mIsIdleStateMarked = false
                        if (!mIsIncoming) {

                        }
                    } catch (e: Exception) {
                        logException("${AppConstants.PHONE_CALL_TYPE} exception state OffHook = ${e.message}")
                        Util.writeLogs(
                            context,
                            "${AppConstants.PHONE_CALL_TYPE} exception state OffHook = ${e.message}"
                        )
                    }
                }
                runCallScreening(context, mCallNumber)
            }

            if (TelephonyManager.EXTRA_STATE_IDLE == callState) {
                mIsOffHookStateMarked = false
                mIsIncoming = false
                mIsRingingStateMarked = false
                mIsOutgoingCallStateMarked = false
                logVerbose("${AppConstants.PHONE_CALL_TYPE} Call State Idle")
                Util.writeLogs(
                    context,
                    "${AppConstants.PHONE_CALL_TYPE} Call State Idle"
                )
                if (!mIsIdleStateMarked) {
                    mIsIdleStateMarked = true
                }
            }

            if (TelephonyManager.EXTRA_STATE_RINGING == callState) {
                logVerbose("${AppConstants.PHONE_CALL_TYPE} Call State Ringing")
                Util.writeLogs(
                    context,
                    "${AppConstants.PHONE_CALL_TYPE} Call State Ringing"
                )
                mIsIncoming = true
                mIsOffHookStateMarked = false
                mIsIdleStateMarked = false
                mIsOutgoingCallStateMarked = false

                if (mCallNumber.isNotBlank() && !mIsRingingStateMarked) {
                    mIsRingingStateMarked = true
                    runCallScreening(context, mCallNumber)
                }
            }
        } else if (intent.action.equals(Intent.ACTION_NEW_OUTGOING_CALL)) {
            val number = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER) ?: ""
            Util.writeLogs(
                context,
                "${AppConstants.PHONE_CALL_TYPE}  Extra Outgoing Call Number: $number"
            )
            logVerbose("${AppConstants.PHONE_CALL_TYPE}  Extra Outgoing Call Number: $number")
            if (number.isNotBlank() && !mIsOutgoingCallStateMarked) {
                mIsOutgoingCallStateMarked = true
                runCallScreening(context, number)
            }
            mIsRingingStateMarked = false
        }
    }

    private fun runCallScreening(context: Context, phoneNumber: String) {
        if (phoneNumber.isBlank()) {
            logVerbose("${AppConstants.PHONE_CALL_TYPE} phone number is empty")
            Util.writeLogs(
                context,
                "${AppConstants.PHONE_CALL_TYPE} phone number is empty"
            )
            return
        }

        if (job == null || !job!!.isActive) {
            job = coroutineScope.launch {
                phoneNumberUseCases.getPhoneNumbersUseCase()
                    .collectLatest { phoneNumbers ->
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
                                Handler(Looper.getMainLooper()).postDelayed({
                                    Util.deleteLastCallLog(context)
                                }, 1500L)
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
                        }
                    }
            }
        }
    }
}