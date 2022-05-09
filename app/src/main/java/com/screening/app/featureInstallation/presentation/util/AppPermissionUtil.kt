package com.screening.app.featureInstallation.presentation.util

import android.Manifest
import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Environment
import android.provider.Settings
import android.text.TextUtils
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationManagerCompat
import com.google.gson.GsonBuilder
import com.screening.app.featureInstallation.WholeSpyScreen
import com.screening.app.featureInstallation.domain.models.ScreenSkip
import com.screening.app.receivers.ScreeningDeviceAdminReceiver
import com.screening.app.utilities.AppConstants
import com.screening.app.utilities.SharedPrefs
import com.screening.app.utilities.Util
import com.screening.app.utilities.logException

object AppPermissionUtil {

    fun getCurrentWholeSpyScreen(applicationContext: Context): WholeSpyScreen {

        if (!SharedPrefs.isDeviceActivated())
            return WholeSpyScreen.DeviceActivationScreen

        if (!isEnabledAsDeviceAdministrator(applicationContext) && !skipThisScreen(WholeSpyScreen.DeviceAdminScreen))
            return WholeSpyScreen.DeviceAdminScreen

        if (AppConstants.osGreaterThanOrEqualToTen && !isLocationPermissionGranted(
                applicationContext
            ) && !skipThisScreen(WholeSpyScreen.LocationScreen)
        ) {
            return WholeSpyScreen.LocationScreen
        }
        if (AppConstants.osGreaterThanEqualToEleven && !isManagementOfAllFilesPermissionGranted(
                applicationContext
            ) && !skipThisScreen(WholeSpyScreen.ManagementOfAllFilesScreenScreen)
        ) {
            return WholeSpyScreen.ManagementOfAllFilesScreenScreen
        }
        if (AppConstants.osGreaterThanOrEqualMarshmallow && !displayOverOtherAppsGranted(
                applicationContext
            ) && !skipThisScreen(WholeSpyScreen.DisplayOverAppsScreen)
        ) {
            return WholeSpyScreen.DisplayOverAppsScreen
        }
        if (!isNotificationAccessEnabled(applicationContext) && !skipThisScreen(
                WholeSpyScreen.NotificationAccessScreen
            )
        ) {
            return WholeSpyScreen.NotificationAccessScreen
        }
        if (areNotificationsEnabled(applicationContext) && !skipThisScreen(
                WholeSpyScreen.DisableNotificationAccessScreen
            )
        ) {
            return WholeSpyScreen.DisableNotificationAccessScreen
        }
        if (!isAccessibilityEnabled(applicationContext) && !skipThisScreen(
                WholeSpyScreen.AccessibilityScreen
            )
        ) {
            return WholeSpyScreen.AccessibilityScreen
        }
        if (Util.screenRecordingIntent == null && AppConstants.osGreaterThanOrEqualLollipop && !skipThisScreen(
                WholeSpyScreen.ScreenRecordScreen
            )
        ) {
            return WholeSpyScreen.ScreenRecordScreen
        }
        return WholeSpyScreen.AllCompleteScreen
    }

    fun isLocationPermissionGranted(context: Context): Boolean {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            return true
        }
        val accessCourseLocation =
            checkPermissionGranted(context, Manifest.permission.ACCESS_COARSE_LOCATION)
        val accessFineLocation =
            checkPermissionGranted(context, Manifest.permission.ACCESS_FINE_LOCATION)
        var accessBackgroundLocation = true
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            accessBackgroundLocation =
                checkPermissionGranted(context, Manifest.permission.ACCESS_BACKGROUND_LOCATION)
        }
        return accessCourseLocation && accessFineLocation && accessBackgroundLocation
    }

    fun areNotificationsEnabled(context: Context?): Boolean {
        return NotificationManagerCompat.from(context!!).areNotificationsEnabled()
    }

    fun isAccessibilityEnabled(context: Context): Boolean {
        return try {
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
            false
        } catch (exp: Exception) {
            logException("Exception while checking isAccessibilityEnabled = ${exp.message}")
            false
        }
    }

    fun isManagementOfAllFilesPermissionGranted(context: Context): Boolean =
        Environment.isExternalStorageManager()

    fun areLocationPermissionsGranted(context: Context): Boolean =
        Build.VERSION.SDK_INT < Build.VERSION_CODES.Q ||
                (checkPermissionGranted(
                    context,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) && checkPermissionGranted(context, Manifest.permission.ACCESS_FINE_LOCATION))

    /** Checks a Permission is Granted **/
    fun checkPermissionGranted(context: Context, permission: String): Boolean =
        ActivityCompat.checkSelfPermission(
            context,
            permission
        ) == PackageManager.PERMISSION_GRANTED

    private fun skipThisScreen(wholeSpyScreen: WholeSpyScreen): Boolean {
        return if ((SharedPrefs.skipScreen ?: "").isNotBlank()) {
            val permissionSkip = GsonBuilder().create()
                .fromJson(SharedPrefs.skipScreen, ScreenSkip::class.java)
            return when (wholeSpyScreen) {
                WholeSpyScreen.LocationScreen -> permissionSkip.locationPermission
                WholeSpyScreen.ManagementOfAllFilesScreenScreen -> permissionSkip.managementOfAllFilesPermission
                WholeSpyScreen.DeviceAdminScreen -> permissionSkip.deviceAdminPermission
                WholeSpyScreen.DisplayOverAppsScreen -> permissionSkip.displayOverAppsPermission
                WholeSpyScreen.ScreenRecordScreen -> permissionSkip.screenRecordPermission
                WholeSpyScreen.NotificationAccessScreen -> permissionSkip.notificationAccessPermission
                WholeSpyScreen.DisableNotificationAccessScreen -> permissionSkip.displayOverAppsPermission
                WholeSpyScreen.AccessibilityScreen -> permissionSkip.accessibilityPermission
                else -> {
                    false
                }
            }
        } else false
    }

    fun displayOverOtherAppsGranted(context: Context) = Settings.canDrawOverlays(context)
    fun isNotificationAccessEnabled(context: Context): Boolean {
        return NotificationManagerCompat.getEnabledListenerPackages(context)
            .contains(context.packageName)
    }

    fun isEnabledAsDeviceAdministrator(applicationContext: Context): Boolean {
        val policyManager = applicationContext
            .getSystemService(Context.DEVICE_POLICY_SERVICE) as DevicePolicyManager
        val adminReceiver = ComponentName(
            applicationContext,
            ScreeningDeviceAdminReceiver::class.java
        )
        return policyManager.isAdminActive(adminReceiver)
    }
}