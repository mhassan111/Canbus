package com.screening.app.ui.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.screening.app.R
import com.screening.app.interfaces.OnPermissionGranted
import com.screening.app.utilities.Util.checkAccessibilityPermissionGranted

class DeviceAccessibilityPermissionFragment : Fragment() {

    private var callback: OnPermissionGranted? = null
    private lateinit var mContext: Context
    private val mHandler: Handler by lazy {
        Handler(Looper.getMainLooper())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = requireContext()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.accessibility_permission_fragment, container, false)
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        val grantPermissionBtn = view.findViewById<Button>(R.id.grant_accessibility_btn)
        grantPermissionBtn.setOnClickListener {
            val intent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
            intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        if (callback != null && checkAccessibilityPermissionGranted(mContext))
            callback!!.onGranted(REQUEST_ACCESSIBILITY_PERMISSION)
    }

    private val mRunnable = Runnable {
        if (checkAccessibilityPermissionGranted(requireContext())) {
            callback!!.onGranted(REQUEST_ACCESSIBILITY_PERMISSION)
        }
        recursiveCallHandler()
    }

    private fun recursiveCallHandler() {
        mHandler.postDelayed(mRunnable, 500)
    }

    override fun onDestroy() {
        super.onDestroy()
        mHandler.removeCallbacks(mRunnable)
    }

    companion object {
        var TAG = DeviceAccessibilityPermissionFragment::class.java.simpleName
        const val REQUEST_ACCESSIBILITY_PERMISSION = 1002
        fun newInstance(): DeviceAccessibilityPermissionFragment {
            val args = Bundle()
            val fragment = DeviceAccessibilityPermissionFragment()
            fragment.arguments = args
            return fragment
        }
    }
}