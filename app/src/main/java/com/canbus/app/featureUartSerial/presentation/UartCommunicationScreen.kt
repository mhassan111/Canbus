package com.canbus.app.featureUartSerial.presentation

import android.content.*
import android.os.*
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Upload
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.canbus.app.featureUartSerial.UsbBroadcastReceiver
import com.canbus.app.featureUartSerial.data.SerialUsbService
import com.canbus.app.featureUartSerial.presentation.components.BaudRateDialog
import com.canbus.app.featureUartSerial.presentation.components.SendCanDataDialog
import com.canbus.app.ui.spacing
import com.canbus.app.utilities.SharedPrefs
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun UartCommunicationScreen(
    viewModel: UartCommunicationViewModel = hiltViewModel()
) {

    val systemUiController = rememberSystemUiController()
    val scaffoldState = rememberScaffoldState()
    val context = LocalContext.current
    val lifeCycleOwner = LocalLifecycleOwner.current
    val keyboardController = LocalSoftwareKeyboardController.current

    val scrollableText by remember { mutableStateOf(StringBuilder("")) }
    val showMessageSenderSection by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState(0)
    var usbService: SerialUsbService? = null
    val messageState = viewModel.messageState
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

    val mHandler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                UsbService.MESSAGE_FROM_SERIAL_PORT -> {
                    val data = msg.obj as String
                    scrollableText.append("$data\n")
                }
                UsbService.CTS_CHANGE -> {
                    Toast.makeText(context, "CTS Change", Toast.LENGTH_LONG).show()
                }
                UsbService.DSR_CHANGE -> {
                    Toast.makeText(context, "DSR Change", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    val usbConnection: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(arg0: ComponentName, arg1: IBinder) {
            usbService = (arg1 as SerialUsbService.SerialUsbBinder).service
            usbService!!.setHandler(mHandler)
        }

        override fun onServiceDisconnected(arg0: ComponentName) {
            usbService = null
        }
    }

    DisposableEffect(key1 = lifeCycleOwner, effect = {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_START -> {

                }
                Lifecycle.Event.ON_RESUME -> {
                    setUsbBroadcastReceiverIntentFilter(context)
                    startService(context, SerialUsbService::class.java, usbConnection, null)
                }
                Lifecycle.Event.ON_PAUSE -> {
                    context.unregisterReceiver(usbBroadcastReceiver)
                    context.unbindService(usbConnection)
                }
                else -> {

                }
            }
        }
        lifeCycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifeCycleOwner.lifecycle.removeObserver(observer)
        }
    })

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
//                navigationIcon = {
//                    IconButton(onClick = {
//
//                    }) {
//                        Icon(
//                            imageVector = Icons.Filled.ArrowBack,
//                            contentDescription = "Navigate Back",
//                            tint = Color.Black
//                        )
//                    }
//                },
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
        },
        floatingActionButtonPosition = FabPosition.End
    ) { paddingValues ->

        if (showBaudRateDialog) {
            BaudRateDialog(
                modifier = Modifier.fillMaxWidth(),
                baudRate,
                onYes = { rate ->
                    baudRate = rate
                    SharedPrefs.baudRate = baudRate
                    showBaudRateDialog = false

                    context.unregisterReceiver(usbBroadcastReceiver)
                    context.unbindService(usbConnection)

                    setUsbBroadcastReceiverIntentFilter(context)
                    startService(context, SerialUsbService::class.java, usbConnection, null)
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
                            Toast.makeText(context, "Please enter message data", Toast.LENGTH_LONG)
                                .show()
                        }
                        usbService!!.write(messageState.value.text.toByteArray())
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

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = MaterialTheme.spacing.xDp,
                    end = MaterialTheme.spacing.xDp
                ),
            verticalArrangement = Arrangement.Top
        ) {

//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .wrapContentHeight(),
//                horizontalArrangement = Arrangement.SpaceBetween,
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//
//                Text(
//                    text = "Canbus",
//                    style = MaterialTheme.typography.h4,
//                    textAlign = TextAlign.Start
//                )
//
//                Image(
//                    imageVector = Icons.Filled.Send,
//                    contentDescription = null,
//                    modifier = Modifier
//                        .padding(MaterialTheme.spacing.small)
//                        .size(MaterialTheme.spacing.x3Dp)
//                        .clickable {
//                            showMessageSenderSection = !showMessageSenderSection
//                        },
//                    colorFilter = ColorFilter.tint(color = Color.Black)
//                )
//            }

            AnimatedVisibility(visible = showMessageSenderSection) {
                Column(
                    modifier = Modifier.padding(top = MaterialTheme.spacing.x3Dp)
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = MaterialTheme.spacing.xDp,
                                end = MaterialTheme.spacing.xDp
                            )
                            .height(60.dp)
                            .verticalScroll(scrollState)
                            .testTag("uart_received_messages"),
                        text = scrollableText.toString()
                    )

                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.xDp))

