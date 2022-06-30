package com.canbus.app.featureUartSerial

import android.annotation.SuppressLint
import android.content.*
import android.os.*
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Upload
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.canbus.app.featureUartSerial.presentation.*
import com.canbus.app.featureUartSerial.presentation.components.BaudRateDialog
import com.canbus.app.featureUartSerial.presentation.components.SendCanDataDialog
import com.canbus.app.ui.spacing
import com.canbus.app.ui.theme.CanbusAppTheme
import com.canbus.app.utilities.SharedPrefs
import com.canbus.app.utilities.logDebug
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import java.lang.ref.WeakReference

class UsbMainActivity : ComponentActivity() {

    private val mUsbReceiver: BroadcastReceiver = object : BroadcastReceiver() {
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

    private var usbService: UsbService? = null
    private var display: TextView? = null
    private var editText: EditText? = null
    private var mHandler: MyHandler? = null
    lateinit var viewModel: UartCommunicationViewModel

    private val usbConnection: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(arg0: ComponentName, arg1: IBinder) {
            usbService = (arg1 as UsbService.UsbBinder).service
            usbService!!.setHandler(mHandler)
        }

        override fun onServiceDisconnected(arg0: ComponentName) {
            usbService = null
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CanbusAppTheme {
                mHandler = MyHandler(this@UsbMainActivity)
                viewModel = viewModel<UartCommunicationViewModel>()
                MainComposable(viewModel)
            }
        }

//        setContentView(R.layout.activity_main)
//        mHandler = MyHandler(this@UsbMainActivity)
//        display = findViewById<View>(R.id.textView1) as TextView
//        editText = findViewById<View>(R.id.editText1) as EditText
//        val sendButton = findViewById<View>(R.id.buttonSend) as Button
//
//        sendButton.setOnClickListener { v: View? ->
//            if (editText!!.text.toString() != "") {
//                val data = editText!!.text.toString()
//                if (usbService != null) { // if UsbService was correctly binded, Send data
//                    usbService!!.write(data.toByteArray())
//                } else {
//                    Toast.makeText(this@UsbMainActivity, "Usb Service is null", Toast.LENGTH_LONG)
//                        .show()
//                }
//            }
//        }
    }

