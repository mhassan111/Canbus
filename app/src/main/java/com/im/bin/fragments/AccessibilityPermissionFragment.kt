package com.im.bin.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.im.bin.R
import com.im.bin.appUtils.Util.checkAccessibillityPermissionGranted
import com.im.bin.interfaces.OnPermissionGranted

class AccessibilityPermissionFragment : Fragment() {

    private var callback: OnPermissionGranted? = null
    private lateinit var mContext: Context
    private lateinit var mHandler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = requireContext()
        mHandler = Handler()
    }

    fun setOnPermissionGrantedListener(callback: OnPermissionGranted?) {
        this.callback = callback
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_accessibility_permission, container, false)
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
        if (callback != null && checkAccessibillityPermissionGranted(
                mContext
            )
        ) callback!!.onGranted(REQUEST_ACCESSIBILITY_PERMISSION)
    }

    private val mRunnable = Runnable {
        if (checkAccessibillityPermissionGranted(requireContext())) {
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
        var TAG = AccessibilityPermissionFragment::class.java.simpleName
        const val REQUEST_ACCESSIBILITY_PERMISSION = 1002
        fun newInstance(): AccessibilityPermissionFragment {
            val args = Bundle()
            val fragment = AccessibilityPermissionFragment()
            fragment.arguments = args
            return fragment
        }
    }
}