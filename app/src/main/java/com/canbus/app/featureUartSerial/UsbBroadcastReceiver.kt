package com.canbus.app.featureUartSerial

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.canbus.app.featureUartSerial.presentation.UsbService

class UsbBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        when (intent.action) {
            UsbService.ACTION_USB_PERMISSION_GRANTED -> Toast.makeText(
                context,
                "USB Ready",
                Toast.LENGTH_SHORT
            ).show()
            UsbService.ACTION_USB_PERMISSION_NOT_GRANTED -> Toast.makeText(
                context,
                "USB Permission not granted",
                Toast.LENGTH_SHORT
            ).show()
            UsbService.ACTION_NO_USB -> Toast.makeText(
                context,
                "No USB connected",
                Toast.LENGTH_SHORT
            ).show()
            UsbService.ACTION_USB_DISCONNECTED -> Toast.makeText(
                context,
                "USB disconnected",
                Toast.LENGTH_SHORT
            ).show()
            UsbService.ACTION_USB_NOT_SUPPORTED -> Toast.makeText(
                context,
                "USB device not supported",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

}