//                    HintTextField(
//                        text = messageState.text,
//                        hint = messageState.hint,
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .wrapContentHeight(),
//                        onValueChange = {
//                            viewModel.onEvent(UartCommunicationEvent.EnteredUartMessage(it))
//                        },
//                        onFocusChange = {
//                            viewModel.onEvent(UartCommunicationEvent.ChangeUartMessageFocus(it))
//                        },
//                        isHintVisible = messageState.isHintVisible,
//                        singleLine = true,
//                        textStyle = MaterialTheme.typography.h5,
//                        textColor = Color.Black,
//                        onKeyboardActionDone = {
//                            keyboardController?.hide()
//                        }
//                    )

                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.x2Dp))

                    OutlinedButton(
                        onClick = {
                            if (usbService != null) {
                                usbService!!.write(messageState.value.text.toByteArray())
                            } else {
                                Toast.makeText(context, "Usb Service is null", Toast.LENGTH_LONG)
                                    .show()
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = "Send")
                    }
                }
            }

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.x2Dp))

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = MaterialTheme.spacing.xDp, end = MaterialTheme.spacing.xDp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .onGloballyPositioned {  }
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(IntrinsicSize.Min),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        LeftRow(
                            modifier = Modifier.weight(1f),
                            title = uartSerialCommunicationState.speed.tag,
                            data = uartSerialCommunicationState.speed.data
                        )
                        Divider(
                            modifier = Modifier
                                .padding(
                                    start = MaterialTheme.spacing.small,
                                    end = MaterialTheme.spacing.small
                                )
                                .width(1.dp)
                                .fillMaxHeight(),
                            color = Color.DarkGray
                        )
                        RightRow(
                            modifier = Modifier.weight(1f),
                            title = uartSerialCommunicationState.currentOfBattery.tag,
                            data = uartSerialCommunicationState.currentOfBattery.data
                        )
                    }

                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.xDp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(IntrinsicSize.Min),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        LeftRow(
                            modifier = Modifier.weight(1f),
                            title = uartSerialCommunicationState.socBatteryPack.tag,
                            data = uartSerialCommunicationState.socBatteryPack.data
                        )
                        Divider(
                            modifier = Modifier
                                .padding(
                                    start = MaterialTheme.spacing.small,
                                    end = MaterialTheme.spacing.small
                                )
                                .width(1.dp)
                                .fillMaxHeight(),
                            color = Color.DarkGray
                        )
                        RightRow(
                            modifier = Modifier.weight(1f),
                            title = uartSerialCommunicationState.motorRpm.tag,
                            data = uartSerialCommunicationState.motorRpm.data
                        )
                    }
                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.xDp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(IntrinsicSize.Min),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        LeftRow(
                            modifier = Modifier.weight(1f),
                            title = uartSerialCommunicationState.temperatureSensor1.tag,
                            data = uartSerialCommunicationState.temperatureSensor1.data
                        )
                        Divider(
                            modifier = Modifier
                                .padding(
                                    start = MaterialTheme.spacing.small,
                                    end = MaterialTheme.spacing.small
                                )
                                .width(1.dp)
                                .fillMaxHeight(),
                            color = Color.DarkGray
                        )
                        RightRow(
                            modifier = Modifier.weight(1f),
                            title = uartSerialCommunicationState.temperatureSensor2.tag,
                            data = uartSerialCommunicationState.temperatureSensor2.data
                        )
                    }
                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.xDp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(IntrinsicSize.Min),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        LeftRow(
                            modifier = Modifier.weight(1f),
                            title = uartSerialCommunicationState.whPerKm.tag,
                            data = uartSerialCommunicationState.whPerKm.data
                        )
                        Divider(
                            modifier = Modifier
                                .padding(
                                    start = MaterialTheme.spacing.small,
                                    end = MaterialTheme.spacing.small
                                )
                                .width(1.dp)
                                .fillMaxHeight(),
                            color = Color.DarkGray
                        )
                        RightRow(
                            modifier = Modifier.weight(1f),
                            title = uartSerialCommunicationState.timeSinceBoot.tag,
                            data = uartSerialCommunicationState.timeSinceBoot.data
                        )
                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(top = MaterialTheme.spacing.x2Dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
//                    CanSpeedoMeter()
                }
            }
        }
    }


}

@Composable
fun LeftRow(
    modifier: Modifier,
    title: String,
    data: String
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.wrapContentWidth(),
            text = data, style = MaterialTheme.typography.h5, color = Color.Black
        )
        Spacer(modifier = Modifier.width(MaterialTheme.spacing.xDp))
        Text(
            modifier = Modifier.weight(1f),
            text = title,
            style = MaterialTheme.typography.body1,
            color = Color.Black,
            textAlign = TextAlign.End
        )
    }
}

@Composable
fun RightRow(
    modifier: Modifier,
    title: String,
    data: String
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = title,
            style = MaterialTheme.typography.body1,
            color = Color.Black,
            textAlign = TextAlign.Start
        )
        Spacer(modifier = Modifier.width(MaterialTheme.spacing.medium))
        Text(
            modifier = Modifier.wrapContentWidth(),
            text = data,
            style = MaterialTheme.typography.h5,
            color = Color.Black
        )
    }
}

private val usbBroadcastReceiver = UsbBroadcastReceiver()

private fun setUsbBroadcastReceiverIntentFilter(context: Context) {
    val filter = IntentFilter()
    filter.addAction(UsbService.ACTION_USB_PERMISSION_GRANTED)
    filter.addAction(UsbService.ACTION_NO_USB)
    filter.addAction(UsbService.ACTION_USB_DISCONNECTED)
    filter.addAction(UsbService.ACTION_USB_NOT_SUPPORTED)
    filter.addAction(UsbService.ACTION_USB_PERMISSION_NOT_GRANTED)
    context.registerReceiver(usbBroadcastReceiver, filter)
}

private fun startService(
    context: Context,
    service: Class<*>,
    serviceConnection: ServiceConnection,
    extras: Bundle? = null
) {
    if (!UsbService.SERVICE_CONNECTED) {
        val startService = Intent(context, service)
        if (extras != null && !extras.isEmpty) {
            val keys = extras.keySet()
            for (key in keys) {
                val extra = extras.getString(key)
                startService.putExtra(key, extra)
            }
        }
        context.startService(startService)
    }
    val bindingIntent = Intent(context, service)
    context.bindService(bindingIntent, serviceConnection, ComponentActivity.BIND_AUTO_CREATE)
}

