package com.os.system.featureInstallation.presentation.permissions

import android.Manifest
import android.annotation.TargetApi
import android.app.Activity
import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.content.Intent
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.os.system.R
import com.os.system.featureInstallation.presentation.destinations.DeviceAdminPermissionScreenDestination
import com.os.system.featureInstallation.presentation.destinations.LocationPermissionScreenDestination
import com.os.system.featureInstallation.presentation.destinations.ManagementOfAllFilesPermissionScreenDestination
import com.os.system.featureInstallation.presentation.main.util.appPermissionsList
import com.os.system.featureInstallation.presentation.main.util.locationPermission
import com.os.system.featureInstallation.presentation.permissions.components.PermissionFrame
import com.os.system.receivers.TOSDeviceAdminReceiver
import com.os.system.ui.sizes
import com.os.system.ui.spacing
import com.os.system.ui.theme.*
import com.os.system.utilities.isPermanentlyDenied
import com.os.system.utilities.logVerbose
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@ExperimentalPermissionsApi
@Composable
@Destination
fun LocationPermissionScreen(
    navigator: DestinationsNavigator? = null,
    viewModel: PermissionsViewModel = hiltViewModel()
) {

    // Current Context
    val currentContext = LocalContext.current

    // remember permission state
    val permissionState = rememberMultiplePermissionsState(permissions = locationPermission)

    // remember Coroutine scope
    val coroutineScope = rememberCoroutineScope()

    // Current Lifecycle owner
    val lifeCycleOwner = LocalLifecycleOwner.current

    // Derives the Permissions State if All permissions are granted or not
    val allPermissionGranted by derivedStateOf {
        permissionState.allPermissionsGranted
    }

    permissionState.permissions.forEach { permission ->
        when (permission.permission) {
            Manifest.permission.ACCESS_BACKGROUND_LOCATION -> {
                when {
                    permission.hasPermission -> {
                        currentContext.logVerbose("Location Permission Granted")
//                        navigator!!.navigate(ManagementOfAllFilesPermissionScreenDestination)
                    }
                    permission.shouldShowRationale -> {
                        currentContext.logVerbose("Location Permission shouldShowRationale")
                    }
                    permission.isPermanentlyDenied() -> {
                        currentContext.logVerbose("Location Permission permanently denied")
                    }
                }
            }
        }
    }

    DisposableEffect(key1 = lifeCycleOwner, effect = {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_START) {
                // Observer permission screen state
                coroutineScope.launch {
                    viewModel.permissionEventState.collect { permissionState ->
                        when (permissionState) {
                            DeviceAdminPermissionEvent -> {

                            }
                            LocationPermissionEvent -> {

                            }
                            AccessibilityPermissionEvent -> {

                            }
                            DisableNotificationAccessEvent -> {

                            }
                            DisplayOverAppsPermissionEvent -> {

                            }
                            ManagementOfAllFilesPermissionEVent -> {
                                navigator!!.navigate(ManagementOfAllFilesPermissionScreenDestination)
                            }
                            NotificationAccessPermissionEvent -> {

                            }
                            ScreenRecordPermissionEvent -> {

                            }
                            HideAppEvent -> {

                            }
                            NoEvent -> {

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
        permissionEvent = LocationPermissionEvent,
        content = {
            Column {
                TopSection()
                AppNameAndIcon()
                AllowLocationPermission(
                    modifier = Modifier
                        .background(LighterGrayColor)
                        .padding(15.dp)
                        .weight(1f)
                        .padding(vertical = 30.dp)
                )
            }
        },
        skipPermission = {

        },
        allowPermission = {
            permissionState.launchMultiplePermissionRequest()
        }
    )
}

@Composable
fun TopSection() {
    Row(
        modifier = Modifier
            .padding(top = 10.dp, bottom = 0.dp)
            .fillMaxWidth()
            .padding(horizontal = 15.dp, vertical = 5.dp),
        verticalAlignment = CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Icon(
            imageVector = Icons.Filled.ArrowBack, contentDescription = "Back Arrow",
            modifier = Modifier
                .size(30.dp)
        )
        Spacer(modifier = Modifier.width(15.dp))
        Text(
            modifier = Modifier.weight(1f),
            text = "Location Permission",
            color = Color.Black,
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Default
        )

        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = "Search Icon",
            modifier = Modifier
                .size(25.dp)
                .align(CenterVertically)
        )
    }
}

@Composable
fun AppNameAndIcon() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(LightGrayColor)
            .padding(top = 15.dp, bottom = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {

        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "App Logo",
            Modifier
                .size(width = 35.dp, height = 35.dp)
                .clip(RoundedCornerShape(35.dp))
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Android System Manager",
            modifier = Modifier.fillMaxWidth(),
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

    }
}

@Composable
fun AllowLocationPermission(modifier: Modifier) {
    Column(modifier = modifier) {
        Text(
            text = "LOCATION ACCESS FOR THIS APP",
            modifier = Modifier
                .fillMaxWidth()
                .offset(50.dp),
            color = LightGreenishColor,
            fontSize = 11.sp,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(20.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {

            RadioButton(
                selected = true,
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .size(30.dp)
                    .offset(0.dp),
            )

            Box(
                contentAlignment = CenterStart,
                modifier = Modifier
                    .wrapContentWidth()
                    .height(30.dp)
                    .offset(50.dp)
            ) {
                Text(
                    text = "Allow all the time",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .wrapContentWidth()
                        .wrapContentHeight()
                )
            }

            Image(
                modifier = Modifier
                    .size(30.dp)
                    .offset((LocalConfiguration.current.screenWidthDp - 200).dp),
                painter = painterResource(id = R.drawable.hand_pointer),
                contentDescription = null,
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {

            RadioButton(
                selected = false,
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .size(30.dp)
                    .offset(0.dp)
            )

            Box(
                contentAlignment = CenterStart,
                modifier = Modifier
                    .wrapContentWidth()
                    .height(30.dp)
                    .offset(50.dp)
            ) {
                Text(
                    text = "Allow only while using the app",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .wrapContentWidth()
                        .wrapContentHeight()
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {

            RadioButton(
                selected = false,
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .size(30.dp)
                    .offset(0.dp)
            )
            Box(
                contentAlignment = CenterStart,
                modifier = Modifier
                    .wrapContentWidth()
                    .height(30.dp)
                    .offset(50.dp)
            ) {
                Text(
                    text = "Deny",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .wrapContentWidth()
                        .wrapContentHeight()
                )
            }
        }
    }
}

@ExperimentalPermissionsApi
@Preview
@Composable
fun LocationPermissionPreview() {
    LocationPermissionScreen()
}