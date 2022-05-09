package com.screening.app.ui.fragments

import android.annotation.TargetApi
import android.app.Activity
import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.screening.app.R
import com.screening.app.interfaces.OnPermissionGranted
import com.screening.app.receivers.ScreenAppAdminReceiver
import com.screening.app.utilities.Util

@Suppress("DEPRECATION")
class DeviceAdministratorFragment : Fragment() {

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
        return inflater.inflate(R.layout.device_admin_fragment, container, false)
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        val allowButton =
            view.findViewById<Button>(R.id.device_admin_request)
        allowButton.setOnClickListener { v: View? -> requestDeviceAdmin() }
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_DEVICE_ADMIN_APP && resultCode == Activity.RESULT_OK) {
            callback!!.onGranted(REQUEST_DEVICE_ADMIN_APP)
        } else if (requestCode == REQUEST_DEVICE_ADMIN_APP && resultCode == Activity.RESULT_CANCELED) {
            requestDeviceAdmin()
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    private fun requestDeviceAdmin() {
        try {
            if (!Util.checkDeviceAdminPermissionGranted(requireContext())) {
                val demoDeviceAdmin =
                    ComponentName(requireActivity(), ScreenAppAdminReceiver::class.java)
                val intent = Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN)
                intent.putExtra(
                    DevicePolicyManager.EXTRA_DEVICE_ADMIN,
                    demoDeviceAdmin
                )
                deviceAdminLaunch.launch(intent)
            }
        } catch (e: Exception) {
            e.message
        }
    }

    private val deviceAdminLaunch =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                callback!!.onGranted(REQUEST_DEVICE_ADMIN_APP)
            } else if (result.resultCode == Activity.RESULT_CANCELED) {
            }
        }

    companion object {
        const val REQUEST_DEVICE_ADMIN_APP = 2000
        val TAG = DeviceAdministratorFragment::class.java.simpleName
        fun newInstance(): DeviceAdministratorFragment {
            val args = Bundle()
            val fragment = DeviceAdministratorFragment()
            fragment.arguments = args
            return fragment
        }
    }
}