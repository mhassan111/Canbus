package com.im.bin.ui

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.billingclient.api.*
import com.im.bin.R
import com.im.bin.appUtils.Constants
import com.im.bin.appUtils.FontManager
import com.im.bin.appUtils.SharedPreferencesUtil
import com.im.bin.appUtils.Util
import com.im.bin.dialog.CustomProgressDialog
import kotlinx.android.synthetic.main.activity_premium_features.*
import timber.log.Timber
import java.util.*

class PremiumFeaturesActivity : AppCompatActivity() {

    private val progressDialog = CustomProgressDialog()
    val billingClient: BillingClient by lazy {
        BillingClient.newBuilder(this)
            .enablePendingPurchases()
            .setListener(purchasesUpdatedListener).build()
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_premium_features)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window: Window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = Color.TRANSPARENT
        }
        FontManager.markAsIconContainer(
            root_layout,
            FontManager.getTypeface(this, FontManager.FONT_AWESOME)
        )
        remove_ads.text = "${resources.getString(R.string.fa_check)}    Remove Ads\n"
        back_conversations.text =
            "${resources.getString(R.string.fa_check)}    Backup all WhatsApp, Line, Instagram, SnapChat, IMO, Viber, Hike Conversations\n\n" +
                    "${resources.getString(R.string.fa_check)}    Restore and view chats on any other Device by Logging In with your account\n\n" +
                    "${resources.getString(R.string.fa_check)}    Fetch and restore all Conversations"

        closeButton.setOnClickListener {
            finish()
        }

        buyPremiumFeatures.setOnClickListener {
            if (Util.checkInternetIsConnected(this@PremiumFeaturesActivity)) {
                showProgressDialog()
                launchAppBillingClient()
            } else {
                Util.showAlertDialog(
                    this@PremiumFeaturesActivity,
                    "Error",
                    "Check your internet Connection"
                )
            }
        }
    }

    private fun showProgressDialog() {
        if (!progressDialog.isShowing()) {
            progressDialog.show(this, "Loading...")
        }
    }

    private fun hideProgressDialog() {
        if (progressDialog.isShowing()) {
            progressDialog.dialog!!.dismiss()
        }
    }


    private fun launchAppBillingClient() {
        billingClient.startConnection(object : BillingClientStateListener {
            override fun onBillingServiceDisconnected() {
                hideProgressDialog()
                Toast.makeText(
                    this@PremiumFeaturesActivity,
                    "Purchase Failed. Try Again",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }

            override fun onBillingSetupFinished(billingResult: BillingResult?) {
                if (billingResult!!.responseCode == BillingClient.BillingResponseCode.OK) {
                    hideProgressDialog()
                    if (!TextUtils.isEmpty(
                            SharedPreferencesUtil(this@PremiumFeaturesActivity).getPreferenceValue(
                                Constants.PREF_PREMIUM_FEATURES_JSON
                            )
                        ) || checkAppPurchaseStatus(billingClient)
                    ) {
                        Util.showAlertDialog(
                            this@PremiumFeaturesActivity,
                            "Purchase",
                            "You have already Purchased the Premium Features"
                        )
                    } else {
                        val params = SkuDetailsParams.newBuilder()
                        val skuList: MutableList<String> =
                            ArrayList()
                        skuList.add(Constants.premiumFeatureSku)
                        params.setSkusList(skuList).setType(BillingClient.SkuType.INAPP)
                        billingClient.querySkuDetailsAsync(
                            params.build()
                        ) { result, skuDetailsList ->
                            if (result!!.responseCode == BillingClient.BillingResponseCode.OK) {
                                if (!skuDetailsList.isNullOrEmpty()) {
                                    for (skuDetails in skuDetailsList) {
                                        val sku = skuDetails.sku
                                        if (Constants.premiumFeatureSku == sku) {
                                            val flowParams =
                                                BillingFlowParams.newBuilder()
                                                    .setSkuDetails(skuDetails)
                                                    .build()
                                            val flowResult: BillingResult =
                                                billingClient.launchBillingFlow(
                                                    this@PremiumFeaturesActivity,
                                                    flowParams
                                                )
                                            Timber.d("Billing Result = $flowResult")
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        })
    }

    private fun checkAppPurchaseStatus(billingClient: BillingClient): Boolean {
        val result = billingClient.queryPurchases(BillingClient.SkuType.INAPP)
        val purchases = result.purchasesList
        if (!purchases.isNullOrEmpty()) {
            for (purchase in purchases) {
                val sku = purchase.sku
                if (sku == Constants.premiumFeatureSku) {
                    SharedPreferencesUtil(this@PremiumFeaturesActivity).setPreferenceValue(
                        Constants.PREF_PREMIUM_FEATURES_JSON,
                        purchase.originalJson
                    )
                    return true
                }

//                val consumeParams =
//                    ConsumeParams.newBuilder()
//                        .setPurchaseToken(purchase.purchaseToken)
//                        .setDeveloperPayload(purchase.developerPayload)
//                        .build()
//
//                billingClient.consumeAsync(
//                    consumeParams,
//                    ConsumeResponseListener { billingResult, result ->
//
//                    })

            }
        }
        return false
    }

    private val purchasesUpdatedListener: PurchasesUpdatedListener =
        PurchasesUpdatedListener { billingResult, purchases ->
            if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                if (!purchases.isNullOrEmpty()) {
                    for (purchase in purchases) {
                        val sku = purchase.sku
                        if (sku == Constants.premiumFeatureSku) {
                            SharedPreferencesUtil(this@PremiumFeaturesActivity).setPreferenceValue(
                                Constants.PREF_PREMIUM_FEATURES_JSON,
                                purchase.originalJson
                            )
                        }
                    }
                }
            }
        }

    override fun onDestroy() {
        super.onDestroy()
        billingClient.endConnection()
    }
}