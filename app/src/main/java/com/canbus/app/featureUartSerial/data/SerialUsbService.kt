package com.canbus.app.featureUartSerial.data

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.Handler
import android.os.IBinder
import com.canbus.app.featureUartSerial.data.usbServiceImpl.ISerialUsbServiceImpl
import com.canbus.app.featureUartSerial.domain.usbService.ISerialUsbServiceProcessing

class SerialUsbService : Service() {

    lateinit var mISerialUsbServiceProcessing: ISerialUsbServiceProcessing
    override fun onCreate() {
        super.onCreate()
        mISerialUsbServiceProcessing = ISerialUsbServiceImpl(this)
        mISerialUsbServiceProcessing.onCreate()
    }

    fun setHandler(mHandler: Handler) {
        mISerialUsbServiceProcessing.setHandler(mHandler)
    }

    fun write(data: ByteArray) {
        mISerialUsbServiceProcessing.write(data)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return mISerialUsbServiceProcessing.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return SerialUsbBinder()
    }

    override fun onDestroy() {
        super.onDestroy()
        mISerialUsbServiceProcessing.onDestroy()
    }

    inner class SerialUsbBinder : Binder() {
        val service: SerialUsbService
            get() = this@SerialUsbService
    }
}