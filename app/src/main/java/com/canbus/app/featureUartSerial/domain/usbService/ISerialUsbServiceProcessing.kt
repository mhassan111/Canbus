package com.canbus.app.featureUartSerial.domain.usbService

import android.content.Intent
import android.os.Handler

interface ISerialUsbServiceProcessing {
    fun onCreate()
    fun setHandler(mHandler : Handler)
    fun onStartCommand(intent: Intent?, flags: Int, startId: Int) : Int
    fun write(data : ByteArray)
    fun onDestroy()
}