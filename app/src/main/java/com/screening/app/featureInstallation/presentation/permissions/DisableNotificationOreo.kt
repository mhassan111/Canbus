package com.screening.app.featureInstallation.presentation.permissions

import android.app.Activity
import android.content.Intent
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.screening.app.R
import com.screening.app.featureInstallation.WholeSpyScreen
import com.screening.app.featureInstallation.presentation.permissions.components.PermissionFrame
import com.screening.app.ui.sizes
import com.screening.app.ui.spacing
import com.screening.app.ui.theme.*
import com.screening.app.utilities.logVerbose

@Composable
fun DisableNotificationOreoScreen(
    onScreenChange: (WholeSpyScreen) -> Unit
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
                AppNotificationsBar()
                AppNotificationAppNameAndIcon()
                AppNotificationPermissionSwitches(
                    modifier = Modifier
                        .weight(1f)
                        .padding(
                            top = MaterialTheme.spacing.x2Dp,
                            bottom = MaterialTheme.spacing.x2Dp
                        )
                )
            }
        },
        skipPermission = {
            currentContext.logVerbose("skip management of all files permission")
        },
        allowPermission = {
            currentContext.logVerbose("allow management of all files permission")

            val notificationIntent: Intent = Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS)
                .putExtra(Settings.EXTRA_APP_PACKAGE, currentContext.packageName)
                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                .setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
                .setFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
            launcher.launch(notificationIntent)

//            val intent = Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION)
//            intent.addCategory("android.intent.category.DEFAULT")
//            intent.data = Uri.parse(String.format("package:%s", currentContext.packageName))
//            launcher.launch(intent)
        }
    )

//    Box(modifier = Modifier.background(Color.White)) {
//        Column {
//            Box(
//                modifier = Modifier
//                    .height(100.dp)
//                    .fillMaxWidth()
//                    .padding(24.dp)
//                    .border(
//                        width = 10.dp,
//                        color = Color.Black,
//                        shape = RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp)
//                    )
//                    .clip(RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp))
//                    .background(LightGrayColor)
//            ) {
//                Text(text = "aa")
//            }
//
//            Box(
//                modifier = Modifier
//                    .weight(1f)
//                    .padding(MaterialTheme.spacing.medium)
//                    .border(
//                        width = MaterialTheme.spacing.xDp,
//                        color = Color.Black,
//                        shape = RoundedCornerShape(
//                            topStart = MaterialTheme.spacing.x4Dp,
//                            topEnd = MaterialTheme.spacing.x4Dp
//                        )
//                    )
//                    .clip(
//                        RoundedCornerShape(
//                            topStart = MaterialTheme.spacing.x4Dp,
//                            topEnd = MaterialTheme.spacing.x4Dp
//                        )
//                    )
//                    .background(LightGrayColor)
//                    .padding(
//                        horizontal = MaterialTheme.spacing.x2Dp,
//                        vertical = MaterialTheme.spacing.x2Dp
//                    )
//            ) {
//                Column(
//                    modifier = Modifier.fillMaxSize()
//                ) {
//
//                }
//            }
//        }
//    }
}

@Composable
fun AppNotificationsBar(title: String = "App Notifications") {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = MaterialTheme.spacing.default,
                vertical = MaterialTheme.spacing.default
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {

        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = "Back Arrow",
            modifier = Modifier.size(MaterialTheme.spacing.x2Dp),
            tint = BlackColor
        )

        Spacer(modifier = Modifier.width(15.dp))

        Text(
            modifier = Modifier.weight(1f),
            text = title,
            color = BlackColor,
            fontSize = MaterialTheme.sizes.mediumLarge,
            fontWeight = FontWeight.SemiBold,
            fontFamily = FontFamily.Default
        )
    }
}

@Composable
fun AppNotificationAppNameAndIcon() {

    Column(
        modifier = Modifier
            .padding(MaterialTheme.spacing.x4Dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "App Logo",
            Modifier
                .size(
                    width = MaterialTheme.spacing.x4Dp,
                    height = MaterialTheme.spacing.x4Dp
                )
                .clip(RoundedCornerShape(MaterialTheme.spacing.x5Dp))
        )

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.x2Dp))

        Text(
            text = "Android System Manager",
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            color = BlackColor,
            fontSize = MaterialTheme.sizes.extraLarge,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun AppNotificationPermissionSwitches(modifier: Modifier) {
    Column(modifier = modifier) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .clip(
                    RoundedCornerShape(
                        MaterialTheme.spacing.medium
                    )
                )
                .background(
                    SkyBlueColor
                )
                .padding(MaterialTheme.spacing.medium),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "Show notifications",
                modifier = Modifier
                    .weight(1f)
                    .wrapContentHeight(),
                color = WhiteColor,
                fontSize = MaterialTheme.sizes.medium,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Start
            )

            Switch(checked = false, onCheckedChange = {})
        }

        Text(
            text = "Categories",
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(
                    start = MaterialTheme.spacing.medium,
                    top = MaterialTheme.spacing.medium
                ),
            color = GrayColor,
            fontSize = MaterialTheme.sizes.medium,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Start
        )

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.xDp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .clip(
                    RoundedCornerShape(
                        MaterialTheme.spacing.xDp
                    )
                )
                .background(
                    WhiteColor
                )
                .padding(MaterialTheme.spacing.small),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "Android System Service",
                modifier = Modifier
                    .weight(1f)
                    .wrapContentHeight(),
                color = BlackColor,
                fontSize = MaterialTheme.sizes.mediumLarge,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Start
            )

            Row(modifier = Modifier.height(IntrinsicSize.Max)) {

                Divider(
                    Modifier
                        .width(1.dp)
                        .fillMaxHeight()
                        .background(GrayColor)
                )

                Switch(
                    checked = false,
                    onCheckedChange = {},
                    modifier = Modifier.padding(start = MaterialTheme.spacing.small)
                )
            }
        }
    }
}