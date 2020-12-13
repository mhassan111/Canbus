package com.im.bin.ui.auth

import android.Manifest
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import com.google.firebase.auth.AuthResult
import com.im.bin.R
import com.im.bin.appUtils.ActivityUtil
import com.im.bin.appUtils.Constants
import com.im.bin.appUtils.SharedPreferencesUtil
import com.im.bin.appUtils.Util
import com.im.bin.databinding.LoginActivityBinding
import com.im.bin.models.User
import com.im.bin.ui.LockScreenActivity
import com.im.bin.ui.base.BaseAuthActivity
import com.im.bin.viewModel.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*
import timber.log.Timber
import javax.inject.Inject

class LoginActivity : BaseAuthActivity() {

//    companion object {
//        private const val REQUEST_PERMISSION_CODE = 1000
//
//        @RequiresApi(Build.VERSION_CODES.M)
//        private val permissionsArray = arrayOf(
//            Manifest.permission.INTERNET,
//            Manifest.permission.RECEIVE_BOOT_COMPLETED,
//            Manifest.permission.ACCESS_WIFI_STATE,
//            Manifest.permission.ACCESS_NETWORK_STATE,
//            Manifest.permission.READ_EXTERNAL_STORAGE,
//            Manifest.permission.WRITE_EXTERNAL_STORAGE,
//            Manifest.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS,
//            Manifest.permission.SYSTEM_ALERT_WINDOW,
//            Manifest.permission.RECORD_AUDIO
//        )
//    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val loginViewModel: LoginViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        transparentStatusBar()
        initViews()
    }

    override fun initViews() {
        val binding: LoginActivityBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.lifecycleOwner = this
        binding.viewModel = loginViewModel

        if (PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean(getString(R.string.key_launch_app_lock), false)
            && !TextUtils.isEmpty(
                SharedPreferencesUtil(this).getPreferenceValue(
                    Constants.PREF_PIN_CODE
                )
            )
        ) {
            ActivityUtil.launchLockScreenActivity(
                this,
                LockScreenActivity.TYPE_APP_LAUNCH_PROTECTION
            )
            finish()
            return
        }

        val currentUser = loginViewModel.getCurrentUser()
        currentUser?.let {
            if (Util.isAppIgnoringBatteryOptimization(this) && Util.checkAccessibillityPermissionGranted(
                    this
                )
                && Util.appearOnTopPermissionAllowed(this)
            ) {
                ActivityUtil.launchHomeActivity(this)
            } else {
                ActivityUtil.launchMainActivity(this)
            }
            finish()
            return
        }

        loginViewModel.getAuthState().observe(this, Observer {
            setAuthState(it)
        })

        loginViewModel.getUser().observe(this, Observer {
            signIn(it)
        })

        signUp.setOnClickListener {
            ActivityUtil.launchSignUpActivity(this)
        }
//        requestRunTimePermissions()
    }

    private fun setAuthState(authState: AuthState) {
        when (authState) {
            is Loading -> onLoading()
            is AuthError -> onAuthError(authState.message)
            is OnAuthSuccess -> onAuthSuccess(authState.authResult)
        }
    }

    private fun onLoading() {
        showProgressBar()
    }

    private fun signIn(user: User) {
        val emailText = user.email
        val passwordText = user.password
        when {
            emailText.isEmpty() -> {
                email.error = "Please Enter Email"
                return
            }
            !Util.checkEmailAddressValid(emailText) -> {
                email.error = "Please Enter a Valid Email"
                return
            }
            passwordText.isEmpty() -> {
                password.error = "Please enter a Password"
                return
            }
            else -> {
                loginViewModel.login(user)
            }
        }
    }

    private fun onAuthSuccess(authResult: AuthResult) {
        hideProgressBar()
        SharedPreferencesUtil(this).setBoolPref(Constants.PREF_USER_LOGGED_IN, true)
        if (Util.isAppIgnoringBatteryOptimization(this) && Util.checkAccessibillityPermissionGranted(
                this
            ) && Util.appearOnTopPermissionAllowed(this)
        ) {
            ActivityUtil.launchHomeActivity(this)
        } else {
            ActivityUtil.launchMainActivity(this)
        }
        finish()
    }

    private fun onAuthError(error: String?) {
        hideProgressBar()
        Util.showCustomAlertDialog(this, "Error", error!!)
    }

    private fun showProgressBar() {
        loginViewModel.setProgressBarState(true)
        setWindowNotTouchable()
    }

    private fun hideProgressBar() {
        loginViewModel.setProgressBarState(false)
        clearWindowTouchableFlag()
    }
