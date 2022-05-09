package com.screening.app.featureInstallation.presentation.permissions.components

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
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.screening.app.featureInstallation.presentation.permissions.*
import com.screening.app.ui.sizes
import com.screening.app.ui.spacing
import com.screening.app.ui.theme.BlackColor
import com.screening.app.ui.theme.SlateBlue
import com.screening.app.ui.theme.LightGrayColor
import com.screening.app.ui.theme.WhiteColor
import com.screening.app.utilities.AppConstants
import com.screening.app.utilities.buildAnnotatedText
import com.screening.app.utilities.incrementOne

@Composable
fun PermissionFrame(
    screen: Screen,
    content: @Composable () -> Unit,
    skipPermission: () -> Unit,
    allowPermission: (Screen) -> Unit
) {

    Box(modifier = Modifier
        .background(MaterialTheme.colors.onPrimary)
        .systemBarsPadding()) {

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
                        .background(SlateBlue)
                        .padding(
                            horizontal = MaterialTheme.spacing.small,
                            vertical = MaterialTheme.spacing.small
                        )
                ) {
                    PermissionScreenFooter(
                        permissionNumber = getPermissionNumber(screen),
                        annotatedPermissionDetailText = permissionTexts[screen.javaClass.simpleName]!!,
                        permissionButtonText = getPermissionButtonText(screen)
                    ) {
                        allowPermission(screen)
                    }
                }
            }
        }
    }
}

/** Get the Allow Permission Button Text w.r.t to Permission **/
fun getPermissionButtonText(screen: Screen): String {
    return when (screen) {
        Screen.LocationScreen -> {
            "Allow Location Permission"
        }
        Screen.DeviceActivationScreen -> {
            "Activate Device Admin"
        }
        Screen.ManagementOfAllFilesScreenScreen -> {
            "Allow Management Of all Files"
        }
        Screen.NotificationAccessScreen -> {
            "Allow Notification Access"
        }
        Screen.DisableNotificationAccessScreen -> {
            if (AppConstants.osGreaterThanEqualToOreo)
                "Disable Notifications"
            else
                "Block Notifications"
        }
        Screen.AccessibilityScreen -> {
            "Allow Accessibility Access"
        }
        Screen.ScreenRecordScreen -> {
            "Allow Screen Record Permission"
        }
        else -> {
            "Allow"
        }
    }
}

fun getPermissionNumber(screen: Screen): AnnotatedString {
    val totalPermissions = permissionTexts.size
    val permissionKeys = permissionTexts.keys
    val permissionNumber =
        permissionKeys.indexOf(screen.javaClass.simpleName).incrementOne()
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
            Screen.LocationScreen.javaClass.simpleName,
            listOf(
                AnnotatedText(text = "Kindly Allow the "),
                AnnotatedText(text = "Allow all the time ", annotate = true),
                AnnotatedText(text = "Location Permission for "),
                AnnotatedText(text = "Android System Manager ", annotate = true),
            ).buildAnnotatedText()
        )

        // Management Of all Files
        put(
            Screen.ManagementOfAllFilesScreenScreen.javaClass.simpleName,
            listOf(
                AnnotatedText(text = "Kindly Allow the "),
                AnnotatedText(text = "Management of All Files Permission ", annotate = true),
                AnnotatedText(text = "for "),
                AnnotatedText(text = "Android System Manager ", annotate = true),
            ).buildAnnotatedText()
        )

        // Device Admin
        put(
            Screen.DeviceAdminScreen.javaClass.simpleName,
            listOf(
                AnnotatedText(text = "Kindly Activate the "),
                AnnotatedText(text = "System Manager App ", annotate = true),
                AnnotatedText(text = "as Device Admin App")
            ).buildAnnotatedText()
        )

        // Display Over Apps
        put(
            Screen.DisplayOverAppsScreen.javaClass.simpleName,
            listOf(
                AnnotatedText("Kindly Allow the "),
                AnnotatedText("Display Over other Apps / Appear on top ", annotate = true),
                AnnotatedText("Permission for ", annotate = false),
                AnnotatedText("Android System Manager ", annotate = true),
            ).buildAnnotatedText()
        )

        // NotificationAccess
        put(
            Screen.NotificationAccessScreen.javaClass.simpleName,
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
                Screen.DisableNotificationAccessScreen.javaClass.simpleName,
                listOf(
                    AnnotatedText(text = "Kindly Disable the "),
                    AnnotatedText(text = "Show Notifications ", annotate = true),
                    AnnotatedText(text = "for "),
                    AnnotatedText(text = "Android System Manager ", annotate = true),
                ).buildAnnotatedText()
            )
        } else {
            put(
                Screen.DisableNotificationAccessScreen.javaClass.simpleName,
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
            Screen.AccessibilityScreen.javaClass.simpleName,
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
                Screen.ScreenRecordScreen.javaClass.simpleName,
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
                Screen.ScreenRecordScreen.javaClass.simpleName,
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