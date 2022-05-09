package com.screening.app.featureInstallation.presentation.permissions

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.screening.app.R
import com.screening.app.featureInstallation.WholeSpyScreen
import com.screening.app.featureInstallation.presentation.permissions.components.PermissionFrame
import com.screening.app.ui.theme.BlackGray
import com.screening.app.ui.theme.DarkBlackGray
import com.screening.app.utilities.logVerbose

@Composable
fun DisableNotificationNougat(
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
                DisableNotificationNougatBar()
                AppIconAndNameTitleSection()
                BlockNotificationsSection(modifier = Modifier.weight(1f))
            }
        },
        skipPermission = {
            currentContext.logVerbose("skip management of all files permission")
        },
        allowPermission = {
            currentContext.logVerbose("allow management of all files permission")

            val intent = Intent()
            intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
            intent.addCategory(Intent.CATEGORY_DEFAULT)
            intent.data = Uri.parse("package:${currentContext.packageName}")
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent.flags = Intent.FLAG_ACTIVITY_NO_HISTORY
            intent.flags = Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS
            launcher.launch(intent)

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
//                    .padding(15.dp)
//                    .border(
//                        width = 10.dp,
//                        color = Color.Black,
//                        shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp)
//                    )
//                    .clip(RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
//                    .background(Color.White)
//                    .padding(horizontal = 0.dp, vertical = 10.dp)
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
fun DisableNotificationNougatBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(DarkBlackGray)
            .padding(horizontal = 15.dp, vertical = 15.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {

        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = "Back Arrow",
            modifier = Modifier
                .size(20.dp),
            tint = Color.White
        )

        Spacer(modifier = Modifier.width(15.dp))

        Text(
            modifier = Modifier.weight(1f),
            text = "Notifications",
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = FontFamily.Default
        )

        Icon(
            imageVector = Icons.Filled.MoreVert,
            contentDescription = "Search Icon",
            modifier = Modifier
                .size(20.dp)
                .align(Alignment.CenterVertically),
            tint = Color.White
        )
    }
}

@Composable
fun AppIconAndNameTitleSection() {

    Row(
        modifier = Modifier
            .background(BlackGray)
            .padding(horizontal = 20.dp, vertical = 5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {

        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "App Logo",
            Modifier
                .size(width = 35.dp, height = 35.dp)
                .clip(RoundedCornerShape(35.dp))
        )

        Spacer(modifier = Modifier.width(15.dp))

        Text(
            text = "Android System Manager",
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Start
        )
    }
}

@Composable
fun BlockNotificationsSection(modifier: Modifier) {

    Column(
        modifier = Modifier.padding(0.dp),
        verticalArrangement = Arrangement.Top
    ) {

        Box() {

            NotificationPermissionDetail(
                title = "Block all",
                detail = "Never show notifications from this app",
                switchState = true
            )

            Column(
                modifier = Modifier
                    .matchParentSize()
                    .padding(end = 50.dp, bottom = 0.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.End
            ) {
                Image(
                    modifier = Modifier
                        .size(30.dp)
                        .offset(10.dp),
                    painter = painterResource(id = R.drawable.hand_pointer),
                    contentDescription = null,
                )
            }
        }

        Divider(color = Color.LightGray, thickness = 1.dp)

        NotificationPermissionDetail(
            title = "Show silently",
            detail = "Don't make sound, vibrate or peek these notifications into view on the current screen.",
            switchState = false
        )

        Divider(color = Color.LightGray, thickness = 1.dp)

        NotificationPermissionDetail(
            title = "Override Do Not Disturb",
            detail = "Let these notifications continue to interrupt when Do Not Disturb is set to Priority only",
            switchState = false
        )

    }
}

@Composable
fun NotificationPermissionDetail(title: String, detail: String, switchState: Boolean) {

    Row(
        modifier = Modifier
            .padding(horizontal = 30.dp, vertical = 15.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                color = Color.Black,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Start
            )

            Text(
                text = detail,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                color = Color.Black,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Start
            )
        }

        Switch(
            checked = switchState, onCheckedChange = {},
            modifier = Modifier.size(30.dp)
        )
    }
}