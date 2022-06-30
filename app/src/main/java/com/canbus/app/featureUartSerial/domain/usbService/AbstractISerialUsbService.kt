package com.canbus.app.featureUartSerial.domain.usbService

import android.app.Service
import android.content.Context
import android.hardware.usb.UsbDevice
import android.hardware.usb.UsbDeviceConnection
import android.hardware.usb.UsbManager
import android.os.Handler
import com.felhr.usbserial.UsbSerialDevice

abstract class AbstractISerialUsbService(service: Service) : ISerialUsbServiceProcessing {

    lateinit var context: Context
    lateinit var mHandler: Handler
    lateinit var usbManager: UsbManager
    protected var device: UsbDevice? = null
    protected var connection: UsbDeviceConnection? = null
    protected var serialPort: UsbSerialDevice? = null
    protected var serialPortConnected = false

    companion object {

        const val TAG = "SerialUsbService"
        const val ACTION_USB_PERMISSION = "com.android.example.USB_PERMISSION"
        const val BAUD_RATE = 9600
        var SERVICE_CONNECTED = false

        const val ACTION_USB_ATTACHED = "android.hardware.usb.action.USB_DEVICE_ATTACHED"
        const val ACTION_USB_DETACHED = "android.hardware.usb.action.USB_DEVICE_DETACHED"
        const val ACTION_NO_USB = "com.felhr.usbservice.NO_USB"
        const val ACTION_USB_PERMISSION_GRANTED = "com.felhr.usbservice.USB_PERMISSION_GRANTED"
        const val ACTION_USB_PERMISSION_NOT_GRANTED = "com.felhr.usbservice.USB_PERMISSION_NOT_GRANTED"
    }
}