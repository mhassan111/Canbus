package com.im.bin.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import com.im.bin.services.ForegroundService

class NotificationBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let { mContext ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val notificationIntent =
                    Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS)
                        .putExtra(Settings.EXTRA_APP_PACKAGE, mContext.packageName)
                        .putExtra(Settings.EXTRA_CHANNEL_ID, ForegroundService.CHANNEL_ID)
                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                mContext.startActivity(notificationIntent)
            } else {
                val notificationIntent = Intent()
                notificationIntent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                notificationIntent.addCategory(Intent.CATEGORY_DEFAULT)
                notificationIntent.data = Uri.parse("package:" + mContext.packageName)
                notificationIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                mContext.startActivity(notificationIntent)
            }
        }
    }
}