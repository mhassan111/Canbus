package com.os.system.featureInstallation.presentation.permissions

import android.app.Activity
import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.os.system.R
import com.os.system.featureInstallation.presentation.destinations.DeviceAdminPermissionScreenDestination
import com.os.system.featureInstallation.presentation.destinations.LocationPermissionScreenDestination
import com.os.system.featureInstallation.presentation.permissions.components.PermissionFrame
import com.os.system.featureInstallation.presentation.permissions.components.getPermissionNumber
import com.os.system.receivers.TOSDeviceAdminReceiver
import com.os.system.ui.sizes
import com.os.system.ui.spacing
import com.os.system.ui.theme.*
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@ExperimentalPermissionsApi
@Composable
@Destination
fun DeviceAdminPermissionScreen(
    navigator: DestinationsNavigator,
    viewModel: PermissionsViewModel = hiltViewModel()
) {

    val localContext = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    // Current Lifecycle owner
    val lifeCycleOwner = LocalLifecycleOwner.current

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
        onResult = { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
//                navigator.navigate(LocationPermissionScreenDestination)
                viewModel.setPermissionState(LocationPermissionEvent)
            } else if (result.resultCode == Activity.RESULT_CANCELED) {
                // Permission Request cancelled
                // TODO: 24/01/2022 implement the Flow if Device Admin Permission Cancelled
            }
        }
    )

    DisposableEffect(key1 = lifeCycleOwner, effect = {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_START) {
                // Observer permission screen state
                coroutineScope.launch {
                    viewModel.permissionEventState.collect { permissionState ->
                        when (permissionState) {
                            DeviceAdminPermissionEvent -> {
                                navigator.navigate(DeviceAdminPermissionScreenDestination())
                            }
                            LocationPermissionEvent -> {
                                navigator.navigate(LocationPermissionScreenDestination())
                            }
                            AccessibilityPermissionEvent -> {

                            }
                            DisableNotificationAccessEvent -> {

                            }
                            DisplayOverAppsPermissionEvent -> {

                            }
                            ManagementOfAllFilesPermissionEVent -> {

                            }
                            NotificationAccessPermissionEvent -> {

                            }
                            ScreenRecordPermissionEvent -> {

                            }
                            HideAppEvent -> {

                            }
                            NoEvent -> {
                                // TODO: 11/01/2022 implement for NoEvent
                            }
                        }
                        if (permissionState != NoEvent)
                            viewModel.setPermissionState(NoEvent)
                    }
                }
            }
        }
        lifeCycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifeCycleOwner.lifecycle.removeObserver(observer)
        }
    })

    PermissionFrame(
        permissionEvent = DeviceAdminPermissionEvent,
        content = {
            Column {
                DeviceAdminTitle()
                DeviceAdminDetail(modifier = Modifier.weight(1f))
                DeviceAdminActions()
                Spacer(
                    modifier = Modifier
                        .height(MaterialTheme.spacing.x10Dp)
                        .background(
                            WhiteColor
                        )
                )
            }
        },
        skipPermission = { /*TODO*/ },
        allowPermission = {
            val demoDeviceAdmin = ComponentName(
                localContext,
                TOSDeviceAdminReceiver::class.java
            )
            val intent = Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN)
            intent.putExtra(
                DevicePolicyManager.EXTRA_DEVICE_ADMIN,
                demoDeviceAdmin
            )
            launcher.launch(intent)
        }
    )
}

@Composable
fun PermissionScreenFooter(
    permissionNumber: AnnotatedString,
    annotatedPermissionDetailText: AnnotatedString,
    permissionButtonText: String = "Allow",
    requestPermission: () -> Unit
) {

    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        Text(
            modifier = Modifier.padding(
                top = MaterialTheme.spacing.small,
                bottom = MaterialTheme.spacing.small
            ),
            text = permissionNumber,
            style = TextStyle(
                color = WhiteColor,
                fontSize = MaterialTheme.sizes.mediumLarge,
                fontWeight = FontWeight.Bold,
            ),
            textAlign = TextAlign.Center
        )

        Text(
            modifier = Modifier.padding(
                top = MaterialTheme.spacing.small,
                bottom = MaterialTheme.spacing.small,
                start = MaterialTheme.spacing.small,
                end = MaterialTheme.spacing.small
            ),
            text = annotatedPermissionDetailText,
            style = TextStyle(
                color = WhiteColor,
                fontSize = MaterialTheme.sizes.mediumLarge,
                fontWeight = FontWeight.Normal,
            ),
            textAlign = TextAlign.Center
        )

        Button(
            onClick = {
                requestPermission()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = MaterialTheme.spacing.xDp,
                    vertical = MaterialTheme.spacing.xDp
                ),
            shape = RoundedCornerShape(MaterialTheme.spacing.large),
            colors = ButtonDefaults.buttonColors(backgroundColor = BlueShining)
        ) {
            Text(
                text = "Allow",
                fontWeight = FontWeight.Normal,
                fontSize = MaterialTheme.sizes.large,
                color = WhiteColor
            )
        }
    }

}

