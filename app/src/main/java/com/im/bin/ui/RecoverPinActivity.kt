package com.im.bin.ui

import android.os.Bundle
import android.text.TextUtils
import android.util.DisplayMetrics
import android.view.Display
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.material.textfield.TextInputEditText
import com.im.bin.R
import com.im.bin.appUtils.AdMobUtil
import com.im.bin.appUtils.Constants
import com.im.bin.appUtils.SharedPreferencesUtil
import com.im.bin.appUtils.Util
import java.util.*

class RecoverPinActivity : AppCompatActivity() {

    lateinit var adContainerView: FrameLayout
    lateinit var adView: AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recover_pin)

        adContainerView = findViewById(R.id.ad_view_container)
        adView = AdView(this)
        adView.adUnitId = Constants.bannerAdId
        adContainerView.addView(adView)

        val nickName = findViewById<TextInputEditText>(R.id.nick_name)
        findViewById<View>(R.id.recover_pin).setOnClickListener { v: View? ->
            val name =
                Objects.requireNonNull(nickName.text).toString()
                    .trim { it <= ' ' }
            if (!TextUtils.isEmpty(name) && name.equals(
                    SharedPreferencesUtil(this).getPreferenceValue(
                        Constants.PREF_NICK_NAME
                    ), ignoreCase = true
                )
            ) {
                Util.showAlertDialog(
                    this@RecoverPinActivity, "Congrats", "Your Pin Code is: " +
                            SharedPreferencesUtil(this).getPreferenceValue(
                                Constants.PREF_PIN_CODE
                            )
                )
            } else {
                Util.showAlertDialog(this, "Error", "Not Able to Recover Your Pin Code")
            }
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
}