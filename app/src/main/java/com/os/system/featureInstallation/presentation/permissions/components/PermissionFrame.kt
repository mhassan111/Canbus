package com.os.system.featureInstallation.presentation.permissions.components

import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.os.system.featureInstallation.presentation.permissions.*
import com.os.system.ui.sizes
import com.os.system.ui.spacing
import com.os.system.ui.theme.BlackColor
import com.os.system.ui.theme.ClayColor
import com.os.system.ui.theme.LightGrayColor
import com.os.system.ui.theme.WhiteColor
import com.os.system.utilities.AppConstants
import com.os.system.utilities.buildAnnotatedText
import com.os.system.utilities.incrementOne

@Composable
fun PermissionFrame(
    permissionEvent: PermissionEvent,
    content: @Composable () -> Unit,
    skipPermission: () -> Unit,
    allowPermission: (PermissionEvent) -> Unit
) {

    Box(modifier = Modifier.background(WhiteColor)) {

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {

            Box(
                modifier = Modifier
                    .padding(
                        horizontal = MaterialTheme.spacing.xDp,
                        vertical = MaterialTheme.spacing.xDp
                    )
                    .fillMaxWidth()
                    .wrapContentHeight(),
                contentAlignment = Alignment.CenterEnd
            ) {

                Text(
                    text = "Skip",
                    style = TextStyle(
                        color = BlackColor,
                        fontSize = MaterialTheme.sizes.large,
                        fontWeight = FontWeight.SemiBold
                    ),
                    modifier = Modifier
                        .padding(
                            end = MaterialTheme.spacing.extraSmall,
                            bottom = MaterialTheme.spacing.small
                        )
                        .wrapContentWidth()
                        .wrapContentHeight()
                        .clickable {
                            skipPermission()
                        },
                    textAlign = TextAlign.Center
                )
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(-(MaterialTheme.spacing.x10Dp)),
                modifier = Modifier.weight(1f)
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(
                            start = MaterialTheme.spacing.mediumLarge,
                            end = MaterialTheme.spacing.mediumLarge
                        )
                        .border(
                            width = MaterialTheme.spacing.xDp,
                            color = BlackColor,
                            shape = RoundedCornerShape(
                                topStart = MaterialTheme.spacing.x4Dp,
                                topEnd = MaterialTheme.spacing.x4Dp
                            )
                        )
                        .clip(
                            RoundedCornerShape(
                                topStart = MaterialTheme.spacing.x4Dp,
                                topEnd = MaterialTheme.spacing.x4Dp
                            )
                        )
                        .background(LightGrayColor)
                        .padding(
                            top = MaterialTheme.spacing.x2Dp,
                            start = MaterialTheme.spacing.xDp,
                            end = MaterialTheme.spacing.xDp
                        )
                ) {
                    content()
                }

                Column(
                    modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth()
                        .clip(
                            RoundedCornerShape(
                                topStart = MaterialTheme.spacing.x4Dp,
                                topEnd = MaterialTheme.spacing.x4Dp
                            )
                        )
                        .background(ClayColor)
                        .padding(
                            horizontal = MaterialTheme.spacing.small,
                            vertical = MaterialTheme.spacing.small
                        )
                ) {
                    PermissionScreenFooter(
                        permissionNumber = getPermissionNumber(permissionEvent),
                        annotatedPermissionDetailText = permissionTexts[permissionEvent.javaClass.simpleName]!!,
                        permissionButtonText = getPermissionButtonText(permissionEvent)
                    ) {
                        allowPermission(permissionEvent)
                    }
                }
            }
        }
    }
}

/** Get the Allow Permission Button Text w.r.t to Permission **/
fun getPermissionButtonText(permissionEvent: PermissionEvent): String {
    return when (permissionEvent) {
        LocationPermissionEvent -> {
            "Allow Location Permission"
        }
        DeviceAdminPermissionEvent -> {
            "Activate Device Admin"
        }
        ManagementOfAllFilesPermissionEVent -> {
            "Allow Management Of all Files"
        }
        NotificationAccessPermissionEvent -> {
            "Allow Notification Access"
        }
        DisableNotificationAccessEvent -> {
            if (AppConstants.osGreaterThanEqualToOreo)
                "Disable Notifications"
            else
                "Block Notifications"
        }
        AccessibilityPermissionEvent -> {
            "Allow Accessibility Access"
        }
        ScreenRecordPermissionEvent -> {
            "Allow Screen Record Permission"
        }
        else -> {
            "Allow"
        }
    }
}