//
//    private fun requestRunTimePermissions() {
//        if (Util.osVersionGreaterThanLollipop()) {
//            if (checkSelfPermission(permissionsArray[0]) != PackageManager.PERMISSION_GRANTED || checkSelfPermission(
//                    permissionsArray[1]
//                ) != PackageManager.PERMISSION_GRANTED || checkSelfPermission(permissionsArray[2]) != PackageManager.PERMISSION_GRANTED || checkSelfPermission(
//                    permissionsArray[3]
//                ) != PackageManager.PERMISSION_GRANTED || checkSelfPermission(permissionsArray[4]) != PackageManager.PERMISSION_GRANTED || checkSelfPermission(
//                    permissionsArray[5]
//                ) != PackageManager.PERMISSION_GRANTED || checkSelfPermission(
//                    permissionsArray[6]
//                ) != PackageManager.PERMISSION_GRANTED || checkSelfPermission(
//                    permissionsArray[7]
//                ) != PackageManager.PERMISSION_GRANTED || checkSelfPermission(
//                    permissionsArray[8]
//                ) != PackageManager.PERMISSION_GRANTED
//            ) {
//                if (ActivityCompat.shouldShowRequestPermissionRationale(
//                        this@LoginActivity,
//                        permissionsArray[0]
//                    )
//                    || ActivityCompat.shouldShowRequestPermissionRationale(
//                        this@LoginActivity,
//                        permissionsArray[1]
//                    )
//                    || ActivityCompat.shouldShowRequestPermissionRationale(
//                        this@LoginActivity,
//                        permissionsArray[2]
//                    )
//                    || ActivityCompat.shouldShowRequestPermissionRationale(
//                        this@LoginActivity,
//                        permissionsArray[3]
//                    )
//                    || ActivityCompat.shouldShowRequestPermissionRationale(
//                        this@LoginActivity,
//                        permissionsArray[4]
//                    )
//                    || ActivityCompat.shouldShowRequestPermissionRationale(
//                        this@LoginActivity,
//                        permissionsArray[5]
//                    )
//                    || ActivityCompat.shouldShowRequestPermissionRationale(
//                        this@LoginActivity,
//                        permissionsArray[6]
//                    )
//                    || ActivityCompat.shouldShowRequestPermissionRationale(
//                        this@LoginActivity,
//                        permissionsArray[7]
//                    )
//                    || ActivityCompat.shouldShowRequestPermissionRationale(
//                        this@LoginActivity,
//                        permissionsArray[8]
//                    )
//                ) {
//                    displayPermissionRequestDialog()
//                } else {
//                    ActivityCompat.requestPermissions(
//                        this@LoginActivity,
//                        permissionsArray
//                        , REQUEST_PERMISSION_CODE
//                    )
//                }
//            }
//        }
//    }
//
//    override fun checkSelfPermission(permission: String): Int {
//        return ActivityCompat.checkSelfPermission(this@LoginActivity, permission)
//    }
//
//    @RequiresApi(Build.VERSION_CODES.M)
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<String?>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        when (requestCode) {
//            REQUEST_PERMISSION_CODE -> {
//                var allGranted = false
//                for (result in grantResults) {
//                    if (result == PackageManager.PERMISSION_GRANTED) {
//                        allGranted = true
//                    } else {
//                        allGranted = false
//                        break
//                    }
//                }
//                if (allGranted) {
//                    Timber.d("All Permission Granted.")
//                } else if (ActivityCompat.shouldShowRequestPermissionRationale(
//                        this@LoginActivity,
//                        permissionsArray[0]
//                    )
//                    || ActivityCompat.shouldShowRequestPermissionRationale(
//                        this@LoginActivity,
//                        permissionsArray[1]
//                    )
//                    || ActivityCompat.shouldShowRequestPermissionRationale(
//                        this@LoginActivity,
//                        permissionsArray[2]
//                    )
//                    || ActivityCompat.shouldShowRequestPermissionRationale(
//                        this@LoginActivity,
//                        permissionsArray[3]
//                    )
//                    || ActivityCompat.shouldShowRequestPermissionRationale(
//                        this@LoginActivity,
//                        permissionsArray[4]
//                    )
//                    || ActivityCompat.shouldShowRequestPermissionRationale(
//                        this@LoginActivity,
//                        permissionsArray[5]
//                    )
//                    || ActivityCompat.shouldShowRequestPermissionRationale(
//                        this@LoginActivity,
//                        permissionsArray[6]
//                    )
//                    || ActivityCompat.shouldShowRequestPermissionRationale(
//                        this@LoginActivity,
//                        permissionsArray[7]
//                    )
//                    || ActivityCompat.shouldShowRequestPermissionRationale(
//                        this@LoginActivity,
//                        permissionsArray[8]
//                    )
//                ) {
//                    displayPermissionRequestDialog()
//                }
//            }
//        }
//    }
//
//    @RequiresApi(Build.VERSION_CODES.M)
//    private fun displayPermissionRequestDialog() {
//        val builder =
//            AlertDialog.Builder(this@LoginActivity, R.style.AlertDialogCustom)
//        builder.setTitle("Need Permissions")
//        builder.setMessage("App need Multiple permissions. Please Grant.")
//        builder.setPositiveButton(
//            "Grant"
//        ) { dialog, _ ->
//            dialog.cancel()
//            ActivityCompat.requestPermissions(
//                this@LoginActivity,
//                permissionsArray,
//                REQUEST_PERMISSION_CODE
//            )
//        }
//        builder.setNegativeButton(
//            "Cancel"
//        ) { dialog, _ -> dialog.cancel() }
//        builder.setCancelable(false)
//        builder.show()
//    }
}
