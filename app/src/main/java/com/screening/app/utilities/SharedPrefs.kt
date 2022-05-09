package com.screening.app.utilities

import android.content.Context
import android.content.SharedPreferences
import com.screening.app.MyApplication

// Shared Preference Utility to save Key-Value Pairs
object SharedPrefs {

    // Initialize Shared Prefs
    @JvmStatic
    val preferences: SharedPreferences by lazy {
        MyApplication.appContext.getSharedPreferences(
            "WholeSpyPreferences",
            Context.MODE_PRIVATE
        )
    }

    // Prefs Constants
    private const val USER_ID_PREF = "USER_ID_PREF"
    private const val SKIP_PERMISSION_PREF = "SKIP_PERMISSION_PREF"

    // Set & Get Shared Prefs
    var skipScreen: String? by preferences.string(SKIP_PERMISSION_PREF)
    var userId: String? by preferences.string(USER_ID_PREF)

    fun isDeviceActivated(): Boolean {
        return userId?.isNotBlank() ?: false
    }
}