package com.im.bin.appUtils

import android.content.Context

object PreferenceUtil {

    @JvmStatic
    fun getPreference(context: Context, key: String): String {
        return SharedPreferencesUtil(context).getPreferenceValue(key)!!
    }
}