    @OptIn(ExperimentalComposeUiApi::class)
    @Composable
    private fun MainComposable(
        viewModel: UartCommunicationViewModel
    ) {

        // system ui controller
        val systemUiController = rememberSystemUiController()

        // scaffold state
        val scaffoldState = rememberScaffoldState()

        // context
        val context = LocalContext.current

        // lifeCycle Owner
        val lifeCycleOwner = LocalLifecycleOwner.current

        // Keyboard Controller
        val keyboardController = LocalSoftwareKeyboardController.current

//        val scrollableText by remember { mutableStateOf(StringBuilder("")) }
//        val showMessageSenderSection by remember { mutableStateOf(false) }
//        val scrollState = rememberScrollState(0)

        // send message state
        val messageState = viewModel.messageState

        // uart communication state
        val uartSerialCommunicationState = viewModel.uartSerialCommunicationState.value

        var mDisplayMenu by remember { mutableStateOf(false) }
        var sendCanDataDialog by remember { mutableStateOf(false) }
        var showBaudRateDialog by remember { mutableStateOf(false) }
        var baudRate by remember { mutableStateOf(SharedPrefs.baudRate ?: "9600") }
        var targetValue1 by remember { mutableStateOf(0.40f) }
        var targetValue2 by remember { mutableStateOf(0f) }

        val statusBarColor = MaterialTheme.colors.primary
        SideEffect {
            systemUiController.setStatusBarColor(color = statusBarColor)
        }

        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            "Canbus",
                            style = MaterialTheme.typography.h3,
                            color = Color.White
                        )
                    },
                    backgroundColor = MaterialTheme.colors.primary,
                    actions = {
                        DropdownMenu(
                            expanded = mDisplayMenu,
                            onDismissRequest = { mDisplayMenu = false }
                        ) {
                            DropdownMenuItem(onClick = {
                                mDisplayMenu = false
                                showBaudRateDialog = true
                            }) {
                                Row(
                                    modifier = Modifier.wrapContentHeight(),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Settings,
                                        contentDescription = "Set BaudRate",
                                        tint = MaterialTheme.colors.primary
                                    )
                                    Spacer(modifier = Modifier.width(MaterialTheme.spacing.xDp))
                                    Text(text = "Set BaudRate")
                                }
                            }

                            DropdownMenuItem(onClick = {
                                mDisplayMenu = false
                                sendCanDataDialog = true
                            }) {
                                Row(
                                    modifier = Modifier.wrapContentHeight(),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Upload,
                                        contentDescription = "Send CanData",
                                        tint = MaterialTheme.colors.primary
                                    )
                                    Spacer(modifier = Modifier.width(MaterialTheme.spacing.xDp))
                                    Text(text = "Send CanData")
                                }
                            }
                        }

                        IconButton(onClick = { mDisplayMenu = !mDisplayMenu }) {
                            Icon(
                                imageVector = Icons.Default.MoreVert,
                                contentDescription = "More",
                                tint = Color.White
                            )
                        }

                    }
                )
            }
        ) { paddingValues ->

            if (showBaudRateDialog) {
                BaudRateDialog(
                    modifier = Modifier.fillMaxWidth(),
                    baudRate,
                    onYes = { rate ->
                        baudRate = rate
                        SharedPrefs.baudRate = baudRate
                        showBaudRateDialog = false

                        // unbind usb services
                        context.unregisterReceiver(mUsbReceiver)
                        context.unbindService(usbConnection)
                        
                        // start usb service
                        setFilters()
                        startService(UsbService::class.java, usbConnection, null)
                    },
                    onCancel = { showBaudRateDialog = false }) {
                    showBaudRateDialog = false
                }
            }

            if (sendCanDataDialog) {
                SendCanDataDialog(
                    modifier = Modifier.fillMaxSize(),
                    messageState = messageState,
                    text = "Send CanData Message",
                    onMessageValueChange = {
                        viewModel.onEvent(UartCommunicationEvent.EnteredUartMessage(it))
                    },
                    onMessageFocusStateChange = {
                        viewModel.onEvent(UartCommunicationEvent.ChangeUartMessageFocus(it))
                    },
                    onYes = {
                        if (usbService != null) {
                            if (messageState.value.text.isEmpty()) {
                                Toast.makeText(
                                    context,
                                    "Please enter message data",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                            usbService!!.write(messageState.value.text.toByteArray())
                            Toast.makeText(context, "Data Packet Sent", Toast.LENGTH_LONG).show()
                        } else {
                            Toast.makeText(context, "Usb Service is null", Toast.LENGTH_LONG).show()
                        }
                    },
                    onCancel = {
                        sendCanDataDialog = false
                    }) {
                    sendCanDataDialog = false
                }
            }

            Row(modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize()
                        .padding(
                            start = MaterialTheme.spacing.xDp,
                            end = MaterialTheme.spacing.xDp
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    RightRow(
                        modifier = Modifier.weight(1f),
                        title = uartSerialCommunicationState.speed.tag,
                        data = uartSerialCommunicationState.speed.data
                    )

                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.xDp))

                    RightRow(
                        modifier = Modifier.weight(1f),
                        title = uartSerialCommunicationState.currentOfBattery.tag,
                        data = uartSerialCommunicationState.currentOfBattery.data
                    )

                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.xDp))

                    RightRow(
                        modifier = Modifier.weight(1f),
                        title = uartSerialCommunicationState.socBatteryPack.tag,
                        data = uartSerialCommunicationState.socBatteryPack.data
                    )

                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.xDp))

                    RightRow(
                        modifier = Modifier.weight(1f),
                        title = uartSerialCommunicationState.motorRpm.tag,
                        data = uartSerialCommunicationState.motorRpm.data
                    )

                    RightRow(
                        modifier = Modifier.weight(1f),
                        title = uartSerialCommunicationState.temperatureSensor1.tag,
                        data = uartSerialCommunicationState.temperatureSensor1.data
                    )

                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.xDp))

                }
                Column(
                    modifier = Modifier
                        .weight(2f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    Box(modifier = Modifier.padding(top = MaterialTheme.spacing.medium)) {
                        CanSpeedoMeter(
                            progress =
                            if (uartSerialCommunicationState.driverInput.data == "...") 0 else
                                uartSerialCommunicationState.driverInput.data.toInt(),
                            progress2 =
                            if (uartSerialCommunicationState.outputThrottle.data == "...") 0 else
                                uartSerialCommunicationState.outputThrottle.data.toInt()
                        )
                    }
                    Column(
                        modifier = Modifier.wrapContentHeight(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Row(
                            modifier = Modifier.wrapContentWidth(),
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Canvas(
                                modifier = Modifier.wrapContentWidth(),
                                onDraw = {
                                    drawIntoCanvas { canvas ->
                                        drawRect(
                                            color = Color.Red,
                                            size = Size(50f, 50f)
                                        )
                                    }
                                })
                            Spacer(modifier = Modifier.width(40.dp))
                            Text(text = "Output Throttle")
                        }

                        Row(
                            modifier = Modifier.wrapContentWidth(),
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Canvas(
                                modifier = Modifier
                                    .wrapContentWidth(),
                                onDraw = {
                                    drawIntoCanvas { canvas ->
                                        drawRect(
                                            color = Color.Green,
                                            size = Size(50f, 50f)
                                        )
                                    }
                                })
                            Spacer(modifier = Modifier.width(40.dp))
                            Text(text = "Driver Input Throttle")
                        }
                    }
                }

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize()
                        .padding(
                            start = MaterialTheme.spacing.xDp,
                            end = MaterialTheme.spacing.xDp
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    RightRow(
                        modifier = Modifier.weight(1f),
                        title = uartSerialCommunicationState.temperatureSensor2.tag,
                        data = uartSerialCommunicationState.temperatureSensor2.data
                    )

                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.xDp))

                    RightRow(
                        modifier = Modifier.weight(1f),
                        title = uartSerialCommunicationState.whPerKm.tag,
                        data = uartSerialCommunicationState.whPerKm.data
                    )

                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.xDp))

                    RightRow(
                        modifier = Modifier.weight(1f),
                        title = uartSerialCommunicationState.spare1.tag,
                        data = uartSerialCommunicationState.spare1.data
                    )

                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.xDp))

                    RightRow(
                        modifier = Modifier.weight(1f),
                        title = uartSerialCommunicationState.spare2.tag,
                        data = uartSerialCommunicationState.spare2.data
                    )

                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.xDp))

                    RightRow(
                        modifier = Modifier.weight(1f),
                        title = uartSerialCommunicationState.spare3.tag,
                        data = uartSerialCommunicationState.spare3.data
                    )

                }
            }


