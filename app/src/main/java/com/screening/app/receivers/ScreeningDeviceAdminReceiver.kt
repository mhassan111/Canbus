package com.screening.app.receivers

import android.app.admin.DeviceAdminReceiver
import android.content.Context
import android.content.Intent
import com.screening.app.utilities.logVerbose

/**
 * This is the component that is responsible for actual device administration.
 * It becomes the receiver when a policy is applied. It is important that we
 * subclass DeviceAdminReceiver class here and to implement its only required
 * method onEnabled().
 */
class ScreeningDeviceAdminReceiver : DeviceAdminReceiver() {

    /**
     * Called when this application is approved to be a device administrator.
     */
    override fun onEnabled(context: Context, intent: Intent) {
        super.onEnabled(context, intent)
        logVerbose(TAG, "onEnabled")
    }

    /**
     * Called when this application is no longer the device administrator.
     */
    override fun onDisabled(context: Context, intent: Intent) {
        super.onDisabled(context, intent)
        logVerbose(TAG, "onDisabled")
    }

    override fun onLockTaskModeEntering(context: Context, intent: Intent, pkg: String) {
        super.onLockTaskModeEntering(context, intent, pkg)
        logVerbose(TAG, "onLockTaskEntering")
    }

    override fun onPasswordChanged(context: Context, intent: Intent) {
        super.onPasswordChanged(context, intent)
        logVerbose(TAG, "onPasswordChanged")
    }

    override fun onPasswordFailed(context: Context, intent: Intent) {
        super.onPasswordFailed(context, intent)
        logVerbose(TAG, "onPasswordFailed")
    }

    override fun onPasswordSucceeded(context: Context, intent: Intent) {
        super.onPasswordSucceeded(context, intent)
        logVerbose(TAG, "onPasswordSucceeded")
    }

    companion object {
        private const val TAG = "TOSDeviceAdminReceiver"
    }
}