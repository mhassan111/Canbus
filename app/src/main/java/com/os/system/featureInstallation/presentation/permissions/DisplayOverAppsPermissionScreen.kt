package com.os.system.featureInstallation.presentation.permissions

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.os.system.R
import com.os.system.featureInstallation.presentation.util.Screen
import com.os.system.ui.sizes
import com.os.system.ui.spacing
import com.os.system.ui.theme.*
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@ExperimentalPermissionsApi
@Composable
@Destination
fun DisplayOverAppsPermissionScreen(
    navigator: DestinationsNavigator,
    viewModel: PermissionsViewModel = hiltViewModel()
) {

    val localContext = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    // Current Lifecycle owner
    val lifeCycleOwner = LocalLifecycleOwner.current

}

@Preview
@Composable
fun DisplayOverAppsPermissionScreenPreview() {
    Box(modifier = Modifier.background(WhiteColor)) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {

            Box(
                modifier = Modifier
                    .padding(
                        horizontal = MaterialTheme.spacing.medium,
                        vertical = MaterialTheme.spacing.xDp
                    )
                    .fillMaxWidth()
                    .wrapContentHeight(),
                contentAlignment = Alignment.CenterEnd
            ) {

                Box(
                    modifier = Modifier
                        .width(MaterialTheme.spacing.x4Dp)
                        .height(MaterialTheme.spacing.x4Dp)
                        .clickable {

                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Skip",
                        style = TextStyle(
                            color = BlackColor,
                            fontSize = MaterialTheme.sizes.mediumLarge,
                            fontWeight = FontWeight.SemiBold
                        ),
                        modifier = Modifier
                            .wrapContentWidth()
                            .wrapContentHeight(),
                        textAlign = TextAlign.Center
                    )
                }
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(-(MaterialTheme.spacing.x10Dp)),
                modifier = Modifier.weight(1f)
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(
                            start = MaterialTheme.spacing.large,
                            end = MaterialTheme.spacing.large
                        )
                        .border(
                            width = MaterialTheme.spacing.xDp,
                            color = Color.Black,
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

                    Column(
                        modifier = Modifier.padding(
                            start = MaterialTheme.spacing.xDp,
                            end = MaterialTheme.spacing.xDp
                        )
                    ) {
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
                }

                Column(
                    modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth()
                        .clip(
                            RoundedCornerShape(
                                topStart = MaterialTheme.spacing.x5Dp,
                                topEnd = MaterialTheme.spacing.x5Dp
                            )
                        )
                        .background(ClayColor)
                        .padding(
                            horizontal = MaterialTheme.spacing.xDp,
                            vertical = MaterialTheme.spacing.xDp
                        )
                ) {
                }
            }
        }
    }
}

@Composable
fun DisplayOverAppsPermissionTitle() {
    AppNotificationsBar(title = "Appear on top")
}

@Composable
fun DisplayOverAppsPermissionDetail(modifier: Modifier) {

    Column(modifier = modifier) {

        Row(
            modifier = Modifier
                .padding(
                    top = MaterialTheme.spacing.mediumLarge,
                    bottom = MaterialTheme.spacing.x2Dp
                )
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "App Logo",
                Modifier
                    .size(width = 35.dp, height = 35.dp)
                    .clip(RoundedCornerShape(35.dp))
            )

            Spacer(modifier = Modifier.width(MaterialTheme.spacing.xDp))

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Android System Manager",
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = MaterialTheme.sizes.medium,
                    fontWeight = FontWeight.Normal
                )
                Text(
                    text = "39.23.32 (232329-959282923)",
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = MaterialTheme.sizes.small,
                    fontWeight = FontWeight.Normal,
                    color = GrayColor
                )
            }
        }

        Column(verticalArrangement = Arrangement.spacedBy(-(MaterialTheme.spacing.medium))) {

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
                    text = "Allow Permission",
                    modifier = Modifier
                        .weight(1f)
                        .wrapContentHeight(),
                    color = BlackColor,
                    fontSize = MaterialTheme.sizes.medium,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Start
                )

                Switch(checked = true, onCheckedChange = {})
            }

            Box(
                modifier = Modifier
                    .padding(end = MaterialTheme.spacing.x3Dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                Image(
                    painter = painterResource(id = R.drawable.hand_pointer),
                    contentDescription = "Hand Pointer",
                    modifier = Modifier
                        .wrapContentWidth()
                        .size(30.dp)
                )
            }

            Text(
                modifier = Modifier
                    .padding(top = MaterialTheme.spacing.mediumLarge)
                    .weight(1f),
                color = BlackGray,
                text = "This permission allows an app to show things on the top of other apps you're using. This may interfere with your use of other apps."
            )
        }
    }

}