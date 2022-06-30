package com.canbus.app.utilities

import android.os.Build

object AppConstants {

    const val PHONE_CALL_TYPE = "PhoneCall"
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