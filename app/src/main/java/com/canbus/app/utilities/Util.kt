package com.canbus.app.utilities

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.*
import android.provider.CallLog
import android.provider.ContactsContract
import android.provider.Settings
import android.telecom.TelecomManager
import android.telephony.PhoneNumberUtils
import android.text.TextUtils
import androidx.annotation.RequiresApi
import com.google.i18n.phonenumbers.PhoneNumberUtil
import com.canbus.app.MyApplication.Companion.appContext
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.lang.reflect.Method
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*

object Util {

    val osLessThanPie by lazy {
        Build.VERSION.SDK_INT < Build.VERSION_CODES.P
    }

    val osLessThanOreo by lazy {
        Build.VERSION.SDK_INT < Build.VERSION_CODES.O
    }

    fun isManagementOfAllFilesPermissionGranted(context: Context): Boolean =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            Environment.isExternalStorageManager()
        } else {
            false
        }

    private fun osVersionGreaterThanLollipop(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
    }

    @TargetApi(Build.VERSION_CODES.M)
    fun isAppIgnoringBatteryOptimization(mContext: Context): Boolean {
        return if (osVersionGreaterThanLollipop()) {
            val packageName = mContext.packageName
            val powerManager =
                mContext.getSystemService(Context.POWER_SERVICE) as PowerManager
            powerManager.isIgnoringBatteryOptimizations(packageName)
        } else {
            true
        }
    }


    @JvmStatic
    fun checkAccessibilityPermissionGranted(context: Context): Boolean {
        try {
            val mStringColonSplitter = TextUtils.SimpleStringSplitter(':')
            val settingValue = Settings.Secure.getString(
                context.contentResolver,
                Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES
            )
            if (settingValue != null && settingValue.isNotEmpty()) {
                mStringColonSplitter.setString(settingValue)
                while (mStringColonSplitter.hasNext()) {
                    val accessibilityService = mStringColonSplitter.next()
                    if (accessibilityService.contains(context.packageName)) return true
                }
            }
        } catch (e: Exception) {
            e.message
        }
        return false
    }

    fun displayOverOtherAppsGranted(context: Context) = Settings.canDrawOverlays(context)

    var screenRecordingIntent: Intent? = null

    @SuppressLint("MissingPermission")
    @RequiresApi(Build.VERSION_CODES.P)
    fun cutTheCall(context: Context): Boolean {
        val telecomManager = context.getSystemService(Context.TELECOM_SERVICE) as TelecomManager
        if (telecomManager.isInCall) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                val callDisconnected = telecomManager.endCall()
                if (callDisconnected) {
                    logVerbose("${AppConstants.PHONE_CALL_TYPE} Call disconnected successfully")
                } else {
                    logVerbose("${AppConstants.PHONE_CALL_TYPE} Call disconnected Failed")
                }
            }
        }
        return true
    }

    fun getDefaultPhoneDialer(): String {
        val dialerIntent = Intent(Intent.ACTION_DIAL).addCategory(Intent.CATEGORY_DEFAULT)
        val resolveInfo = appContext.packageManager.resolveActivity(
            dialerIntent, PackageManager.MATCH_DEFAULT_ONLY
        )
        return resolveInfo?.activityInfo?.packageName ?: ""
    }

    fun retrievePhoneNumberFromDisplayName(displayName: String): String {
        var numberMobile = ""
        try {
            val whereCondition = (ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " = ?")
            val whereParams = arrayOf(displayName)
            val managedCursor = appContext.contentResolver.query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI, arrayOf("data1"),
                whereCondition, whereParams, null, null
            )
            if (managedCursor != null) {
                if (managedCursor.moveToFirst()) numberMobile = managedCursor.getString(0)
                managedCursor.close()
            }
        } catch (e: Exception) {
            logException("Error retrievePhoneNumberFromDisplayName: ${e.message}")
        }
        return numberMobile
    }

    fun getAppNameFromPackage(packageName: String?): String {
        val packageManager = appContext.packageManager
        var applicationInfo: ApplicationInfo? = null
        try {
            applicationInfo = packageManager.getApplicationInfo(packageName!!, 0)
        } catch (exp: PackageManager.NameNotFoundException) {
            exp.message
        }
        return (if (applicationInfo != null) packageManager.getApplicationLabel(applicationInfo) else "Unknown") as String
    }

    fun parsePhoneNumber(phoneNumber: String): String {
        return try {
            if (phoneNumber.startsWith("+")) {
                val libPhone = PhoneNumberUtil.getInstance().parse(phoneNumber, "")
                return libPhone.nationalNumber.toString()
            } else {
                return phoneNumber
            }
        } catch (e: Exception) {
            e.printStackTrace()
            phoneNumber
        }
    }

    fun randomUUID(): String = UUID.randomUUID().toString()

    fun md5Hash(s: String): String {
        try {
            val digest = MessageDigest.getInstance("MD5")
            digest.update(s.toByteArray())
            val messageDigest = digest.digest()

            // Create Hex String
            val hexString = StringBuffer()
            for (i in messageDigest.indices) hexString.append(
                Integer.toHexString(
                    0xFF and messageDigest[i]
                        .toInt()
                )
            )
            return hexString.toString()
        } catch (e: NoSuchAlgorithmException) {
            e.message
        }
        return ""
    }

    fun writeLogs(context: Context, text: String) {
        val logFile = File(context.getExternalFilesDir(null), "log.txt")
        if (!logFile.exists()) {
            try {
                logFile.createNewFile()
            } catch (e: IOException) {
                logException("Exception while create file for logs ${e.message}", throwable = e)
            }
        }
        try {
            //BufferedWriter for performance, true to set append to file flag
            val buf = BufferedWriter(FileWriter(logFile, true))
            buf.append(text)
            buf.newLine()
            buf.close()
        } catch (e: IOException) {
            logException("Exception while appending logs ${e.message}", throwable = e)
        }
    }

    fun getIdOfLastCall(context: Context): Int {
        var id = -1
        return try {
            val cursor = context.contentResolver.query(
                CallLog.Calls.CONTENT_URI,
                null,
                null,
                null,
                CallLog.Calls.DATE + " DESC"
            )
            if (cursor != null) {
                if (cursor.moveToFirst()) id = cursor.getInt(cursor.getColumnIndex("_id"))
                cursor.close()
            }
            id
        } catch (e: Exception) {
            logException("Error Getting Last Call Id: ${e.message}")
            id
        }
    }

    fun deleteLastCallLog(context: Context) {
        try {
            val cursor = context.contentResolver.query(
                CallLog.Calls.CONTENT_URI, null, null, null,
                CallLog.Calls.DATE + " DESC"
            )
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    context.contentResolver.delete(
                        CallLog.Calls.CONTENT_URI,
                        "_ID=" + cursor.getInt(cursor.getColumnIndex("_ID")), null
                    )
                }
                cursor.close()
            }
        } catch (e: Exception) {
            e.message
        }
    }

}