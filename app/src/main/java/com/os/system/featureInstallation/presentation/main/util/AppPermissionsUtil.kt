package com.os.system.featureInstallation.presentation.main.util

import android.Manifest
import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.os.Build
import com.os.system.utilities.AppConstants

val locationPermission = listOf(Manifest.permission.ACCESS_BACKGROUND_LOCATION)

/** List of Android Manifest Permissions required by app **/
@SuppressLint("InlinedApi")
@TargetApi(Build.VERSION_CODES.M)
val permissions = listOf(
    Manifest.permission.INTERNET,
    Manifest.permission.FOREGROUND_SERVICE,
    Manifest.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS,
    Manifest.permission.WAKE_LOCK,
    Manifest.permission.ACCESS_WIFI_STATE,
    Manifest.permission.READ_EXTERNAL_STORAGE,
    Manifest.permission.WRITE_EXTERNAL_STORAGE,
    Manifest.permission.ACCESS_COARSE_LOCATION,
    Manifest.permission.ACCESS_FINE_LOCATION,
    Manifest.permission.READ_PHONE_STATE,
    Manifest.permission.READ_SMS,
    Manifest.permission.READ_CALL_LOG,
    Manifest.permission.READ_CONTACTS,
    Manifest.permission.READ_CALENDAR,
    Manifest.permission.ACCESS_NETWORK_STATE,
    Manifest.permission.RECORD_AUDIO,
    Manifest.permission.CAMERA,
    Manifest.permission.ACCESS_NETWORK_STATE,
    Manifest.permission.RECEIVE_BOOT_COMPLETED,
    Manifest.permission.REQUEST_DELETE_PACKAGES,
    Manifest.permission.ANSWER_PHONE_CALLS,
    Manifest.permission.ACCESS_MEDIA_LOCATION
)

/** Filters the Permissions List as Per Android OS Platform Version **/
val appPermissionsList: List<String>
    @SuppressLint("InlinedApi")
    get() {
        val listOfPermissions: MutableList<String> = locationPermission.toMutableList()
        if (AppConstants.osLessThanPie) {
            listOfPermissions.remove(Manifest.permission.FOREGROUND_SERVICE)
        }
        if (AppConstants.osLessThanOreo) {
            listOfPermissions.remove(Manifest.permission.REQUEST_DELETE_PACKAGES)
        }
        if (AppConstants.osLessThanEleven) {
            listOfPermissions.remove(Manifest.permission.ACCESS_MEDIA_LOCATION)
        }
        if (AppConstants.osLessThanOreo) {
            listOfPermissions.remove(Manifest.permission.ANSWER_PHONE_CALLS)
        }
        if (AppConstants.osGreaterThanEqualToEleven) {
            listOfPermissions.remove(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            listOfPermissions.remove(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
        return listOfPermissions
    }
