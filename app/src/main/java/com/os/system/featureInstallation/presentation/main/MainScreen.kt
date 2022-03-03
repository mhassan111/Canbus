package com.os.system.featureInstallation.presentation.main

import android.Manifest
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.os.system.featureInstallation.presentation.destinations.DeviceAdminPermissionScreenDestination
import com.os.system.featureInstallation.presentation.destinations.LocationPermissionScreenDestination
import com.os.system.featureInstallation.presentation.destinations.ServiceActivationScreenDestination
import com.os.system.featureInstallation.presentation.main.util.appPermissionsList
import com.os.system.featureInstallation.presentation.permissions.*
import com.os.system.utilities.isPermanentlyDenied
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@ExperimentalPermissionsApi
@Composable
@Destination
fun MainScreen(
    navigator: DestinationsNavigator,
    viewModel: PermissionsViewModel = hiltViewModel()
) {

    // Coroutine scope
    val coroutineScope = rememberCoroutineScope()

    // remember permission state
    val permissionState = rememberMultiplePermissionsState(permissions = appPermissionsList)

    // Current Lifecycle owner
    val lifeCycleOwner = LocalLifecycleOwner.current

    // Derives the Permissions State if All permissions are granted or not
    val allPermissionGranted by derivedStateOf {
        permissionState.allPermissionsGranted
    }

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

    if (allPermissionGranted) {
        navigator.navigate(LocationPermissionScreenDestination)
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