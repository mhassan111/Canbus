package com.im.bin.fragments

import android.annotation.TargetApi
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.PowerManager
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.im.bin.R
import com.im.bin.interfaces.OnPermissionGranted

class BatterOptimizationFragment : Fragment() {

    private var mContext: Context? = null
    private var callback: OnPermissionGranted? = null

    fun setOnPermissionGrantedListener(callback: OnPermissionGranted?) {
        this.callback = callback
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = activity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_battery_optimization, container, false)
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        val allowButton =
            view.findViewById<Button>(R.id.allow_battery_request)
        allowButton.setOnClickListener { v: View? -> requestBatteryOptimization() }
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_BATTERY_OPTIMIZATION && resultCode == Activity.RESULT_OK) {
            callback!!.onGranted(REQUEST_BATTERY_OPTIMIZATION)
        } else if (requestCode == REQUEST_BATTERY_OPTIMIZATION && resultCode == Activity.RESULT_CANCELED) {
            requestBatteryOptimization()
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    private fun requestBatteryOptimization() {
        try {
            val packageName = mContext!!.packageName
            val powerManager =
                mContext!!.getSystemService(Context.POWER_SERVICE) as PowerManager
            if (powerManager != null && !powerManager.isIgnoringBatteryOptimizations(packageName)) {
                val intent = Intent()
                intent.action = Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS
                intent.data = Uri.parse("package:$packageName")
                startActivityForResult(
                    intent,
                    REQUEST_BATTERY_OPTIMIZATION
                )
            }
        } catch (e: Exception) {
            e.message
        }
    }

    companion object {
        const val REQUEST_BATTERY_OPTIMIZATION = 1000
        val TAG = BatterOptimizationFragment::class.java.simpleName
        fun newInstance(): BatterOptimizationFragment {
            val args = Bundle()
            val fragment = BatterOptimizationFragment()
            fragment.arguments = args
            return fragment
        }
    }
}