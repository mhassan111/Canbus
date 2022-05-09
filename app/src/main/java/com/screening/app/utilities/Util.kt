package com.screening.app.utilities

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
import com.screening.app.MyApplication.Companion.appContext
import com.screening.app.receivers.ScreenAppAdminReceiver
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
    fun checkDeviceAdminPermissionGranted(mContext: Context): Boolean {
        val mDPM = mContext.getSystemService(Context.DEVICE_POLICY_SERVICE) as DevicePolicyManager
        val mAdminName = ComponentName(mContext, ScreenAppAdminReceiver::class.java)
        return mDPM.isAdminActive(mAdminName)
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

    fun hangUpPhoneCall(context: Context) {
        if (AppConstants.osGreaterThanEqualToPie) {
            cutTheCall(context)
        } else {
            disconnectCall()
        }
        writeLogs(
            context,
            "${AppConstants.PHONE_CALL_TYPE} call hanged up"
        )
    }

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

    private fun disconnectCall() {
        try {
            val serviceManagerName = "android.os.ServiceManager"
            val serviceManagerNativeName = "android.os.ServiceManagerNative"
            val telephonyName = "com.android.internal.telephony.ITelephony"
            val telephonyStubClass: Class<*>
            val telephonyEndCall: Method
            val telephonyObject: Any
            val serviceManagerObject: Any
            val telephonyClass: Class<*> = Class.forName(telephonyName)
            telephonyStubClass = telephonyClass.classes[0]
            val serviceManagerClass: Class<*> = Class.forName(serviceManagerName)
            val serviceManagerNativeClass: Class<*> = Class.forName(serviceManagerNativeName)
            val getService =  // getDefaults[29];
                serviceManagerClass.getMethod("getService", String::class.java)
            val tempInterfaceMethod = serviceManagerNativeClass.getMethod(
                "asInterface",
                IBinder::class.java
            )
            val tmpBinder = Binder()
            tmpBinder.attachInterface(null, "fake")
            serviceManagerObject = tempInterfaceMethod.invoke(null, tmpBinder)
            val retbinder = getService.invoke(serviceManagerObject, "phone") as IBinder
            val serviceMethod = telephonyStubClass.getMethod(
                "asInterface",
                IBinder::class.java
            )
            telephonyObject = serviceMethod.invoke(null, retbinder)
            telephonyEndCall = telephonyClass.getMethod("endCall")
            telephonyEndCall.invoke(telephonyObject)
        } catch (e: Exception) {
            e.printStackTrace()
        }
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

    fun phoneNumbersAreEqual(phoneNumber1: String, phoneNumber2: String): Boolean {
        return PhoneNumberUtils.compare(phoneNumber1, phoneNumber2)
//        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
//            PhoneNumberUtils.areSamePhoneNumber(phoneNumber1, phoneNumber2, "")
//        } else {
//            PhoneNumberUtils.compare(phoneNumber1, phoneNumber2)
//        }
    }

    @JvmStatic
    @Synchronized
    @SuppressWarnings("deprecation")
    fun retrieveFilePath(context: Context, directory: String, fileName: String): String {
        // Create a folder in Shared Media Directory if OS >= 10
        // Else Creates a folder in External Storage
        val storageDirectory = if (AppConstants.osGreaterThanEqualToEleven) {
            ""
        } else {
            "AppConstants.STORAGE_FOLDER"
        }

        // create Storage folder If not Exists
        val storageFolder =
            File(Environment.getExternalStorageDirectory().toString() + storageDirectory)
        if (!storageFolder.exists()) {
            logVerbose("creating folder ${storageFolder.absolutePath}")
            val created = storageFolder.mkdirs()
            if (created) {
                logVerbose("folder created ${storageFolder.absolutePath}")
            }
        }

        val folder = File(
            Environment.getExternalStorageDirectory()
                .toString() + storageDirectory + File.separator + directory
        )
        if (!folder.exists()) {
            logVerbose("creating folder ${folder.absolutePath}")
            val created = folder.mkdirs()
            if (created) {
                logVerbose("folder created ${folder.absolutePath}")
            }
        }
        return String.format("%s%s%s", folder.absolutePath, File.separator, fileName)
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