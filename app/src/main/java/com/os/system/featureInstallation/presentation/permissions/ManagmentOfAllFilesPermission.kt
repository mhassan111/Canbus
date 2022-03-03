package com.os.system.featureInstallation.presentation.permissions

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.os.system.R
import com.os.system.featureInstallation.presentation.permissions.components.PermissionFrame
import com.os.system.ui.sizes
import com.os.system.ui.spacing
import com.os.system.ui.theme.LightGrayColor
import com.os.system.ui.theme.LighterGrayColor
import com.os.system.ui.theme.BlueShining
import com.os.system.utilities.AppConstants
import com.os.system.utilities.logVerbose
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination
fun ManagementOfAllFilesPermissionScreen(navigator: DestinationsNavigator) {

    // Local Context
    val currentContext = LocalContext.current



    PermissionFrame(
        permissionEvent = ManagementOfAllFilesPermissionEVent,
        content = {
            Column {
                ManagementOfAllFilesAppBar()
                AllFilePermissionAppNameAndIconSection()
                ManagementOfAllFilePermissionSection(
                    modifier = Modifier
                        .background(LighterGrayColor)
                        .padding(15.dp)
                        .weight(1f)
                        .padding(vertical = MaterialTheme.spacing.x2Dp)
                )
            }
        },
        skipPermission = {
            currentContext.logVerbose("skip management of all files permission")
        },
        allowPermission = {
            currentContext.logVerbose("allow management of all files permission")
        }
    )
}

@Composable
fun ManagementOfAllFilesAppBar() {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = MaterialTheme.spacing.xDp,
                vertical = MaterialTheme.spacing.extraSmall
            ),
        verticalAlignment = CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {

        Icon(
            imageVector = Icons.Filled.ArrowBack, contentDescription = "Back Arrow",
            modifier = Modifier
                .size(MaterialTheme.spacing.x3Dp)
        )

        Spacer(modifier = Modifier.width(MaterialTheme.spacing.medium))

        Text(
            modifier = Modifier.weight(1f),
            text = "Files and media Permission",
            color = Color.Black,
            fontSize = MaterialTheme.sizes.mediumLarge,
            fontWeight = FontWeight.Bold
        )

        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = "Search Icon",
            modifier = Modifier
                .size(MaterialTheme.spacing.x2Dp)
                .align(CenterVertically)
        )
    }
}

@Composable
fun AllFilePermissionAppNameAndIconSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(LightGrayColor)
            .padding(
                top = MaterialTheme.spacing.xDp,
                bottom = MaterialTheme.spacing.x2Dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {

        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "App Logo",
            Modifier
                .size(MaterialTheme.spacing.x4Dp)
                .clip(RoundedCornerShape(MaterialTheme.spacing.x4Dp))
        )

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.xDp))

        Text(
            text = "Android System Manager",
            modifier = Modifier.fillMaxWidth(),
            fontSize = MaterialTheme.sizes.large,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun ManagementOfAllFilePermissionSection(modifier: Modifier) {

    Column(modifier = modifier) {

        Text(
            text = "FILES AND MEDIA ACCESS FOR THIS APP",
            modifier = Modifier
                .fillMaxWidth()
                .offset(MaterialTheme.spacing.x4Dp),
            color = BlueShining,
            fontSize = MaterialTheme.sizes.extraSmall,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.x2Dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            contentAlignment = CenterStart
        ) {

            RadioButton(
                selected = true,
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .size(MaterialTheme.spacing.x2Dp),
            )

            Box(
                contentAlignment = CenterStart,
                modifier = Modifier
                    .wrapContentWidth()
                    .height(MaterialTheme.spacing.x3Dp)
                    .offset(MaterialTheme.spacing.x4Dp)
            ) {
                Text(
                    text = "Allow management of all files",
                    fontSize = MaterialTheme.sizes.mediumLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .wrapContentWidth()
                        .wrapContentHeight()
                )
            }

            Image(
                modifier = Modifier
                    .size(MaterialTheme.spacing.x3Dp)
                    .offset((LocalConfiguration.current.screenWidthDp - 115).dp),
                painter = painterResource(id = R.drawable.hand_pointer),
                contentDescription = null,
            )
        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.xDp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            contentAlignment = CenterStart
        ) {

            RadioButton(
                selected = false,
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .size(MaterialTheme.spacing.x2Dp)
            )

            Box(
                contentAlignment = CenterStart,
                modifier = Modifier
                    .wrapContentWidth()
                    .height(MaterialTheme.spacing.x3Dp)
                    .offset(MaterialTheme.spacing.x4Dp)
            ) {
                Text(
                    text = "Allow access to media only",
                    fontSize = MaterialTheme.sizes.mediumLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .wrapContentWidth()
                        .wrapContentHeight()
                )
            }
        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.xDp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            contentAlignment = CenterStart
        ) {

            RadioButton(
                selected = false,
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .size(MaterialTheme.spacing.x2Dp)
            )
            Box(
                contentAlignment = CenterStart,
                modifier = Modifier
                    .wrapContentWidth()
                    .height(MaterialTheme.spacing.x3Dp)
                    .offset(MaterialTheme.spacing.x4Dp)
            ) {
                Text(
                    text = "Deny",
                    fontSize = MaterialTheme.sizes.mediumLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .wrapContentWidth()
                        .wrapContentHeight()
                )
            }
        }
    }
}