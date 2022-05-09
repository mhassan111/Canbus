package com.screening.app.ui.fragments

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.screening.app.R
import com.screening.app.interfaces.OnPermissionGranted
import com.screening.app.utilities.Util

class ManagementOfFilesFragment : Fragment() {

    private var callback: OnPermissionGranted? = null
    private lateinit var mContext: Context

    private val mHandler by lazy {
        Handler(Looper.getMainLooper())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = requireContext()
    }

    fun setOnPermissionGrantedListener(callback: OnPermissionGranted?) {
        this.callback = callback
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.management_of_all_files_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val grantPermissionBtn = view.findViewById<Button>(R.id.grant_notification_btn)
        grantPermissionBtn.setOnClickListener {
            requestAllMediaPermission()
        }
    }

    private fun requestAllMediaPermission() {
        try {
            val intent = Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION)
            intent.addCategory("android.intent.category.DEFAULT")
            intent.data = Uri.parse(String.format("package:%s", requireActivity().packageName))
            accessAllMediaLaunch.launch(intent)
        } catch (e: Exception) {
            val intent = Intent()
            intent.action = Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION
            accessAllMediaLaunch.launch(intent)
        }
    }

    private val accessAllMediaLaunch =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
//            if (AppUtils.isManagementOfAllFilesPermissionGranted(requireContext())) {
//                logVerbose("${ManualPermissionActivity.TAG} accessAllMedia Permission Granted")
//                manualPermissionViewModel.setPermissionScreen(requireContext(), PermissionScreens.DEVICE_ADMIN_PERMISSION)
//            } else {
//                logVerbose("${ManualPermissionActivity.TAG} accessAllMediaLaunch Permission Cancelled")
//            }
        }

    override fun onResume() {
        super.onResume()
        if (callback != null && Util.isManagementOfAllFilesPermissionGranted(mContext))
            callback!!.onGranted(REQUEST_MANAGEMENT_OF_ALL_FILES_PERMISSIONS)
    }

    private val mRunnable = Runnable {
        if (Util.isManagementOfAllFilesPermissionGranted(requireContext())) {
            callback!!.onGranted(REQUEST_MANAGEMENT_OF_ALL_FILES_PERMISSIONS)
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
        private var TAG = ManagementOfFilesFragment::class.java.simpleName
        const val REQUEST_MANAGEMENT_OF_ALL_FILES_PERMISSIONS = 1004
        fun newInstance(): ManagementOfFilesFragment {
            val args = Bundle()
            val fragment = ManagementOfFilesFragment()
            fragment.arguments = args
            return fragment
        }
    }
}