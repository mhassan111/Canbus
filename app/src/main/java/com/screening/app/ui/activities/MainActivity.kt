package com.screening.app.ui.activities

import android.Manifest
import android.annotation.TargetApi
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.telephony.PhoneNumberUtils
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.screening.app.R
import com.screening.app.featureCallScreening.CallScreeningActivity
import com.screening.app.interfaces.OnPermissionGranted
import com.screening.app.ui.fragments.*
import com.screening.app.utilities.*

class MainActivity : BaseActivity(), OnPermissionGranted {

    private val mHandler: Handler by lazy { Handler(Looper.getMainLooper()) }
    private lateinit var batteryOptimizationFragment: PhoneBatteryOptimisationFragment
    private lateinit var deviceAccessibilityPermissionFragment: DeviceAccessibilityPermissionFragment
    private lateinit var displayOverAppsFragment: DisplayOverAppsFragment
    private lateinit var managementOfFilesFragment: ManagementOfFilesFragment
    private lateinit var deviceAdministratorFragment: DeviceAdministratorFragment
    private lateinit var layout: View

    companion object {

        const val TAG = "MainActivity"

        @TargetApi(Build.VERSION_CODES.M)
        var permissions = arrayOf(
            Manifest.permission.INTERNET,
            Manifest.permission.FOREGROUND_SERVICE,
            Manifest.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.RECEIVE_BOOT_COMPLETED,
            Manifest.permission.ANSWER_PHONE_CALLS,
            Manifest.permission.PROCESS_OUTGOING_CALLS,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_CALL_LOG,
            Manifest.permission.WRITE_CALL_LOG
        )

        // Returns the List Permissions required
        val appPermissionsList: Array<String>
            get() {
                val listOfPermissions: MutableList<String> = permissions.toMutableList()
                if (AppConstants.osLessThanPie) {
                    listOfPermissions.remove(Manifest.permission.FOREGROUND_SERVICE)
                }
                if (AppConstants.osLessThanOreo) {
                    listOfPermissions.remove(Manifest.permission.ANSWER_PHONE_CALLS)
                }
                if (AppConstants.osLessThanOreo) {
                    listOfPermissions.remove(Manifest.permission.ANSWER_PHONE_CALLS)
                }
                if (AppConstants.osGreaterThanEqualToEleven) {
                    listOfPermissions.remove(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    listOfPermissions.remove(Manifest.permission.READ_EXTERNAL_STORAGE)
                }
                return listOfPermissions.toTypedArray()
            }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        layout = findViewById(R.id.main_layout)
        setUpToolBar()
        setToolBarTitle("Screening App")
        initViews()

        // Remove Old Callbacks, and Add new Callback to the Handler
        mHandler.removeCallbacks(mRunnable)
        mHandler.postDelayed(mRunnable, 500)
    }

    override fun onResume() {
        super.onResume()
//        if (Util.osGreaterThanEqualToEleven && !Util.isManagementOfAllFilesPermissionGranted(this)) {
//            loadManagementOfAllFilesFragment()
//        }
        if (!Util.isAppIgnoringBatteryOptimization(this@MainActivity)) {
            loadBatteryOptimizationFragment()
        } else if (!Util.checkDeviceAdminPermissionGranted(this)) {
            loadDeviceAdminFragment()
        }
//        else if (!Util.displayOverOtherAppsGranted(this@MainActivity)) {
//            loadNotificationAccessFragment()
//        }
        else if (!Util.checkAccessibilityPermissionGranted(this@MainActivity)) {
            loadAccessibilityAccessFragment()
        } else {
            launchScreenAppActivity()
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onAttachFragment(fragment: Fragment) {
        super.onAttachFragment(fragment)
        when (fragment) {
            is PhoneBatteryOptimisationFragment -> {
                fragment.setOnPermissionGrantedListener(this)
            }
            is DeviceAdministratorFragment -> {
                fragment.setOnPermissionGrantedListener(this)
            }
            is DisplayOverAppsFragment -> {
                fragment.setOnPermissionGrantedListener(this)
            }
            is ManagementOfFilesFragment -> {
                fragment.setOnPermissionGrantedListener(this)
            }
        }
    }

    private fun loadDisplayOverAppsPermission() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, displayOverAppsFragment).commit()
    }

    private fun loadAccessibilityAccessFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, deviceAccessibilityPermissionFragment).commit()
    }

    private fun loadDeviceAdminFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, deviceAdministratorFragment).commit()
    }

    private fun loadBatteryOptimizationFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, batteryOptimizationFragment).commit()
    }

    private fun loadManagementOfAllFilesFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, managementOfFilesFragment).commit()
    }

    private val mRunnable = Runnable {
        when (supportFragmentManager.findFragmentById(R.id.container)) {
            is ManagementOfFilesFragment -> {
                if (Util.isManagementOfAllFilesPermissionGranted(this)) {
                    relaunchActivity()
                    return@Runnable
                }
            }
            is DeviceAccessibilityPermissionFragment -> {
                if (Util.checkAccessibilityPermissionGranted(this)) {
                    launchScreenAppActivity()
                    return@Runnable
                }
            }
            is DisplayOverAppsFragment -> {
                if (Util.displayOverOtherAppsGranted(this)) {
                    relaunchActivity()
                    return@Runnable
                }
            }
        }
        recursiveCallHandler()
    }

    private fun relaunchActivity() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    private fun recursiveCallHandler() {
        mHandler.postDelayed(mRunnable, 500)
    }

    override fun initViews() {
        batteryOptimizationFragment = PhoneBatteryOptimisationFragment.newInstance()
        deviceAccessibilityPermissionFragment =
            DeviceAccessibilityPermissionFragment.newInstance()
        deviceAdministratorFragment = DeviceAdministratorFragment.newInstance()
        displayOverAppsFragment = DisplayOverAppsFragment.newInstance()
        managementOfFilesFragment = ManagementOfFilesFragment.newInstance()
        requestAppPermissions()
    }

    override fun onGranted(type: Int) {
        when (type) {
            PhoneBatteryOptimisationFragment.REQUEST_BATTERY_OPTIMIZATION -> {
                loadDeviceAdminFragment()
            }
            DeviceAdministratorFragment.REQUEST_DEVICE_ADMIN_APP -> {
                loadAccessibilityAccessFragment()
            }
            DisplayOverAppsFragment.REQUEST_DISPLAY_OVER_APPS -> {
//                loadAccessibilityAccessFragment()
            }
            DeviceAccessibilityPermissionFragment.REQUEST_ACCESSIBILITY_PERMISSION -> {
                launchScreenAppActivity()
            }
        }
    }

    private fun launchScreenAppActivity() {
        startActivity(
            Intent(
                this,
                CallScreeningActivity::class.java
            ).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        )
        finish()
    }

    private fun requestAppPermissions() {
        // Permission has not been granted and must be requested.
        if (shouldShowRequestPermissionRationaleCompat(appPermissionsList)) {
            // Provide an additional rationale to the user if the permission was not granted
            // and the user would benefit from additional context for the use of the permission.
            // Display a SnackBar with a button to request the missing permission.
            layout.showSnackBar(
                "Please Grant All Permissions",
                Snackbar.LENGTH_INDEFINITE, "Ok"
            ) {
                permissionsResultLauncher.launch(appPermissionsList)
            }
        } else {
            // Request the permission. The result will be received in onRequestPermissionResult().
            permissionsResultLauncher.launch(appPermissionsList)
        }
    }

    private val permissionsResultLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            // Handle Permission granted/rejected
            var isPermissionDenied = false
            permissions.entries.forEachIndexed { _, entry ->
                val isGranted = entry.value
                if (!isGranted) {
                    isPermissionDenied = true
                }
            }
            if (!isPermissionDenied) {
                logVerbose("$TAG All permissions are granted")
            } else {
                logVerbose("$TAG Missing Permissions")
                requestAppPermissions()
            }
        }

    override fun onDestroy() {
        super.onDestroy()
        mHandler.removeCallbacks(mRunnable)
    }
}