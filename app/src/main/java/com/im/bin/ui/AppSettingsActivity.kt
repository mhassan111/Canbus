package com.im.bin.ui

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.im.bin.BuildConfig
import com.im.bin.R
import com.im.bin.appUtils.ActivityUtil
import com.im.bin.appUtils.AdMobUtil
import com.im.bin.appUtils.Util

class AppSettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.pref_container, SettingsFragment())
                .commit()
        }
        if (Util.showLoadAds(this)) {
            AdMobUtil.loadInterstitialAds(this)
        }
    }

    class SettingsFragment : PreferenceFragmentCompat(), Preference.OnPreferenceChangeListener {
        override fun onCreatePreferences(
            savedInstanceState: Bundle?,
            rootKey: String?
        ) {
            addPreferencesFromResource(R.xml.pref_main)
            val myPref = findPreference(getString(R.string.key_send_feedback)) as Preference?
            myPref!!.onPreferenceClickListener =
                Preference.OnPreferenceClickListener {
                    sendFeedback(requireContext())
                    true
                }
            val appVersionPref = findPreference(getString(R.string.key_app_version)) as Preference?
            appVersionPref!!.summary = BuildConfig.VERSION_NAME
            val pinCodePreference = findPreference(getString(R.string.key_pin_code)) as Preference?
            pinCodePreference!!.onPreferenceClickListener =
                Preference.OnPreferenceClickListener {
                    ActivityUtil.launchLockScreenActivity(
                        requireContext(),
                        LockScreenActivity.TYPE_ENTER_PIN
                    )
                    true
                }
        }

        override fun onPreferenceChange(
            preference: Preference,
            newValue: Any
        ): Boolean {
            return true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        private val TAG = AppSettingsActivity::class.java.simpleName

        /**
         * Email client intent to send support mail
         * Appends the necessary device information to email body
         * useful when providing support
         */
        fun sendFeedback(context: Context) {
            var body: String? = null
            try {
                body = context.packageManager
                    .getPackageInfo(context.packageName, 0).versionName
                body =
                    """
                 -----------------------------
                 Please don't remove this information
                 Device OS: Android
                 Device OS version: ${Build.VERSION.RELEASE}
                 App Version: $body
                 Device Brand: ${Build.BRAND}
                 Device Model: ${Build.MODEL}
                 Device Manufacturer: ${Build.MANUFACTURER}"""
            } catch (e: PackageManager.NameNotFoundException) {
                e.message
            }
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "message/rfc822"
            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("codemine111@gmail.com"))
            intent.putExtra(Intent.EXTRA_SUBJECT, "Query From User")
            intent.putExtra(Intent.EXTRA_TEXT, body)
            context.startActivity(
                Intent.createChooser(
                    intent,
                    context.getString(R.string.choose_email_client)
                )
            )
        }
    }
}