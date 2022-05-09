package com.screening.app.featureInstallation.presentation.permissions

import android.os.Build
import androidx.compose.runtime.Composable
import com.screening.app.featureInstallation.WholeSpyScreen


@Composable
fun DisableNotificationPermissionScreen(
    onScreenChange: (WholeSpyScreen) -> Unit
) {
    if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.Q) {
        DisableNotificationNougat(onScreenChange = onScreenChange)
    } else {
        DisableNotificationOreoScreen(onScreenChange = onScreenChange)
    }
}