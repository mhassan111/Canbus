package com.im.bin.appUtils

import android.content.Context
import android.text.TextUtils
import com.im.bin.interfaces.CustomDialogListener

object AppRater {

    const val APP_PACKAGE_NAME = "com.im.bin"
    private const val DAYS_UNTIL_PROMPT = 1
    private const val LAUNCHES_UNTIL_PROMPT = 3

    fun launchAppRater(mContext: Context): Boolean {
        if (SharedPreferencesUtil(mContext).getBoolPref(Constants.PREF_SHOW_RATE_US_DIALOG)) {
            return false
        }

        var launchCount =
            SharedPreferencesUtil(mContext).getIntegerValue(Constants.PREF_LAUNCH_COUNT)
        if (launchCount == -1)
            launchCount = 0

        SharedPreferencesUtil(mContext).setIntegerValue(
            Constants.PREF_LAUNCH_COUNT,
            launchCount + 1
        )

        val dateLaunch =
            SharedPreferencesUtil(mContext).getPreferenceValue(Constants.PREF_LAUNCH_DATE)
        if (TextUtils.isEmpty(dateLaunch)) {
            SharedPreferencesUtil(mContext).setPreferenceValue(
                Constants.PREF_LAUNCH_DATE,
                System.currentTimeMillis().toString()
            )
        }

        if (launchCount >= LAUNCHES_UNTIL_PROMPT) {
            return System.currentTimeMillis() >= dateLaunch!!.toLong() + DAYS_UNTIL_PROMPT * 24 * 60 * 60 * 1000
        } else {
            return false
        }
    }

    private fun showRateDialog(
        mContext: Context
    ) {
        Util.showAlertDialog(mContext,
            "Rate Our App",
            "Please take a moment to rate the App. Thank You for your feedback",
            "Rate Us",
            "Remind me Later",
            object : CustomDialogListener {
                override fun onYes() {
                    SharedPreferencesUtil(mContext).setBoolPref(
                        Constants.PREF_SHOW_RATE_US_DIALOG,
                        true
                    )
                    Util.rateOnGooglePlay(mContext)
                }

                override fun onCancel() {

                }
            })
    }
}