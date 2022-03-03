package com.os.system.featureInstallation.presentation.permissions

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Checkbox
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.os.system.R
import com.os.system.featureInstallation.presentation.permissions.components.PermissionFrame
import com.os.system.ui.sizes
import com.os.system.ui.spacing
import com.os.system.ui.theme.BlackColor
import com.os.system.ui.theme.LightGrayColor
import com.os.system.ui.theme.LightGreenishColor
import com.os.system.utilities.buildAnnotatedText
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
fun ScreenRecordPermissionPie() {

    PermissionFrame(
        permissionEvent = ScreenRecordPermissionEvent,
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(MaterialTheme.spacing.mediumLarge),
                verticalArrangement = Arrangement.Center
            ) {

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = listOf(
                        AnnotatedText(text = "Android System Manager ", annotate = true),
                        AnnotatedText(text = "will start capturing everything that's displayed on your screen")
                    ).buildAnnotatedText(
                        spanStyle = SpanStyle(
                            color = BlackColor,
                            fontSize = MaterialTheme.sizes.mediumLarge,
                            fontWeight = FontWeight.Bold
                        )
                    ),
                    color = BlackColor,
                    fontSize = MaterialTheme.sizes.mediumLarge,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Start
                )

                Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {

                    Checkbox(
                        checked = true,
                        onCheckedChange = {},
                        modifier = Modifier.size(MaterialTheme.spacing.medium)
                    )

                    Box(
                        modifier = Modifier
                            .height(MaterialTheme.spacing.x3Dp)
                            .padding(start = MaterialTheme.spacing.xDp)
                            .fillMaxWidth(),
                        contentAlignment = Alignment.CenterStart
                    ) {

                        Text(
                            modifier = Modifier
                                .wrapContentHeight()
                                .wrapContentWidth(),
                            text = "Don't show again",
                            fontSize = MaterialTheme.sizes.medium,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                }

                Spacer(modifier = Modifier.height(MaterialTheme.spacing.x2Dp))

                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = MaterialTheme.spacing.medium,
                            end = MaterialTheme.spacing.medium,
                            top = MaterialTheme.spacing.medium,
                            bottom = MaterialTheme.spacing.small
                        )
                ) {

                    Text(
                        text = "CANCEL",
                        color = LightGreenishColor,
                        fontSize = MaterialTheme.sizes.mediumLarge,
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(modifier = Modifier.width(20.dp))

                    Text(
                        text = "START NOW",
                        color = LightGreenishColor,
                        fontSize = MaterialTheme.sizes.mediumLarge,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            end = MaterialTheme.spacing.x2Dp
                        )
                ) {

                    Image(
                        modifier = Modifier.size(MaterialTheme.spacing.x3Dp),
                        painter = painterResource(id = R.drawable.hand_pointer),
                        contentDescription = null
                    )

                }
            }
        },
        skipPermission = {

        },
        allowPermission = {

        }
    )
}

@Preview
@Composable
fun ScreenRecordPermissionPiePreview() {
    ScreenRecordPermissionPie()
}

