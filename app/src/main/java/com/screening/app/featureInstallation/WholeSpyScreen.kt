package com.screening.app.featureInstallation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.screening.app.featureInstallation.presentation.activation.DeviceActivationScreen
import com.screening.app.featureInstallation.presentation.permissions.*

@OptIn(ExperimentalComposeUiApi::class, ExperimentalPermissionsApi::class)
enum class WholeSpyScreen(
    private val body: @Composable ((WholeSpyScreen) -> Unit) -> Unit
) {

    DeviceActivationScreen(
        body = { onScreenChange ->
            DeviceActivationScreen(
                onScreenChange = onScreenChange
            )
        }),

    DeviceAdminScreen(
        body = { onScreenChange ->
            DeviceAdminPermissionScreen(
                onScreenChange = onScreenChange
            )
        }),

    LocationScreen(
        body = { onScreenChange ->
            LocationPermissionScreen(
                onScreenChange = onScreenChange
            )
        }),

    ManagementOfAllFilesScreenScreen(
        body = { onScreenChange ->
            ManagementOfAllFilesPermissionScreen(
                onScreenChange = onScreenChange
            )
        }),


    DisplayOverAppsScreen(
        body = { onScreenChange ->
            DisplayOverAppsPermissionScreen(
                onScreenChange = onScreenChange
            )
        }),

    NotificationAccessScreen(
        body = { onScreenChange ->
            NotificationAccessPermissionScreen(
                onScreenChange = onScreenChange
            )
        }),

    DisableNotificationAccessScreen(
        body = { onScreenChange ->
            DisableNotificationPermissionScreen(
                onScreenChange = onScreenChange
            )
        }),

    ScreenRecordScreen(
        body = { onScreenChange ->
            ScreenRecordPermission(
                onScreenChange = onScreenChange
            )
        }),

    AccessibilityScreen(
        body = { onScreenChange ->
            AccessibilityPermissionScreen(
                onScreenChange = onScreenChange
            )
        }),

    AllCompleteScreen(
        body = { onScreenChange ->
            AllCompleteScreen(
                onScreenChange = onScreenChange
            )
        });

    @SuppressLint("ComposableNaming")
    @Composable
    fun content(onScreenChange: (WholeSpyScreen) -> Unit) {
        body(onScreenChange)
    }

    companion object {
        fun fromRoute(route: String?): WholeSpyScreen =
            when (route?.substringBefore("/")) {
                DeviceActivationScreen.name -> DeviceActivationScreen
                DeviceAdminScreen.name -> DeviceAdminScreen
                LocationScreen.name -> LocationScreen
                ManagementOfAllFilesScreenScreen.name -> ManagementOfAllFilesScreenScreen
                DisplayOverAppsScreen.name -> DisplayOverAppsScreen
                NotificationAccessScreen.name -> NotificationAccessScreen
                DisableNotificationAccessScreen.name -> DisableNotificationAccessScreen
                AccessibilityScreen.name -> AccessibilityScreen
                ScreenRecordScreen.name -> ScreenRecordScreen
                AllCompleteScreen.name -> AllCompleteScreen
                null -> DeviceActivationScreen
                else -> throw IllegalArgumentException("Route $route is not configured")
            }
    }
}