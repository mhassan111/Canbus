package com.screening.app.ui.fragments

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.screening.app.R
import com.screening.app.interfaces.OnPermissionGranted

class DisplayOverAppsFragment : Fragment() {

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
        return inflater.inflate(R.layout.display_over_apps_permission_fragment, container, false)
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        val grantPermissionBtn = view.findViewById<Button>(R.id.grant_notification_btn)
        grantPermissionBtn.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val intent = Intent(
                    Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:${requireActivity().packageName}")
                )
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                intent.flags = Intent.FLAG_ACTIVITY_NO_HISTORY
                intent.flags = Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS
                startActivity(intent)
            } else {

            }
        }
    }

    override fun onResume() {
        super.onResume()
//        if (callback != null && Util.isNotificationAccessEnabled(mContext))
//            callback!!.onGranted(REQUEST_NOTIFICATION_PERMISSION)
    }

    private val mRunnable = Runnable {

    }

    private fun recursiveCallHandler() {
        mHandler.postDelayed(mRunnable, 500)
    }

    override fun onDestroy() {
        super.onDestroy()
        mHandler.removeCallbacks(mRunnable)
    }

    companion object {
        private var TAG = DisplayOverAppsFragment::class.java.simpleName
        const val REQUEST_DISPLAY_OVER_APPS = 1003
        fun newInstance(): DisplayOverAppsFragment {
            val args = Bundle()
            val fragment = DisplayOverAppsFragment()
            fragment.arguments = args
            return fragment
        }
    }
}