package com.screening.app.featureInstallation.presentation.permissions

import android.os.Build
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.screening.app.featureInstallation.WholeSpyScreen

@Composable
fun ScreenRecordPermission(
    onScreenChange: (WholeSpyScreen) -> Unit
) {
    if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
        ScreenRecordPermissionPie(onScreenChange = onScreenChange)
    } else {
        ScreenRecordPermissionAndroid10(onScreenChange = onScreenChange)
    }
}

@Preview
@Composable
fun ScreenRecordPermissionsPreview() {

}