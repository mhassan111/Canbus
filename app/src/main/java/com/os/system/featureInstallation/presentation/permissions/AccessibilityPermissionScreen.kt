package com.os.system.featureInstallation.presentation.permissions

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.os.system.ui.theme.LightGrayColor
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun AccessibilityPermissionScreen(navigator: DestinationsNavigator) {

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
                    .padding(24.dp)
                    .border(
                        width = 10.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp)
                    )
                    .clip(RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
                    .background(LightGrayColor)
                    .padding(horizontal = 10.dp, vertical = 10.dp)
            ) {
                Column(modifier = Modifier.fillMaxSize()) {
                    Title()
                    PermissionSection()
                    BottomSection(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
fun Title() {
    Row(
        modifier = Modifier
            .padding(top = 10.dp, bottom = 0.dp)
            .fillMaxWidth()
            .padding(horizontal = 15.dp, vertical = 5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Icon(
            imageVector = Icons.Filled.ArrowBack, contentDescription = "Back Arrow",
            modifier = Modifier
                .width(30.dp)
                .height(30.dp)
        )
        Spacer(modifier = Modifier.width(15.dp))
        Text(
            modifier = Modifier.weight(1f),
            text = "Android System Manager",
            color = Color.Black,
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Default
        )
    }
}

@Composable
fun PermissionSection() {
    Row(
        modifier = Modifier
            .padding(top = 10.dp, bottom = 0.dp)
            .background(Color.White)
            .fillMaxWidth()
            .padding(horizontal = 15.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Off",
            color = Color.Black,
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Default
        )
        Switch(checked = true, onCheckedChange = {})
    }
}

@Composable
fun BottomSection(modifier: Modifier) {
    Column(
        modifier = modifier
            .padding(top = 5.dp)
            .fillMaxSize()
            .padding(horizontal = 15.dp, vertical = 10.dp)
    ) {
        Text(
            text = "No description provided.",
            color = Color.Black,
            fontSize = 15.sp,
            fontFamily = FontFamily.Default
        )
    }
}