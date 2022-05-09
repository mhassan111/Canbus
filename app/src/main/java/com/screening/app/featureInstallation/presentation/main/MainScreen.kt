package com.screening.app.featureInstallation.presentation.main

import android.Manifest
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.accompanist.permissions.rememberPermissionState
import com.screening.app.featureInstallation.presentation.permissions.MainViewModel
import com.screening.app.utilities.isPermanentlyDenied

@ExperimentalPermissionsApi
@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel()
) {

    // Coroutine scope
    val coroutineScope = rememberCoroutineScope()

    // remember permission state
    val permissionState = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
    )

    // Current Lifecycle owner
    val lifeCycleOwner = LocalLifecycleOwner.current

    // Derives the Permissions State if All permissions are granted or not
    val allPermissionGranted by derivedStateOf { permissionState.allPermissionsGranted }

    // Lifecycle Aware Events
    DisposableEffect(key1 = lifeCycleOwner, effect = {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_START) {
                // Request for Runtime permissions
                permissionState.launchMultiplePermissionRequest()
                // Observer permission screen state
//                coroutineScope.launch {
//                    viewModel.permissionEventState.collect { permissionState ->
//                        when (permissionState) {
//                            DeviceAdminPermissionEvent -> {
//                                navigator.navigate(DeviceAdminPermissionScreenDestination())
//                            }
//                            LocationPermissionEvent -> {
//                                navigator.navigate(LocationPermissionScreenDestination())
//                            }
//                            AccessibilityPermissionEvent -> {
//
//                            }
//                            DisableNotificationAccessEvent -> {
//
//                            }
//                            DisplayOverAppsPermissionEvent -> {
//
//                            }
//                            ManagementOfAllFilesPermissionEVent -> {
//
//                            }
//                            NotificationAccessPermissionEvent -> {
//
//                            }
//                            ScreenRecordPermissionEvent -> {
//
//                            }
//                            HideAppEvent -> {
//
//                            }
//                            NoEvent -> {
//                                // TODO: 11/01/2022 implement for NoEvent
//                            }
//                        }
//                        viewModel.setPermissionState(NoEvent)
//                    }
//                }
            }
        }
        lifeCycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifeCycleOwner.lifecycle.removeObserver(observer)
        }
    })

    var doNotShowRationale by rememberSaveable { mutableStateOf(false) }
    val cameraPermissionState = rememberPermissionState(Manifest.permission.ACCESS_FINE_LOCATION)

//    permissionState.permissions.forEach { permissionState ->
//        PermissionRequired(
//            permissionState = permissionState,
//            permissionNotGrantedContent = {
//                if (doNotShowRationale) {
//                    Text("Feature not available")
//                } else {
//                    Column(modifier = Modifier.statusBarsPadding()) {
//                        Text("The camera is important for this app. Please grant the permission.")
//                        Spacer(modifier = Modifier.height(8.dp))
//                        Row {
//                            Button(onClick = { permissionState.launchPermissionRequest() }) {
//                                Text("Ok!")
//                            }
//                            Spacer(Modifier.width(8.dp))
//                            Button(onClick = { doNotShowRationale = true }) {
//                                Text("Nope")
//                            }
//                        }
//                    }
//                }
//            },
//            permissionNotAvailableContent = {
//                Column(modifier = Modifier.statusBarsPadding()) {
//                    Text(
//                        "Camera permission denied. See this FAQ with information about why we " +
//                                "need this permission. Please, grant us access on the Settings screen."
//                    )
//                    Spacer(modifier = Modifier.height(8.dp))
//                }
//            }
//        ) {
//            Text("Camera permission Granted")
//        }
//    }

    if (allPermissionGranted) {

    } else {

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            permissionState.permissions.forEach { permission ->
                when (permission.permission) {
                    Manifest.permission.CAMERA -> {
                        when {
                            permission.hasPermission -> {
                                Text(text = "Camera Permission granted")
                            }
                            permission.shouldShowRationale -> {
                                Text(text = "Camera Permission is needed to get camera")
                            }
                            permission.isPermanentlyDenied() -> {
                                Text(text = "Camera Permission was permanently denied, you can enable it in app settings")
                            }
                        }
                    }
                    Manifest.permission.RECORD_AUDIO -> {
                        when {
                            permission.hasPermission -> {
                                Text(text = "Record audio Permission granted")
                            }
                            permission.shouldShowRationale -> {
                                Text(text = "Record Permission is needed to record from microphone")
                            }
                            permission.isPermanentlyDenied() -> {
                                Text(text = "Record Permission was permanently denied, you can enable it in app settings")
                            }
                        }
                    }
                }
            }

        }
    }
}