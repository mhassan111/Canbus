package com.os.system.featureInstallation

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import com.os.system.featureInstallation.presentation.NavGraphs
import com.os.system.featureInstallation.presentation.destinations.DeviceAdminPermissionScreenDestination
import com.os.system.featureInstallation.presentation.destinations.LocationPermissionScreenDestination
import com.os.system.featureInstallation.presentation.permissions.*
import com.os.system.ui.theme.TOSTheme
import com.os.system.utilities.Util
import com.os.system.utilities.collectLatestLifeCycleFlow
import com.os.system.utilities.logVerbose
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.ramcosta.composedestinations.DestinationsNavHost
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@ExperimentalPermissionsApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    // AppPermissionViewModel
    @Inject
    lateinit var permissionsViewModel: PermissionsViewModel

    // mainThread Handler
    private val mainThreadHandler by lazy {
        Handler(Looper.getMainLooper())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // This app draws behind the system bars, so we want to handle fitting system windows
//        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            TOSTheme {
                DestinationsNavHost(navGraph = NavGraphs.root)
            }
        }

        collectLatestLifeCycleFlow(permissionsViewModel.permissionEventState) { permissionState ->
            logVerbose("permission state collect = ${permissionState.javaClass.simpleName}")
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
            if (permissionState != NoEvent) {
                permissionsViewModel.setPermissionState(NoEvent)
            }
        }
        addHandlerCallbacks()
    }

    private fun addHandlerCallbacks() {
        mainThreadHandler.removeCallbacks(mRunnable)
        mainThreadHandler.postDelayed(mRunnable, 500)
    }

    private val mRunnable = Runnable {
        when (permissionsViewModel.permissionEventState.value) {
            AccessibilityPermissionEvent -> {

            }
            DeviceAdminPermissionEvent -> {

            }
            DisableNotificationAccessEvent -> {

            }
            DisplayOverAppsPermissionEvent -> {

            }
            HideAppEvent -> {

            }
            LocationPermissionEvent -> {
                if (Util.isLocationPermissionGranted(this)) {
                    logVerbose("location permission is granted")
                    permissionsViewModel.setPermissionState(ManagementOfAllFilesPermissionEVent)
                }
            }
            ManagementOfAllFilesPermissionEVent -> {

            }
            NotificationAccessPermissionEvent -> {

            }
            ScreenRecordPermissionEvent -> {

            }
            NoEvent -> {

            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mainThreadHandler.removeCallbacks(mRunnable)
    }
}
