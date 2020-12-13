package com.im.bin.ui

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import com.im.bin.MyApplication
import com.im.bin.R
import com.im.bin.appUtils.ActivityUtil
import com.im.bin.appUtils.Util
import com.im.bin.fragments.AccessibilityPermissionFragment
import com.im.bin.fragments.AppearOnTopFragment
import com.im.bin.fragments.BatterOptimizationFragment
import com.im.bin.interfaces.OnPermissionGranted
import com.im.bin.ui.base.BaseActivity

class MainActivity : BaseActivity(), OnPermissionGranted {

    private lateinit var mHandler: Handler
    private lateinit var batteryOptimizationFragment: BatterOptimizationFragment
    private lateinit var accessibilityPermissionFragment: AccessibilityPermissionFragment
    private lateinit var appearOnTopFragment: AppearOnTopFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpToolBar()
        setToolBarTitle("WhatsDeleted Store")
        initViews()
    }

    override fun onResume() {
        super.onResume()
        if (!Util.isAppIgnoringBatteryOptimization(this@MainActivity)) {
            loadBatteryOptimizationFragment()
        } else if (!Util.appearOnTopPermissionAllowed(this@MainActivity)) {
            loadAppearOnTopFragment()
        } else if (!Util.checkAccessibillityPermissionGranted(this@MainActivity)) {
            loadAccessibilityPermissionFragment()
        } else {
            ActivityUtil.launchHomeActivity(this)
            finish()
        }
    }

    override fun onAttachFragment(fragment: Fragment) {
        super.onAttachFragment(fragment)
        when (fragment) {
            is BatterOptimizationFragment -> {
                fragment.setOnPermissionGrantedListener(this)
            }
        }
    }

    private fun loadAccessibilityPermissionFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, accessibilityPermissionFragment).commit()
    }

    private fun loadBatteryOptimizationFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, batteryOptimizationFragment).commit()
    }

    private fun loadAppearOnTopFragment() {
        supportFragmentManager.beginTransaction().replace(R.id.container, appearOnTopFragment)
            .commit()
    }

    private val mRunnable = Runnable {
        when (supportFragmentManager.findFragmentById(R.id.container)) {
            appearOnTopFragment -> {
                if (Util.appearOnTopPermissionAllowed(this)) {
                    ActivityUtil.launchMainActivity(MyApplication.getContext())
                }
            }
            accessibilityPermissionFragment -> {
                if (Util.checkAccessibillityPermissionGranted(this)) {
                    ActivityUtil.launchHomeActivity(this)
                    finish()
                }
            }
        }
        recursiveCallHandler()
    }

    private fun recursiveCallHandler() {
        mHandler.postDelayed(mRunnable, 500)
    }

    // TODO: Initialize views
    override fun initViews() {
        mHandler = Handler()
        batteryOptimizationFragment = BatterOptimizationFragment.newInstance()
        appearOnTopFragment = AppearOnTopFragment.newInstance()
        accessibilityPermissionFragment =
            AccessibilityPermissionFragment.newInstance()
        mHandler.postDelayed(mRunnable, 500)
    }

    override fun onGranted(type: Int) {
        if (type == BatterOptimizationFragment.REQUEST_BATTERY_OPTIMIZATION) {
            loadAppearOnTopFragment()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mHandler.removeCallbacks(mRunnable)
    }
}