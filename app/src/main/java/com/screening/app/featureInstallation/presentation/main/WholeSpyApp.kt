package com.screening.app.featureInstallation.presentation.main

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.screening.app.featureInstallation.presentation.main.util.permissions
import com.screening.app.featureInstallation.presentation.permissions.MainViewModel
import com.screening.app.ui.theme.ScreeningAppTheme
import com.screening.app.utilities.logVerbose

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun WholeSpyApp(viewModel: MainViewModel = hiltViewModel()) {

    ScreeningAppTheme {
        val currentContext = LocalContext.current

        // Coroutine scope
        val coroutineScope = rememberCoroutineScope()

        // remember permission state
        val permissionStates = rememberMultiplePermissionsState(permissions = permissions)

        // Current Lifecycle owner
        val lifeCycleOwner = LocalLifecycleOwner.current

        // Derives the Permissions State if All permissions are granted or not
        val allPermissionGranted by derivedStateOf { permissionStates.allPermissionsGranted }
        val currentScreenState = viewModel.currentScreenState.value
        var currentScreen by rememberSaveable { mutableStateOf(currentScreenState) }

        SideEffect {
            currentContext.logVerbose("$TAG Inside SideEffect")
        }

        Scaffold { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                logVerbose("$TAG setting current screen content")
                currentScreen.content(onScreenChange = { screen -> currentScreen = screen })
            }
        }

        // Lifecycle Aware Events
        DisposableEffect(key1 = lifeCycleOwner, effect = {
            val observer = LifecycleEventObserver { _, event ->
                if (event == Lifecycle.Event.ON_START) {
                    logVerbose("$TAG onStart called")
//                    permissionStates.launchMultiplePermissionRequest()

//                    coroutineScope.launch {
//                        viewModel.currentScreen.collectLatest { screen ->
//                            when (screen) {
//                                WholeSpyScreen.LocationScreen -> {
//
//                                }
//                            }
//                            currentScreen = screen
//                        }
//                    }
                }
            }
            lifeCycleOwner.lifecycle.addObserver(observer)
            onDispose {
                lifeCycleOwner.lifecycle.removeObserver(observer)
            }
        })

//        if (allPermissionGranted) {
//
//        } else {
//            Column(
//                modifier = Modifier.fillMaxSize(),
//                horizontalAlignment = Alignment.CenterHorizontally,
//                verticalArrangement = Arrangement.Top
//            ) {
//                permissionStates.permissions.forEach { it ->
//                    when (it.permission) {
//                        Manifest.permission.READ_EXTERNAL_STORAGE -> {
//                            when {
//                                it.hasPermission -> {
//                                    /* Permission has been granted by the user.
//                                       You can use this permission to now acquire the location of the device.
//                                       You can perform some other tasks here.
//                                    */
////                                    Text(text = "Read Ext Storage permission has been granted")
//                                }
//                                it.shouldShowRationale -> {
//                                    /*Happens if a user denies the permission two times
//
//                                     */
////                                    Text(text = "Read Ext Storage permission is needed")
//                                }
//                                !it.hasPermission && !it.shouldShowRationale -> {
//                                    /* If the permission is denied and the should not show rationale
//                                        You can only allow the permission manually through app settings
//                                     */
////                                    Text(text = "Navigate to settings and enable the Storage permission")
//                                }
//                            }
//                        }
//                        Manifest.permission.ACCESS_FINE_LOCATION -> {
//                            when {
//                                it.hasPermission -> {
//                                    /* Permission has been granted by the user.
//                                       You can use this permission to now acquire the location of the device.
//                                       You can perform some other tasks here.
//                                    */
////                                    Text(text = "Location permission has been granted")
//                                }
//                                it.shouldShowRationale -> {
//                                    /*Happens if a user denies the permission two times
//
//                                     */
////                                    Text(text = "Location permission is needed")
//
//                                }
//                                !it.hasPermission && !it.shouldShowRationale -> {
//                                    /* If the permission is denied and the should not show rationale
//                                        You can only allow the permission manually through app settings
//                                     */
////                                    Text(text = "Navigate to settings and enable the Location permission")
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
    }
}

const val TAG = "WholeSpyApp"
