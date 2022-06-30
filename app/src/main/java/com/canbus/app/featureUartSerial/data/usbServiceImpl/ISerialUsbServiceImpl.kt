package com.canbus.app.featureUartSerial.data.usbServiceImpl

import android.app.PendingIntent
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.hardware.usb.UsbManager
import android.os.Handler
import com.canbus.app.featureUartSerial.domain.usbService.AbstractISerialUsbService
import com.canbus.app.featureUartSerial.presentation.UsbService
import com.canbus.app.utilities.SharedPrefs
import com.canbus.app.utilities.logVerbose
import com.felhr.usbserial.CDCSerialDevice
import com.felhr.usbserial.UsbSerialDevice
import com.felhr.usbserial.UsbSerialInterface
import java.io.UnsupportedEncodingException
import java.nio.charset.StandardCharsets

class ISerialUsbServiceImpl(val service: Service) : AbstractISerialUsbService(service) {

    override fun onCreate() {
        context = service.applicationContext
        usbManager = service.getSystemService(Context.USB_SERVICE) as UsbManager
        setUsbBroadcastReceiver()
        serialPortConnected = false
        SERVICE_CONNECTED = true
        findSerialPortDevice()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return Service.START_NOT_STICKY
    }

    override fun setHandler(mHandler: Handler) {
        this.mHandler = mHandler
    }

    override fun onDestroy() {
        if (serialPort != null) {
            serialPort!!.close()
        }
        service.unregisterReceiver(usbReceiver)
        SERVICE_CONNECTED = false
    }

    override fun write(data: ByteArray) {
        if (serialPort != null) {
            serialPort!!.write(data)
        }
    }

    private fun setUsbBroadcastReceiver() {
        val intentFilter = IntentFilter()
        intentFilter.addAction(ACTION_USB_PERMISSION)
        intentFilter.addAction(ACTION_USB_DETACHED)
        intentFilter.addAction(ACTION_USB_ATTACHED)
        service.registerReceiver(usbReceiver, intentFilter)
    }

    private val usbReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            intent?.let {
                when (it.action) {
                    ACTION_USB_PERMISSION -> {
                        val granted =
                            it.extras?.getBoolean(UsbManager.EXTRA_PERMISSION_GRANTED) ?: false
                        if (granted) {
                            context!!.sendBroadcast(Intent(ACTION_USB_PERMISSION_GRANTED))
                            connection = usbManager.openDevice(device)
                            ConnectionThread().start()
                        } else {
                            context!!.sendBroadcast(Intent(ACTION_USB_PERMISSION_NOT_GRANTED))
                        }
                    }
                    ACTION_USB_ATTACHED -> {
                        if (!serialPortConnected) findSerialPortDevice()
                    }
                    ACTION_USB_DETACHED -> {
                        service.sendBroadcast(Intent(UsbService.ACTION_USB_DISCONNECTED))
                        if (serialPortConnected) {
                            serialPort!!.close()
                        }
                        serialPortConnected = false
                    }
                }
            }
        }
    }

    private fun findSerialPortDevice() {
        val usbDevices = usbManager.deviceList
        if (usbDevices.isNotEmpty()) {
            usbDevices.forEach { usbDevice ->
                device = usbDevice.value
                device?.let {
                    logVerbose(
                        String.format(
                            "$TAG USBDevice.HashMap (vid:pid) (%X:%X)-%b class:%X:%X name:%s",
                            it.vendorId, it.productId,
                            UsbSerialDevice.isSupported(it),
                            it.deviceClass, it.deviceSubclass,
                            it.deviceName
                        )
                    )
                }
            }

            usbDevices.forEach { usbDevice ->
                device = usbDevice.value
                device?.let {
                    val deviceVendorId = it.vendorId
                    val deviceProductId = it.productId
                    logVerbose("$TAG device vendor Id = $deviceVendorId")
                    logVerbose("$TAG device product Id = $deviceProductId")

                    if (UsbSerialDevice.isSupported(it)) {
                        requestUsbPermissionFromUser()
                        return@forEach
                    } else {
                        connection = null
                        device = null
                    }
                }
            }

            if (device == null) {
                service.sendBroadcast(Intent(ACTION_NO_USB))
            }
        } else {
            logVerbose("$TAG findSerialPortDevice() usbManager returned empty device list")
            service.sendBroadcast(Intent(ACTION_NO_USB))
        }
    }

    private fun requestUsbPermissionFromUser() {
        logVerbose(
            String.format(
                "$TAG requestUsbPermissionFromUser(%X:%X)",
                device!!.vendorId,
                device!!.productId
            )
        )

        val mPendingIntent =
            PendingIntent.getBroadcast(service, 0, Intent(ACTION_USB_PERMISSION), 0)
        usbManager.requestPermission(device, mPendingIntent)
    }

    private inner class ConnectionThread : Thread() {
        override fun run() {
            serialPort = UsbSerialDevice.createUsbSerialDevice(device, connection)
            if (serialPort != null) {
                if (serialPort!!.open()) {
                    serialPortConnected = true
                    serialPort!!.setBaudRate(SharedPrefs.baudRate?.toInt() ?: BAUD_RATE)
                    serialPort!!.setDataBits(UsbSerialInterface.DATA_BITS_8)
                    serialPort!!.setStopBits(UsbSerialInterface.STOP_BITS_1)
                    serialPort!!.setParity(UsbSerialInterface.PARITY_NONE)
                    serialPort!!.setFlowControl(UsbSerialInterface.FLOW_CONTROL_OFF)
                    serialPort!!.read(mUsbReadCallback)
                    serialPort!!.getCTS(usbCTSCallback)
                    serialPort!!.getDSR(usbDSRCallback)

                    val intent = Intent(UsbService.ACTION_USB_READY)
                    context.sendBroadcast(intent)
                } else {
                    if (serialPort is CDCSerialDevice) {
                        val intent = Intent(UsbService.ACTION_CDC_DRIVER_NOT_WORKING)
                        context.sendBroadcast(intent)
                    } else {
                        val intent = Intent(UsbService.ACTION_USB_DEVICE_NOT_WORKING)
                        context.sendBroadcast(intent)
                    }
                }
            } else {
                val intent = Intent(UsbService.ACTION_USB_NOT_SUPPORTED)
                context.sendBroadcast(intent)
            }
        }
    }

    private val mUsbReadCallback = UsbSerialInterface.UsbReadCallback { arg0 ->
        try {
            val data = String(arg0, StandardCharsets.UTF_8)
            mHandler.obtainMessage(UsbService.MESSAGE_FROM_SERIAL_PORT, data).sendToTarget()
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }
    }

    private val usbCTSCallback = UsbSerialInterface.UsbCTSCallback {
        mHandler.obtainMessage(UsbService.CTS_CHANGE).sendToTarget()
    }

    private val usbDSRCallback = UsbSerialInterface.UsbDSRCallback {
        mHandler.obtainMessage(UsbService.DSR_CHANGE).sendToTarget()
    }
}