package com.im.bin.fragments

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
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.im.bin.R
import com.im.bin.appUtils.Util
import com.im.bin.interfaces.OnPermissionGranted


class AppearOnTopFragment : Fragment() {

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
        return inflater.inflate(R.layout.fragment_appear_on_top, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        val grantPermissionBtn = view.findViewById<Button>(R.id.allow_appear_on_top)
        grantPermissionBtn.setOnClickListener {
            val intent = Intent(
                Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                Uri.parse("package:" + mContext.packageName)
            )
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent.flags = Intent.FLAG_ACTIVITY_NO_HISTORY
            intent.flags = Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        if (callback != null && Util.appearOnTopPermissionAllowed(mContext)
        ) callback!!.onGranted(REQUEST_APPEAR_ON_TOP)
    }

    private val mRunnable = Runnable {
        if (Util.appearOnTopPermissionAllowed(requireContext())) {
            callback!!.onGranted(REQUEST_APPEAR_ON_TOP)
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
        var TAG = AppearOnTopFragment::class.java.simpleName
        const val REQUEST_APPEAR_ON_TOP = 1001
        fun newInstance(): AppearOnTopFragment {
            val args = Bundle()
            val fragment = AppearOnTopFragment()
            fragment.arguments = args
            return fragment
        }
    }
}