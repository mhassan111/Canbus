package com.screening.app.utilities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.location.LocationManager
import android.net.wifi.WifiManager
import android.os.BatteryManager
import android.os.Build
import android.telephony.TelephonyManager
import android.text.format.Formatter
//import com.screening.app.BuildConfig

object DeviceInformationUtil {

    private const val TAG = "DeviceInfo"
    const val PHONE_SERVICE_DEVICE = "android"

    @JvmStatic
    val deviceModel: String
        get() {
            val model = Build.MANUFACTURER + "-" + Build.MODEL
            return model.replace(" ".toRegex(), "-")
        }

    @JvmStatic
    val deviceOS: String
        get() = Build.VERSION.RELEASE

    @SuppressLint("MissingPermission", "HardwareIds")
    @JvmStatic
    fun getSimId(context: Context): String {
        return try {
            val telephonyManager =
                context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            return telephonyManager.simSerialNumber ?: ""
        } catch (e: SecurityException) {
            logException("${AppConstants.DEVICE_INFO_TYPE} getSimId Error = ${e.message}", throwable = e)
            ""
        }
    }

    @SuppressLint("MissingPermission", "HardwareIds")
    @SuppressWarnings("deprecation")
    @JvmStatic
    fun getIMEINumber(context: Context): String {
        return try {
            val telephonyManager =
                context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager?
            return telephonyManager?.deviceId ?: ""
        } catch (e: SecurityException) {
            logException(
                "${AppConstants.DEVICE_INFO_TYPE} getIMEINumber Error = ${e.message}",
                throwable = e
            )
            ""
        }
    }

    @JvmStatic
    val isDeviceRooted: Boolean
        get() {
            var isRooted = false
            try {
                Runtime.getRuntime().exec(arrayOf("su"))
                isRooted = true
            } catch (e: Exception) {
                logException(
                    "${AppConstants.DEVICE_INFO_TYPE} RootPermission Error = ${e.message}",
                    throwable = e
                )
            }
            return isRooted
        }

    @JvmStatic
    @SuppressLint("MissingPermission", "HardwareIds")
    fun getPhoneNumber(context: Context): String {
        val telephonyManager =
            context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        return try {
            telephonyManager.line1Number
        } catch (e: Exception) {
            logException(
                "${AppConstants.DEVICE_INFO_TYPE} getPhoneNumber Error = ${e.message}",
                throwable = e
            )
            ""
        }
    }

    @JvmStatic
    fun getBatteryLevel(context: Context): String {
        val batteryIntent = context.applicationContext
            .registerReceiver(
                null,
                IntentFilter(Intent.ACTION_BATTERY_CHANGED)
            )
        val rawLevel = batteryIntent?.getIntExtra(
            BatteryManager.EXTRA_LEVEL, -1
        ) ?: 0
        val scale = batteryIntent?.getIntExtra(
            BatteryManager.EXTRA_SCALE, -1
        )?.toDouble()
            ?: 0.toDouble()
        var batteryLevel = -1.0
        if (rawLevel >= 0 && scale > 0) {
            batteryLevel = (rawLevel / scale * 100)
        }
        return "$batteryLevel%"
    }

    @JvmStatic
    @SuppressWarnings("deprecation")
    fun getIPAddress(context: Context): String {
        return try {
            val wifiManager: WifiManager =
                context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
            Formatter.formatIpAddress(wifiManager.connectionInfo.ipAddress)
        } catch (e: Exception) {
            logException("$TAG isAddress Error:- " + e.message)
            ""
        }
    }

    @JvmStatic
    fun isWifiEnabled(context: Context): Boolean {
        val wifiManager: WifiManager =
            context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        return wifiManager.isWifiEnabled
    }

    @JvmStatic
    fun isGpsEnabled(context: Context): Boolean {
        val mLocationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }

    @JvmStatic
    fun getNetworkOperator(context: Context): String {
        val mTelephonyManager =
            context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        return mTelephonyManager.networkOperatorName
    }

    fun getMccMnc(context: Context): String {
        val tel = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        try {
            val networkOperator = tel.networkOperator
            return if (networkOperator != null) {
                val mcc = networkOperator.substring(0, 3).toInt()
                val mnc = networkOperator.substring(3).toInt()
                "$mcc,$mnc"
            } else {
                return ""
            }
        } catch (exception: Exception) {
            logException(
                "${AppConstants.DEVICE_INFO_TYPE} getSimId Error = ${exception.message}",
                throwable = exception
            )
        }
        return ""
    }

    @JvmStatic
    val versionName: String = "1.7.2"
    val versionCode: String = "1"
//        get() = BuildConfig.VERSION_CODE.toString()

    fun isGooglePlayServicesAvailable(context: Context): String {
//        val googleApiAvailability = GoogleApiAvailability.getInstance()
//        val resultCode = googleApiAvailability.isGooglePlayServicesAvailable(context)
//        if (resultCode != ConnectionResult.SUCCESS) {
//            return "0"
//        }
//        return "1"
        return "0"
    }
}