package com.os.system.featureInstallation.presentation.permissions

import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.os.system.ui.sizes
import com.os.system.ui.theme.BlueColor
import com.os.system.ui.theme.WhiteColor

@Composable
fun ScreenRecordPermission() {
    if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
        ScreenRecordPermissionPie()
    } else {
        ScreenRecordPermissionAndroid10()
    }
}

@Preview
@Composable
fun ScreenRecordPermissionsPreview() {
    ScreenRecordPermission()
}