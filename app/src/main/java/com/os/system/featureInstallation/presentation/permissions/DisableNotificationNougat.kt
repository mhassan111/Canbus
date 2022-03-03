package com.os.system.featureInstallation.presentation.permissions

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.os.system.R
import com.os.system.ui.theme.BlackGray
import com.os.system.ui.theme.DarkBlackGray
import com.os.system.ui.theme.LightGrayColor
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination
fun DisableNotificationNougat(navigator: DestinationsNavigator) {

    Box(modifier = Modifier.background(Color.White)) {
        Column {
            Box(
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()
                    .padding(24.dp)
                    .border(
                        width = 10.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp)
                    )
                    .clip(RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp))
                    .background(LightGrayColor)
            ) {
                Text(text = "aa")
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(15.dp)
                    .border(
                        width = 10.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp)
                    )
                    .clip(RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
                    .background(Color.White)
                    .padding(horizontal = 0.dp, vertical = 10.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    DisableNotificationNougatBar()
                    AppIconAndNameTitleSection()
                    BlockNotificationsSection(modifier = Modifier.weight(1f))
                }
            }
        }
    }
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