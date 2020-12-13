package com.im.bin.ui

import android.Manifest
import android.app.AlertDialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.Layout
import android.text.TextUtils
import android.util.TypedValue
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.app.ActivityCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.android.billingclient.api.*
import com.google.android.material.navigation.NavigationView
import com.google.android.play.core.review.ReviewInfo
import com.google.android.play.core.review.ReviewManagerFactory
import com.im.bin.R
import com.im.bin.appUtils.*
import com.im.bin.dialog.CustomProgressDialog
import com.im.bin.enums.IMType
import com.im.bin.fragments.im.VoipCallRecordingFragment
import com.im.bin.fragments.im.WhatsAppChatFragment
import com.im.bin.fragments.im.WhatsAppFragment
import com.im.bin.interfaces.CustomDialogListener
import com.im.bin.interfaces.OnSearchFilter
import com.im.bin.respository.UserRepository
import com.im.bin.services.ForegroundService
import com.im.bin.ui.base.BaseActivity
import kotlinx.android.synthetic.main.nav_header.view.*
import timber.log.Timber
import java.util.*
import javax.inject.Inject
import kotlin.system.exitProcess

class HomeActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    companion object {
        private const val REQUEST_PERMISSION_CODE = 1000

        @RequiresApi(Build.VERSION_CODES.M)
        private val permissionsArray = arrayOf(
            Manifest.permission.INTERNET,
            Manifest.permission.RECEIVE_BOOT_COMPLETED,
            Manifest.permission.ACCESS_WIFI_STATE,
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS,
            Manifest.permission.SYSTEM_ALERT_WINDOW,
            Manifest.permission.RECORD_AUDIO
        )
    }

    private var reviewInfo: ReviewInfo? = null
    private lateinit var mDrawerLayout: DrawerLayout
    private lateinit var mNavigationView: NavigationView

    //    private lateinit var mWhatsAppChatFragment: WhatsAppChatFragment
    private lateinit var mWhatsAppFragment: WhatsAppFragment
    private var voipCallRecordingFragment: VoipCallRecordingFragment? = null

    //    private lateinit var mWhatsAppVoipFragment: WhatsAppVoipFragment
    private val progressDialog = CustomProgressDialog()

    val billingClient: BillingClient by lazy {
        BillingClient.newBuilder(this)
            .enablePendingPurchases()
            .setListener(purchasesUpdatedListener).build()
    }

    @Inject
    lateinit var userRepository: UserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setUpToolBar()
        setToolBarTitle("WhatsApp")
        initViews()
        loadWhatsAppFragment()
        setOnSearchQueryListener(object : OnSearchFilter {
            override fun onSearch(searchText: String) {
                onSearchQuery(searchText)
            }
        })
        startForegroundServiceIfNotRunning()
        if (AppRater.launchAppRater(this) && Util.checkInternetIsConnected(this) && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            reviewApp()
        }
        AppUpdater(this).checkForUpdates()
        requestRunTimePermissions()
    }

    private fun reviewApp() {
        val reviewManager = ReviewManagerFactory.create(this)
        val requestFlow = reviewManager.requestReviewFlow()
        requestFlow.addOnCompleteListener { request ->
            if (request.isSuccessful) {
                reviewInfo = request.result
                reviewInfo?.let {
                    val flow = reviewManager.launchReviewFlow(this@HomeActivity, it)
                    flow.addOnCompleteListener {
                        SharedPreferencesUtil(this).setBoolPref(
                            Constants.PREF_SHOW_RATE_US_DIALOG,
                            true
                        )
                    }
                }
            } else {
                reviewInfo = null
            }
        }
    }

    private fun startForegroundServiceIfNotRunning() {
        Util.startService(this, Intent(this, ForegroundService::class.java))
//        if (!Util.isServiceRunning(this, ForegroundService::class.java.name))
//            Util.startService(this, Intent(this, ForegroundService::class.java))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logOut -> {
                logOutUser()
            }
            R.id.settings -> {
                startSettingsActivity()
            }
            R.id.remove_ads -> {
                buyPremiumPackage()
            }
            R.id.buy_premium -> {
                ActivityUtil.launchPremiumFeaturesActivity(this)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun onSearchQuery(query: String) {
        when (val fragment = supportFragmentManager.findFragmentById(R.id.fragment_container)) {
            is WhatsAppChatFragment -> fragment.onSearchQuery(query)
            is WhatsAppFragment -> fragment.onSearchQuery(query)
            is VoipCallRecordingFragment -> fragment.onSearchQuery(query)
        }
    }

    private val customDialogListener: CustomDialogListener = object : CustomDialogListener {
        override fun onYes() {
            if (Util.checkInternetIsConnected(this@HomeActivity)) {
                showProgressDialog()
                launchAppBillingClient()
            } else {
                Util.showAlertDialog(
                    this@HomeActivity,
                    "Error",
                    "Check your internet Connection"
                )
            }
        }

        override fun onCancel() {

        }
    }

    override fun initViews() {
        mDrawerLayout = findViewById(R.id.drawer_layout)
        mNavigationView = mDrawerLayout.findViewById(R.id.nav_view)
        val headerView = mNavigationView.getHeaderView(0)
        val currentUser = userRepository.currentUser()
        currentUser?.let {
            headerView.user_email.text = currentUser.email
        }
        val toggle = ActionBarDrawerToggle(this, mDrawerLayout, toolbar, 0, 0)
        mDrawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        mNavigationView.bringToFront()
        addMenuIcons()
        mNavigationView.setNavigationItemSelectedListener(this)
    }

    private fun loadWhatsAppFragment() {
//        mWhatsAppVoipFragment = WhatsAppVoipFragment.newInstance()
//        mWhatsAppChatFragment = WhatsAppChatFragment.newInstance(IMType.WhatsApp.toString())
        mWhatsAppFragment = WhatsAppFragment.newInstance()
        val supportFragmentManager = supportFragmentManager
        mWhatsAppFragment.let {
            supportFragmentManager.beginTransaction().replace(
                R.id.fragment_container,
                mWhatsAppFragment
            ).commit()
        }
    }

    private fun addMenuIcons() {
        val menu: Menu = mNavigationView.menu
        addIcon(menu.findItem(R.id.nav_whatsapp), getString(R.string.fa_whats_app))
        addIcon(menu.findItem(R.id.nav_snapchat), getString(R.string.fa_snapchat))
        addIcon(menu.findItem(R.id.nav_instagram), getString(R.string.fa_instagram))
        addIcon(menu.findItem(R.id.nav_viber), getString(R.string.fa_whats_app))
        addIcon(menu.findItem(R.id.nav_line), getString(R.string.fa_whats_app))
        addIcon(menu.findItem(R.id.nav_imo), getString(R.string.fa_whats_app))
        addIcon(menu.findItem(R.id.nav_hike), getString(R.string.fa_whats_app))
        addIcon(menu.findItem(R.id.nav_voip_calls), getString(R.string.fa_phone))
        addIcon(menu.findItem(R.id.nav_rate), getString(R.string.fa_rate))
        addIcon(menu.findItem(R.id.nav_remove_ads), getString(R.string.fa_buy))
        addIcon(menu.findItem(R.id.nav_premium_features), getString(R.string.fa_buy))
        addIcon(menu.findItem(R.id.nav_share), getString(R.string.fa_share))
        addIcon(menu.findItem(R.id.nav_settings), getString(R.string.fa_settings))
        addIcon(menu.findItem(R.id.nav_logout), getString(R.string.fa_log_out))
        addIcon(menu.findItem(R.id.nav_exit), getString(R.string.fa_log_out))
        mNavigationView.invalidate()
    }

    private fun addIcon(menuItem: MenuItem, icon: String) {
        val faIcon = TextDrawable(this)
        faIcon.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20.0F)
        faIcon.textAlign = Layout.Alignment.ALIGN_CENTER
        faIcon.typeface = FontManager.getTypeface(this, FontManager.FONT_AWESOME)
        faIcon.text = icon
        faIcon.textSize = 20.0F
        menuItem.icon = faIcon
        mNavigationView.invalidate()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var fragment: Fragment? = null
        when (item.title) {
            "WhatsApp" -> {
                fragment = WhatsAppFragment.newInstance()
//                fragment = WhatsAppChatFragment.newInstance(IMType.WhatsApp.toString())
                setToolBarTitle(item.title.toString())
            }
            "SnapChat" -> {
                fragment = WhatsAppChatFragment.newInstance(IMType.SnapChat.toString())
                setToolBarTitle(item.title.toString())
            }
            "Instagram" -> {
                fragment = WhatsAppChatFragment.newInstance(IMType.Instagram.toString())
                setToolBarTitle(item.title.toString())
            }
            "Viber" -> {
                fragment = WhatsAppChatFragment.newInstance(IMType.Viber.toString())
                setToolBarTitle(item.title.toString())
            }
            "Line" -> {
                fragment = WhatsAppChatFragment.newInstance(IMType.Line.toString())
                setToolBarTitle(item.title.toString())
            }
            "IMO" -> {
                fragment = WhatsAppChatFragment.newInstance(IMType.IMO.toString())
                setToolBarTitle(item.title.toString())
            }
            "Hike" -> {
                fragment = WhatsAppChatFragment.newInstance(IMType.Hike.toString())
                setToolBarTitle(item.title.toString())
            }
            "Voip Calls" -> {
                fragment = VoipCallRecordingFragment.newInstance()
                setToolBarTitle(item.title.toString())
            }
            "Remove Ads" -> {
                buyPremiumPackage()
            }
            "Premium Features" -> {
                ActivityUtil.launchPremiumFeaturesActivity(this)
            }
            "Settings" -> {
                startSettingsActivity()
            }
            "Share" -> {
                share()
            }
            "Rate Us" -> {
                rateUs()
            }
            "LogOut" -> {
                logOutUser()
            }
            "Exit App" -> {
                exit()
            }
        }
        val supportFragmentManager = supportFragmentManager
        fragment?.let {
            supportFragmentManager.beginTransaction().replace(
                R.id.fragment_container,
                fragment
            ).commit()
        }
        mDrawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun buyPremiumPackage() {
        Util.showAlertDialog(
            this@HomeActivity,
            "Remove Ads",
            "Purchase App to remove Ads",
            "Purchase",
            "Cancel",
            customDialogListener
        )
    }

    private fun logOutUser() {
        userRepository.logOut()
        ActivityUtil.launchLogInActivity(this)
        finish()
    }

    private fun startSettingsActivity() {
        val intent = Intent(this@HomeActivity, AppSettingsActivity::class.java)
        startActivity(intent)
    }

    private fun rateUs() {
        val uri = Uri.parse("market://details?id=$packageName")
        val goToMarket = Intent(Intent.ACTION_VIEW, uri)
        goToMarket.addFlags(
            Intent.FLAG_ACTIVITY_NO_HISTORY or
                    Intent.FLAG_ACTIVITY_NEW_DOCUMENT or
                    Intent.FLAG_ACTIVITY_MULTIPLE_TASK
        )
        try {
            startActivity(goToMarket)
        } catch (e: ActivityNotFoundException) {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=$packageName")
                )
            )
        }
    }

    private fun share() {
        val shareBody = "http://play.google.com/store/apps/details?id=com.im.bin"
        val sharingIntent = Intent(Intent.ACTION_SEND)
        sharingIntent.type = "text/plain"
        sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody)
        startActivity(Intent.createChooser(sharingIntent, "WhatsDeleted Store"))
    }

    private fun exit() {
        exitProcess(0)
    }

    private fun launchAppBillingClient() {
        billingClient.startConnection(object : BillingClientStateListener {
            override fun onBillingServiceDisconnected() {
                hideProgressDialog()
                Toast.makeText(
                    this@HomeActivity,
                    "Purchase Failed. Try Again",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }

            override fun onBillingSetupFinished(billingResult: BillingResult?) {
                if (billingResult!!.responseCode == BillingClient.BillingResponseCode.OK) {
                    hideProgressDialog()
                    if (!TextUtils.isEmpty(
                            SharedPreferencesUtil(this@HomeActivity).getPreferenceValue(
                                Constants.PREF_REMOVE_ADS_JSON
                            )
                        )
                        || !TextUtils.isEmpty(
                            SharedPreferencesUtil(this@HomeActivity).getPreferenceValue(
                                Constants.PREF_PREMIUM_FEATURES_JSON
                            )
                        )
                        || checkAppPurchaseStatus(billingClient)
                    ) {
                        Util.showAlertDialog(
                            this@HomeActivity,
                            "Purchase",
                            "You have already Purchased Remove Ads"
                        )
                    } else {
                        val params = SkuDetailsParams.newBuilder()
                        val skuList: MutableList<String> =
                            ArrayList()
                        skuList.add(Constants.removeAdsSku)
                        params.setSkusList(skuList).setType(BillingClient.SkuType.INAPP)
                        billingClient.querySkuDetailsAsync(
                            params.build()
                        ) { result, skuDetailsList ->
                            if (result!!.responseCode == BillingClient.BillingResponseCode.OK) {
                                if (!skuDetailsList.isNullOrEmpty()) {
                                    for (skuDetails in skuDetailsList) {
                                        val sku = skuDetails.sku
                                        if (Constants.removeAdsSku == sku) {
                                            val flowParams =
                                                BillingFlowParams.newBuilder()
                                                    .setSkuDetails(skuDetails)
                                                    .build()
                                            val flowResult: BillingResult =
                                                billingClient.launchBillingFlow(
                                                    this@HomeActivity,
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
                if (sku == Constants.removeAdsSku) {
                    SharedPreferencesUtil(this@HomeActivity).setPreferenceValue(
                        Constants.PREF_REMOVE_ADS_JSON,
                        purchase.originalJson
                    )
                    return true
                } else if (sku == Constants.premiumFeatureSku) {
                    SharedPreferencesUtil(this@HomeActivity).setPreferenceValue(
                        Constants.PREF_PREMIUM_FEATURES_JSON,
                        purchase.originalJson
                    )
                    return true
                }
            }
//            for (purchase in purchases) {
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
//
//            }

        }
        return false
    }

    private val purchasesUpdatedListener: PurchasesUpdatedListener =
        PurchasesUpdatedListener { billingResult, purchases ->
            if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                if (!purchases.isNullOrEmpty()) {
                    for (purchase in purchases) {
                        val sku = purchase.sku
                        if (sku == Constants.removeAdsSku) {
                            SharedPreferencesUtil(this@HomeActivity).setPreferenceValue(
                                Constants.PREF_REMOVE_ADS_JSON,
                                purchase.originalJson
                            )
                        }
                    }
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

    override fun onBackPressed() {
        when {
            mDrawerLayout.isDrawerOpen(GravityCompat.START) -> mDrawerLayout.closeDrawer(
                GravityCompat.START
            )
            else -> super.onBackPressed()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        billingClient.endConnection()
    }


    private fun requestRunTimePermissions() {
        if (Util.osVersionGreaterThanLollipop()) {
            if (checkSelfPermission(HomeActivity.permissionsArray[0]) != PackageManager.PERMISSION_GRANTED || checkSelfPermission(
                    HomeActivity.permissionsArray[1]
                ) != PackageManager.PERMISSION_GRANTED || checkSelfPermission(HomeActivity.permissionsArray[2]) != PackageManager.PERMISSION_GRANTED || checkSelfPermission(
                    HomeActivity.permissionsArray[3]
                ) != PackageManager.PERMISSION_GRANTED || checkSelfPermission(HomeActivity.permissionsArray[4]) != PackageManager.PERMISSION_GRANTED || checkSelfPermission(
                    HomeActivity.permissionsArray[5]
                ) != PackageManager.PERMISSION_GRANTED || checkSelfPermission(
                    HomeActivity.permissionsArray[6]
                ) != PackageManager.PERMISSION_GRANTED || checkSelfPermission(
                    HomeActivity.permissionsArray[7]
                ) != PackageManager.PERMISSION_GRANTED || checkSelfPermission(
                    HomeActivity.permissionsArray[8]
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        this@HomeActivity,
                        HomeActivity.permissionsArray[0]
                    )
                    || ActivityCompat.shouldShowRequestPermissionRationale(
                        this@HomeActivity,
                        HomeActivity.permissionsArray[1]
                    )
                    || ActivityCompat.shouldShowRequestPermissionRationale(
                        this@HomeActivity,
                        HomeActivity.permissionsArray[2]
                    )
                    || ActivityCompat.shouldShowRequestPermissionRationale(
                        this@HomeActivity,
                        HomeActivity.permissionsArray[3]
                    )
                    || ActivityCompat.shouldShowRequestPermissionRationale(
                        this@HomeActivity,
                        HomeActivity.permissionsArray[4]
                    )
                    || ActivityCompat.shouldShowRequestPermissionRationale(
                        this@HomeActivity,
                        HomeActivity.permissionsArray[5]
                    )
                    || ActivityCompat.shouldShowRequestPermissionRationale(
                        this@HomeActivity,
                        HomeActivity.permissionsArray[6]
                    )
                    || ActivityCompat.shouldShowRequestPermissionRationale(
                        this@HomeActivity,
                        HomeActivity.permissionsArray[7]
                    )
                    || ActivityCompat.shouldShowRequestPermissionRationale(
                        this@HomeActivity,
                        HomeActivity.permissionsArray[8]
                    )
                ) {
                    displayPermissionRequestDialog()
                } else {
                    ActivityCompat.requestPermissions(
                        this@HomeActivity,
                        HomeActivity.permissionsArray
                        , HomeActivity.REQUEST_PERMISSION_CODE
                    )
                }
            }
        }
    }

    override fun checkSelfPermission(permission: String): Int {
        return ActivityCompat.checkSelfPermission(this@HomeActivity, permission)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            HomeActivity.REQUEST_PERMISSION_CODE -> {
                var allGranted = false
                for (result in grantResults) {
                    if (result == PackageManager.PERMISSION_GRANTED) {
                        allGranted = true
                    } else {
                        allGranted = false
                        break
                    }
                }
                if (allGranted) {
                    Timber.d("All Permission Granted.")
                } else if (ActivityCompat.shouldShowRequestPermissionRationale(
                        this@HomeActivity,
                        HomeActivity.permissionsArray[0]
                    )
                    || ActivityCompat.shouldShowRequestPermissionRationale(
                        this@HomeActivity,
                        HomeActivity.permissionsArray[1]
                    )
                    || ActivityCompat.shouldShowRequestPermissionRationale(
                        this@HomeActivity,
                        HomeActivity.permissionsArray[2]
                    )
                    || ActivityCompat.shouldShowRequestPermissionRationale(
                        this@HomeActivity,
                        HomeActivity.permissionsArray[3]
                    )
                    || ActivityCompat.shouldShowRequestPermissionRationale(
                        this@HomeActivity,
                        HomeActivity.permissionsArray[4]
                    )
                    || ActivityCompat.shouldShowRequestPermissionRationale(
                        this@HomeActivity,
                        HomeActivity.permissionsArray[5]
                    )
                    || ActivityCompat.shouldShowRequestPermissionRationale(
                        this@HomeActivity,
                        HomeActivity.permissionsArray[6]
                    )
                    || ActivityCompat.shouldShowRequestPermissionRationale(
                        this@HomeActivity,
                        HomeActivity.permissionsArray[7]
                    )
                    || ActivityCompat.shouldShowRequestPermissionRationale(
                        this@HomeActivity,
                        HomeActivity.permissionsArray[8]
                    )
                ) {
                    displayPermissionRequestDialog()
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun displayPermissionRequestDialog() {
        val builder =
            AlertDialog.Builder(this@HomeActivity, R.style.AlertDialogCustom)
        builder.setTitle("Need Permissions")
        builder.setMessage("App need Multiple permissions. Please Grant.")
        builder.setPositiveButton(
            "Grant"
        ) { dialog, _ ->
            dialog.cancel()
            ActivityCompat.requestPermissions(
                this@HomeActivity,
                HomeActivity.permissionsArray,
                HomeActivity.REQUEST_PERMISSION_CODE
            )
        }
        builder.setNegativeButton(
            "Cancel"
        ) { dialog, _ -> dialog.cancel() }
        builder.setCancelable(false)
        builder.show()
    }
}