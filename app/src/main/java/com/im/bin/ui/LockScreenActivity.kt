package com.im.bin.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.DisplayMetrics
import android.view.Display
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.material.textfield.TextInputEditText
import com.im.bin.R
import com.im.bin.appUtils.*
import com.im.bin.appUtils.FontManager.getTypeface
import com.im.bin.appUtils.FontManager.markAsIconContainer
import java.util.*

class LockScreenActivity : AppCompatActivity() {

    private var launchType: String? = null
    private var linearLayout: LinearLayout? = null
    private var forgetPassword: TextView? = null
    lateinit var adContainerView: FrameLayout
    lateinit var adView: AdView

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_lock)
        launchType = intent.getStringExtra(LAUNCH_TYPE)
        val iconFont = getTypeface(
            this,
            FontManager.FONT_AWESOME
        )
        markAsIconContainer(findViewById(R.id.lock_layout), iconFont)

        adContainerView = findViewById(R.id.ad_view_container)
        adView = AdView(this)
        adView.adUnitId = Constants.bannerAdId
        adContainerView.addView(adView)

        val status = findViewById<TextView>(R.id.status)
        val pin = findViewById<TextInputEditText>(R.id.pin)
        val nickName = findViewById<TextInputEditText>(R.id.nick_name)
        linearLayout = findViewById(R.id.nickNameLayout)
        forgetPassword = findViewById(R.id.forget_password)
        val submit = findViewById<Button>(R.id.enter_pin)

        val previousPinCode: String =
            SharedPreferencesUtil(this).getPreferenceValue(Constants.PREF_PIN_CODE)!!
        if (launchType == TYPE_ENTER_PIN) {
            pin.setText(previousPinCode)
            nickName.setText(
                SharedPreferencesUtil(this).getPreferenceValue(Constants.PREF_NICK_NAME)!!
            )
            submit.text = "Save"
            showLayout()
            hideForgetPassword()
        } else {
            hideLayout()
            showForgetPassword()
        }

        submit.setOnClickListener { _: View? ->
            val pinCode =
                Objects.requireNonNull(pin.text).toString()
                    .trim { it <= ' ' }
            if (TextUtils.isEmpty(pinCode)) {
                status.text = getString(R.string.fa_exclamation) + " Error! Please enter Pin"
                return@setOnClickListener
            }
            if (launchType == TYPE_UNINSTALL_PROTECTION || launchType == TYPE_APP_LAUNCH_PROTECTION) {
                if (pinCode == SharedPreferencesUtil(this).getPreferenceValue(
                        Constants
                            .PREF_PIN_CODE
                    )!!
                ) {
                    if (launchType == TYPE_APP_LAUNCH_PROTECTION) {
                        ActivityUtil.launchHomeActivity(this)
                        finish()
                    } else {
                        PreferenceManager.getDefaultSharedPreferences(this).edit()
                            .putBoolean(getString(R.string.key_uninstall_app_protection), false)
                            .apply()
                        Toast.makeText(this, "Uninstall Protection UnLocked", Toast.LENGTH_SHORT)
                            .show()
                        finish()
                    }
                } else {
                    status.text = getString(R.string.fa_exclamation) + " Oops! Pin Not Correct"
                }
            } else if (launchType == TYPE_ENTER_PIN) {
                if (!TextUtils.isEmpty(previousPinCode) && pinCode == previousPinCode) {
                    status.text =
                        getString(R.string.fa_exclamation) + " Oops! Please enter Updated Pin Code to Save"
                } else {
                    val name =
                        Objects.requireNonNull(nickName.text).toString()
                            .trim { it <= ' ' }
                    if (pinCode.length < 4) {
                        status.text =
                            getString(R.string.fa_exclamation) + " Error! Enter At least 4 Digit Pin"
                        return@setOnClickListener
                    } else if (TextUtils.isEmpty(name)) {
                        status.text =
                            getString(R.string.fa_exclamation) + " Error! Please Enter Nick Name"
                        return@setOnClickListener
                    }
                    SharedPreferencesUtil(this).setPreferenceValue(
                        Constants.PREF_PIN_CODE,
                        pinCode
                    )
                    SharedPreferencesUtil(this).setPreferenceValue(
                        Constants.PREF_NICK_NAME,
                        name
                    )
                    status.text = "Congrats! Pin Code Saved Successfully"
                    Toast.makeText(
                        this,
                        "Congrats! Pin Code Saved Successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                    finish()
                }
            }
        }

        forgetPassword?.setOnClickListener {
            val intent = Intent(applicationContext, RecoverPinActivity::class.java)
            startActivity(intent)
        }
        if (Util.showLoadAds(this)) {
            loadBannerAd()
            AdMobUtil.loadInterstitialAds(this)
        }
    }

    fun loadBannerAd() {
        val adRequest = AdRequest.Builder().build()
        val adSize = getAdSize()
        adView.adSize = adSize
        adView.loadAd(adRequest)
    }

    private fun getAdSize(): AdSize? {
        val display: Display = windowManager.defaultDisplay
        val outMetrics = DisplayMetrics()
        display.getMetrics(outMetrics)
        val widthPixels = outMetrics.widthPixels.toFloat()
        val density = outMetrics.density
        val adWidth = (widthPixels / density).toInt()
        return AdSize.getCurrentOrientationBannerAdSizeWithWidth(
            this,
            adWidth
        )
    }

    private fun showLayout() {
        linearLayout!!.visibility = View.VISIBLE
    }

    private fun hideLayout() {
        linearLayout!!.visibility = View.GONE
    }

    private fun hideForgetPassword() {
        forgetPassword!!.visibility = View.GONE
    }

    private fun showForgetPassword() {
        forgetPassword!!.visibility = View.VISIBLE
    }

    companion object {
        const val LAUNCH_TYPE = "LAUNCH_TYPE"
        const val TYPE_UNINSTALL_PROTECTION = "TYPE_UNINSTALL_PROTECTION"
        const val TYPE_APP_LAUNCH_PROTECTION = "TYPE_APP_LAUNCH_PROTECTION"
        const val TYPE_ENTER_PIN = "TYPE_ENTER_PIN"
    }
}