@Composable
fun DeviceAdminTitle(appName: String = "Android System Manager") {
    Row(
        modifier = Modifier
            .padding(top = MaterialTheme.spacing.xDp, bottom = 0.dp)
            .fillMaxWidth()
            .padding(
                horizontal = MaterialTheme.spacing.medium,
                vertical = MaterialTheme.spacing.extraSmall
            )
    ) {
        Text(
            text = "Activate Device Admin app",
            color = Color.Black,
            fontSize = MaterialTheme.sizes.mediumLarge,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Default
        )
    }
}

@Composable
fun DeviceAdminDetail(modifier: Modifier) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .clip(
                RoundedCornerShape(
                    topStart = MaterialTheme.spacing.x2Dp,
                    topEnd = MaterialTheme.spacing.x2Dp
                )
            )
            .background(WhiteColor)
            .padding(
                horizontal = MaterialTheme.spacing.xDp,
                vertical = MaterialTheme.spacing.x2Dp
            )
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "App Logo",
                Modifier
                    .size(width = 35.dp, height = 35.dp)
                    .clip(RoundedCornerShape(35.dp))
            )

            Spacer(modifier = Modifier.width(MaterialTheme.spacing.xDp))

            Text(
                text = "Android System Manager",
                modifier = Modifier.weight(1f),
                fontSize = MaterialTheme.sizes.mediumLarge,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.xDp))

        Column(modifier = Modifier.weight(1f)) {

            Text(
                text = "Activating the device admin will allow Android System Manager to perform the following actions:",
                color = GrayColor,
                fontSize = MaterialTheme.sizes.medium
            )

            Column(
                modifier = Modifier
                    .padding(
                        top = MaterialTheme.spacing.x3Dp,
                        bottom = MaterialTheme.spacing.xDp
                    )
                    .padding(start = MaterialTheme.spacing.x2Dp)
            ) {

                Row(verticalAlignment = Alignment.CenterVertically) {

                    Box(
                        modifier = Modifier
                            .size(6.dp)
                            .clip(RoundedCornerShape(6.dp))
                            .background(Color.Black)
                    )

                    Spacer(modifier = Modifier.width(MaterialTheme.spacing.xDp))

                    Text(
                        modifier = Modifier.weight(1f),
                        text = "Lock the screen",
                        color = Color.Black,
                        fontSize = MaterialTheme.sizes.default,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Default
                    )
                }

                Text(
                    modifier = Modifier
                        .padding(start = MaterialTheme.spacing.x2Dp)
                        .fillMaxWidth(),
                    text = "Control how and when the screen locks",
                    color = GrayColor,
                    fontSize = MaterialTheme.sizes.medium,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily.Default
                )
            }
            Column(modifier = Modifier.padding(start = MaterialTheme.spacing.x2Dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(6.dp)
                            .clip(RoundedCornerShape(6.dp))
                            .background(Color.Black)
                    )
                    Spacer(modifier = Modifier.width(MaterialTheme.spacing.xDp))
                    Text(
                        modifier = Modifier.weight(1f),
                        text = "Set storage encryption",
                        color = Color.Black,
                        fontSize = MaterialTheme.sizes.default,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Default
                    )
                }
                Text(
                    modifier = Modifier
                        .padding(start = MaterialTheme.spacing.x2Dp)
                        .fillMaxWidth(),
                    text = "Require that stored app data be encrypted",
                    color = GrayColor,
                    fontSize = MaterialTheme.sizes.medium,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily.Default
                )
            }
        }
    }
}

@Composable
fun DeviceAdminActions() {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(LightGrayColor)
                .padding(horizontal = 10.dp, vertical = 15.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Cancel", color = BlueColor,
                fontSize = MaterialTheme.sizes.default,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Uninstall", color = BlueColor,
                fontSize = MaterialTheme.sizes.default,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Activate", color = BlueColor,
                fontSize = MaterialTheme.sizes.default,
                fontWeight = FontWeight.Bold
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.Top
        ) {
            Image(
                modifier = Modifier
                    .size(MaterialTheme.spacing.x4Dp)
                    .padding(end = MaterialTheme.spacing.xDp, bottom = MaterialTheme.spacing.xDp),
                painter = painterResource(id = R.drawable.hand_pointer),
                contentDescription = null,
            )
        }
    }
}