package com.screening.app.utilities

import android.os.Build

object AppConstants {

    const val PHONE_CALL_TYPE = "PhoneCall"
    const val NOTIFICATION_STATE_CHANGE = "NotificationStateChange"

    const val DEVICE_INFO_TYPE: String = "DEVICE_INFO_TYPE"
    const val DEVICE_ACTIVATION_TYPE = "DEVICE_ACTIVATION_TYPE"

    const val TOS_BASE_URL_TAG = "tos_base"
    const val TOS_BASE_URL = "http://lb.theonespy.com/"

    val osGreaterThanEqualToNougat by lazy { Build.VERSION.SDK_INT >= Build.VERSION_CODES.N }
    val osGreaterThanEqualToOreo by lazy { Build.VERSION.SDK_INT >= Build.VERSION_CODES.O }
    val osGreaterThanEqualToTen by lazy { Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q }
    val osGreaterThanEqualToEleven by lazy { Build.VERSION.SDK_INT >= Build.VERSION_CODES.R }
    val osGreaterThanEqualToPie by lazy { Build.VERSION.SDK_INT >= Build.VERSION_CODES.P }
    val osLessThanTen by lazy { Build.VERSION.SDK_INT < Build.VERSION_CODES.Q }
    val osLessThanPie by lazy { Build.VERSION.SDK_INT < Build.VERSION_CODES.P }
    val osLessThanOreo by lazy { Build.VERSION.SDK_INT < Build.VERSION_CODES.O }
    val osLessThanEleven by lazy { Build.VERSION.SDK_INT < Build.VERSION_CODES.R }
    val osIsEleven by lazy { Build.VERSION.SDK_INT == Build.VERSION_CODES.R }
    val osGreaterThanOrEqualToTen by lazy { Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q }
    val osGreaterThanOrEqualMarshmallow by lazy { Build.VERSION.SDK_INT >= Build.VERSION_CODES.M }
    val osGreaterThanOrEqualLollipop by lazy { Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP }
}