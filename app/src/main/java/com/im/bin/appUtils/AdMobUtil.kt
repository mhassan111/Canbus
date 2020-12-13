package com.im.bin.appUtils

import android.content.Context
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import timber.log.Timber

object AdMobUtil {

    @JvmStatic
    fun loadInterstitialAds(context: Context?) {
        val mInterstitialAd = InterstitialAd(context)
        mInterstitialAd.adUnitId = Constants.interstitialAdId
        mInterstitialAd.loadAd(AdRequest.Builder().build())
        mInterstitialAd.adListener = object : AdListener() {
            override fun onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                Timber.d("Interstitial Add loaded")
                mInterstitialAd.show()
            }

            override fun onAdFailedToLoad(errorCode: Int) {
                // Code to be executed when an ad request fails.
                Timber.d("Interstitial Add loading failed")
            }

            override fun onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
                Timber.d("Interstitial Add opened")
            }

            override fun onAdClicked() {
                // Code to be executed when the user clicks on an ad.
                Timber.d("Interstitial Add clicked")
            }

            override fun onAdLeftApplication() {
                // Code to be executed when the user has left the app.
                Timber.d("Interstitial Add app exit")
            }

            override fun onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
                Timber.d("Interstitial Add closed")
            }
        }
    }
}