//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(
//                        start = MaterialTheme.spacing.xDp,
//                        end = MaterialTheme.spacing.xDp
//                    ),
//                verticalArrangement = Arrangement.Top
//            ) {
//                Spacer(modifier = Modifier.height(MaterialTheme.spacing.x2Dp))
//
//                Column(
//                    modifier = Modifier
//                        .weight(1f)
//                        .padding(start = MaterialTheme.spacing.xDp, end = MaterialTheme.spacing.xDp)
//                        .verticalScroll(rememberScrollState()),
//                    horizontalAlignment = Alignment.CenterHorizontally,
//                    verticalArrangement = Arrangement.Top
//                ) {
//                    Column(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .wrapContentHeight()
//                            .onGloballyPositioned { }
//                    ) {
//                        Row(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .height(IntrinsicSize.Min),
//                            verticalAlignment = Alignment.CenterVertically
//                        ) {
//                            LeftRow(
//                                modifier = Modifier.weight(1f),
//                                title = uartSerialCommunicationState.speed.tag,
//                                data = uartSerialCommunicationState.speed.data
//                            )
//                            Divider(
//                                modifier = Modifier
//                                    .padding(
//                                        start = MaterialTheme.spacing.small,
//                                        end = MaterialTheme.spacing.small
//                                    )
//                                    .width(1.dp)
//                                    .fillMaxHeight(),
//                                color = Color.Black
//                            )
//                            RightRow(
//                                modifier = Modifier.weight(1f),
//                                title = uartSerialCommunicationState.currentOfBattery.tag,
//                                data = uartSerialCommunicationState.currentOfBattery.data
//                            )
//                        }
//
//                        Spacer(modifier = Modifier.height(MaterialTheme.spacing.xDp))
//
//                        Row(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .height(IntrinsicSize.Min),
//                            verticalAlignment = Alignment.CenterVertically
//                        ) {
//                            LeftRow(
//                                modifier = Modifier.weight(1f),
//                                title = uartSerialCommunicationState.socBatteryPack.tag,
//                                data = uartSerialCommunicationState.socBatteryPack.data
//                            )
//                            Divider(
//                                modifier = Modifier
//                                    .padding(
//                                        start = MaterialTheme.spacing.small,
//                                        end = MaterialTheme.spacing.small
//                                    )
//                                    .width(1.dp)
//                                    .fillMaxHeight(),
//                                color = Color.Black
//                            )
//                            RightRow(
//                                modifier = Modifier.weight(1f),
//                                title = uartSerialCommunicationState.motorRpm.tag,
//                                data = uartSerialCommunicationState.motorRpm.data
//                            )
//                        }
//                        Spacer(modifier = Modifier.height(MaterialTheme.spacing.xDp))
//
//                        Row(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .height(IntrinsicSize.Min),
//                            verticalAlignment = Alignment.CenterVertically
//                        ) {
//                            LeftRow(
//                                modifier = Modifier.weight(1f),
//                                title = uartSerialCommunicationState.temperatureSensor1.tag,
//                                data = uartSerialCommunicationState.temperatureSensor1.data
//                            )
//                            Divider(
//                                modifier = Modifier
//                                    .padding(
//                                        start = MaterialTheme.spacing.small,
//                                        end = MaterialTheme.spacing.small
//                                    )
//                                    .width(1.dp)
//                                    .fillMaxHeight(),
//                                color = Color.Black
//                            )
//                            RightRow(
//                                modifier = Modifier.weight(1f),
//                                title = uartSerialCommunicationState.temperatureSensor2.tag,
//                                data = uartSerialCommunicationState.temperatureSensor2.data
//                            )
//                        }
//                        Spacer(modifier = Modifier.height(MaterialTheme.spacing.xDp))
//
//                        Row(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .height(IntrinsicSize.Min),
//                            verticalAlignment = Alignment.CenterVertically
//                        ) {
//                            LeftRow(
//                                modifier = Modifier.weight(1f),
//                                title = uartSerialCommunicationState.whPerKm.tag,
//                                data = uartSerialCommunicationState.whPerKm.data
//                            )
//                            Divider(
//                                modifier = Modifier
//                                    .padding(
//                                        start = MaterialTheme.spacing.small,
//                                        end = MaterialTheme.spacing.small
//                                    )
//                                    .width(1.dp)
//                                    .fillMaxHeight(),
//                                color = Color.Black
//                            )
//                            RightRow(
//                                modifier = Modifier.weight(1f),
//                                title = uartSerialCommunicationState.spare1.tag,
//                                data = uartSerialCommunicationState.spare1.data
//                            )
//                        }
//
//                        Row(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .height(IntrinsicSize.Min),
//                            verticalAlignment = Alignment.CenterVertically
//                        ) {
//                            LeftRow(
//                                modifier = Modifier.weight(1f),
//                                title = uartSerialCommunicationState.spare2.tag,
//                                data = uartSerialCommunicationState.spare2.data
//                            )
//                            Divider(
//                                modifier = Modifier
//                                    .padding(
//                                        start = MaterialTheme.spacing.small,
//                                        end = MaterialTheme.spacing.small
//                                    )
//                                    .width(1.dp)
//                                    .fillMaxHeight(),
//                                color = Color.Black
//                            )
//                            RightRow(
//                                modifier = Modifier.weight(1f),
//                                title = uartSerialCommunicationState.spare3.tag,
//                                data = uartSerialCommunicationState.spare3.data
//                            )
//                        }
//                    }
//
//                    Column(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .wrapContentHeight()
//                            .padding(top = MaterialTheme.spacing.x2Dp),
//                        horizontalAlignment = Alignment.CenterHorizontally,
//                        verticalArrangement = Arrangement.Center
//                    ) {
//                        SpeedoMeter(
//                            progress =
//                            if (uartSerialCommunicationState.speed.data == "...") 0 else
//                                uartSerialCommunicationState.speed.data.split(" ")[0].toInt()
//                        )
//                    }
//
//                }
//            }
        }
    }

    public override fun onResume() {
        super.onResume()
        setFilters() // Start listening notifications from UsbService
        startService(
            UsbService::class.java,
            usbConnection,
            null
        ) // Start UsbService(if it was not started before) and Bind it
    }

    public override fun onPause() {
        super.onPause()
        unregisterReceiver(mUsbReceiver)
        unbindService(usbConnection)
    }

    private fun startService(
        service: Class<*>,
        serviceConnection: ServiceConnection,
        extras: Bundle?
    ) {
        if (!UsbService.SERVICE_CONNECTED) {
            val startService = Intent(this, service)
            if (extras != null && !extras.isEmpty) {
                val keys = extras.keySet()
                for (key in keys) {
                    val extra = extras.getString(key)
                    startService.putExtra(key, extra)
                }
            }
            startService(startService)
        }
        val bindingIntent = Intent(this, service)
        bindService(bindingIntent, serviceConnection, BIND_AUTO_CREATE)
    }

    private fun setFilters() {
        val filter = IntentFilter()
        filter.addAction(UsbService.ACTION_USB_PERMISSION_GRANTED)
        filter.addAction(UsbService.ACTION_NO_USB)
        filter.addAction(UsbService.ACTION_USB_DISCONNECTED)
        filter.addAction(UsbService.ACTION_USB_NOT_SUPPORTED)
        filter.addAction(UsbService.ACTION_USB_PERMISSION_NOT_GRANTED)
        registerReceiver(mUsbReceiver, filter)
    }

    /*
     * This handler will be passed to UsbService. Data received from serial port is displayed through this handler
     */
    @SuppressLint("HandlerLeak")
    inner class MyHandler(activity: UsbMainActivity) : Handler(Looper.getMainLooper()) {
        private val mActivity: WeakReference<UsbMainActivity> = WeakReference(activity)
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                UsbService.MESSAGE_FROM_SERIAL_PORT -> {
                    val data = msg.obj as String
//                    Toast.makeText(
//                        mActivity.get(),
//                        data,
//                        Toast.LENGTH_LONG
//                    ).show()
                    viewModel.setDataPacket(data)
                }
                UsbService.CTS_CHANGE -> Toast.makeText(
                    mActivity.get(),
                    "CTS_CHANGE",
                    Toast.LENGTH_LONG
                ).show()
                UsbService.DSR_CHANGE -> Toast.makeText(
                    mActivity.get(),
                    "DSR_CHANGE",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

}