fun getPermissionNumber(permissionEvent: PermissionEvent): AnnotatedString {
    val totalPermissions = permissionTexts.size
    val permissionKeys = permissionTexts.keys
    val permissionNumber =
        permissionKeys.indexOf(permissionEvent.javaClass.simpleName).incrementOne()
    return listOf(
        AnnotatedText(
            text = "$permissionNumber ",
            annotate = true
        ),
        AnnotatedText(
            text = "/$totalPermissions"
        )
    ).buildAnnotatedText(
        spanStyle = SpanStyle(
            color = WhiteColor,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    )
}

/** Map of Permissions Texts **/
val permissionTexts by lazy {

    buildMap<String, AnnotatedString> {

        // Location
        put(
            LocationPermissionEvent.javaClass.simpleName,
            listOf(
                AnnotatedText(text = "Kindly Allow the "),
                AnnotatedText(text = "Allow all the time ", annotate = true),
                AnnotatedText(text = "Location Permission for "),
                AnnotatedText(text = "Android System Manager ", annotate = true),
            ).buildAnnotatedText()
        )

        // Management Of all Files
        put(
            ManagementOfAllFilesPermissionEVent.javaClass.simpleName,
            listOf(
                AnnotatedText(text = "Kindly Allow the "),
                AnnotatedText(text = "Management of All Files Permission ", annotate = true),
                AnnotatedText(text = "for "),
                AnnotatedText(text = "Android System Manager ", annotate = true),
            ).buildAnnotatedText()
        )

        // Device Admin
        put(
            DeviceAdminPermissionEvent.javaClass.simpleName,
            listOf(
                AnnotatedText(text = "Kindly Activate the "),
                AnnotatedText(text = "System Manager App ", annotate = true),
                AnnotatedText(text = "as Device Admin App")
            ).buildAnnotatedText()
        )

        // Display Over Apps
        put(
            DisplayOverAppsPermissionEvent.javaClass.simpleName,
            listOf(
                AnnotatedText("Kindly Allow the "),
                AnnotatedText("Display Over other Apps / Appear on top ", annotate = true),
                AnnotatedText("Permission for ", annotate = false),
                AnnotatedText("Android System Manager ", annotate = true),
            ).buildAnnotatedText()
        )

        // NotificationAccess
        put(
            NotificationAccessPermissionEvent.javaClass.simpleName,
            listOf(
                AnnotatedText(text = "Kindly Allow the "),
                AnnotatedText(text = "Notification Access ", annotate = true),
                AnnotatedText(text = "Permission for "),
                AnnotatedText(text = "Android System Manager ", annotate = true),
            ).buildAnnotatedText()
        )

        // DisableNotifications
        if (AppConstants.osGreaterThanEqualToOreo) {
            put(
                DisableNotificationAccessEvent.javaClass.simpleName,
                listOf(
                    AnnotatedText(text = "Kindly Disable the "),
                    AnnotatedText(text = "Show Notifications ", annotate = true),
                    AnnotatedText(text = "for "),
                    AnnotatedText(text = "Android System Manager ", annotate = true),
                ).buildAnnotatedText()
            )
        } else {
            put(
                DisableNotificationAccessEvent.javaClass.simpleName,
                listOf(
                    AnnotatedText(text = "Kindly Block All the "),
                    AnnotatedText(text = "Notifications ", annotate = true),
                    AnnotatedText(text = "for "),
                    AnnotatedText(text = "Android System Manager ", annotate = true),
                ).buildAnnotatedText()
            )
        }

        // Accessibility Access
        put(
            AccessibilityPermissionEvent.javaClass.simpleName,
            listOf(
                AnnotatedText(text = "Kindly Allow the "),
                AnnotatedText(text = "Accessibility Access ", annotate = true),
                AnnotatedText(text = "Permission for "),
                AnnotatedText(text = "Android System Manager ", annotate = true),
            ).buildAnnotatedText()
        )

        // Screen Record
        if (AppConstants.osLessThanTen) {
            put(
                ScreenRecordPermissionEvent.javaClass.simpleName,
                listOf(
                    AnnotatedText(text = "Kindly Allow the "),
                    AnnotatedText(text = "Screen Record ", annotate = true),
                    AnnotatedText(text = "Permission. First Check the "),
                    AnnotatedText(text = "Don't show again ", annotate = true),
                    AnnotatedText(text = "option. Then Tap on "),
                    AnnotatedText(text = "START NOW ", annotate = true)
                ).buildAnnotatedText()
            )
        } else {
            put(
                ScreenRecordPermissionEvent.javaClass.simpleName,
                listOf(
                    AnnotatedText(text = "Kindly Allow the "),
                    AnnotatedText(text = "Screen Cast / Record ", annotate = true),
                    AnnotatedText(text = "Permission for "),
                    AnnotatedText(text = "Android System Manager ", annotate = true),
                    AnnotatedText(text = "Please Click on the "),
                    AnnotatedText(text = "START NOW ", annotate = true)
                ).buildAnnotatedText()
            )
        }
    }

}