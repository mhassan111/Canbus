@file:Suppress("DEPRECATION")

package com.im.bin.appUtils

import android.os.Environment

object Constants {

    /**
     * Shared Preferences Constants
     * These constants defining the Unique Keys
     * SharedPreference are used to store key value pairs
     */
    const val PREF_USER = "PREF_USER"
    const val PREF_USER_LOGGED_IN = "PREF_USER_LOGGED_IN"
    const val PREF_USER_ATTENDANCE = "PREF_USER_ATTENDANCE"

    const val removeAdsSku = "whatsapp.bin"
    const val premiumFeatureSku = "com.premium.features"

//     Test Id
//    const val bannerAdId = "ca-app-pub-3940256099942544/6300978111"
//    const val interstitialAdId = "ca-app-pub-3940256099942544/1033173712"

    const val bannerAdId = "ca-app-pub-5833550661504199/3218356448"
    const val interstitialAdId = "ca-app-pub-5833550661504199/9947416322"

    const val PREF_REMOVE_ADS_JSON = "APP_PURCHASE_JSON"
    const val PREF_PREMIUM_FEATURES_JSON = "APP_PREMIUM_FEATURES_JSON"
    const val PREF_SHOW_RATE_US_DIALOG = "SHOW_RATE_US_DIALOG"
    const val PREF_LAUNCH_COUNT = "PREF_LAUNCH_COUNT"
    const val PREF_LAUNCH_DATE = "PREF_LAUNCH_DATE"
    const val PREF_PIN_CODE = "PIN_CODE_LOCK"
    const val PREF_NICK_NAME = "NICK_NAME_PREF"

    /**
     * Constants specifying the different formats of dates
     */
    const val DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss"
    const val DATE_FORMAT = "yyyy-MM-dd"
    const val DATE_FORMAT_2 = "dd-MMM-yy"

    val WHATS_APP_STORAGE by lazy {
        Environment.getExternalStorageDirectory().toString() + "/WhatsApp/Media/"
    }

    val WHATS_STORE_DIR by lazy {
        Environment.getExternalStorageDirectory().toString() + "/WhatsDeleted Store/"
    }

    const val DIR_PHOTOS = "PHOTOS"
    const val DIR_VIDEOS = "VIDEOS"
    const val DIR_VOICE_NOTES = "VOICE_NOTES"
    const val DIR_STATUSES = "STATUSES"
    const val DIR_DOCUMENTS = "DOCUMENTS"
    const val DIR_VOIP_CALLS = "VOIP_CALLS"
}