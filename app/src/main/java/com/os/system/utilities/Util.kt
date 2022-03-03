package com.os.system.utilities

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context

object Util {

    /**
     * Checks whether app has been granted the location permission or not
     * Returns True if the Location permission is granted
     */
    @SuppressLint("InlinedApi")
    fun isLocationPermissionGranted(context: Context): Boolean {
        if (AppConstants.osLessThanTen)
            return true
        val accessCourseLocation =
            context.checkPermissionGranted(Manifest.permission.ACCESS_COARSE_LOCATION)
        val accessFineLocation =
            context.checkPermissionGranted(Manifest.permission.ACCESS_FINE_LOCATION)
        var accessBackgroundLocation = true
        if (AppConstants.osGreaterThanEqualToTen) {
            accessBackgroundLocation =
                context.checkPermissionGranted(Manifest.permission.ACCESS_BACKGROUND_LOCATION)
        }
        return accessCourseLocation && accessFineLocation && accessBackgroundLocation
    }

}