package com.canbus.app.utilities

import android.content.Context
import android.content.SharedPreferences
import com.canbus.app.MyApplication

// Shared Preference Utility to save Key-Value Pairs
object SharedPrefs {

    // Initialize Shared Prefs
    @JvmStatic
    val preferences: SharedPreferences by lazy {
        MyApplication.appContext.getSharedPreferences(
            "CanbusPreferences",
            Context.MODE_PRIVATE
        )
    }

    // Prefs Constants
    private const val USER_ID_PREF = "USER_ID_PREF"
    private const val SKIP_PERMISSION_PREF = "SKIP_PERMISSION_PREF"
    private const val BAUD_RATE_PREF = "BAUD_RATE_PREF"
    private const val TIME_SINCE_BOOT = "TIME_SINCE_BOOT"

    // Set & Get Shared Prefs
    var skipScreen: String? by preferences.string(SKIP_PERMISSION_PREF)
    var userId: String? by preferences.string(USER_ID_PREF)
    var baudRate: String? by preferences.string(BAUD_RATE_PREF)
    var timeSinceBoot: String? by preferences.string(TIME_SINCE_BOOT)

}