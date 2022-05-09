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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.screening.app.featureInstallation.WholeSpyScreen
import com.screening.app.featureInstallation.presentation.permissions.components.PermissionFrame
import com.screening.app.ui.sizes
import com.screening.app.ui.spacing
import com.screening.app.ui.theme.LightGrayColor
import com.screening.app.ui.theme.LighterGrayColor
import com.screening.app.ui.theme.BlueShining
import com.screening.app.utilities.logVerbose
import com.screening.app.R


@Composable
fun ManagementOfAllFilesPermissionScreen(
    viewModel: MainViewModel = hiltViewModel(),
    onScreenChange: (WholeSpyScreen) -> Unit
) {

    // Local Context
    val currentContext = LocalContext.current

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
        onResult = { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                currentContext.logVerbose("display over apps permission granted")
                val data: Intent? = result.data
//                onScreenChange(WholeSpyScreen.DisplayOverAppsScreen)
            } else if (result.resultCode == Activity.RESULT_CANCELED) {
                // Permission Request cancelled
                currentContext.logVerbose("display over apps permission cancelled")
            }
        }
    )

    PermissionFrame(
        screen = Screen.ManagementOfAllFilesScreenScreen,
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
            val intent = Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION)
            intent.addCategory("android.intent.category.DEFAULT")
            intent.data = Uri.parse(String.format("package:%s", currentContext.packageName))
            launcher.launch(intent)
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