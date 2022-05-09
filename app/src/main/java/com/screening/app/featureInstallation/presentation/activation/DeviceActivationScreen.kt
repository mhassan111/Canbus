package com.screening.app.featureInstallation.presentation.activation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.screening.app.featureInstallation.WholeSpyScreen
import com.screening.app.featureInstallation.presentation.activation.components.HintTextField
import com.screening.app.ui.spacing
import com.screening.app.ui.theme.SlateBlue
import com.screening.app.ui.theme.WhiteColor
import kotlinx.coroutines.flow.collectLatest

/**
 * [DeviceActivationScreen]
 * This composable is responsible for drawing the content of Device Activation Screen. This Screen contains an input text
 * field where User will have to put the Activation Code to activate the service. And a button to start the activation process.
 * These two are the major components of this Activation Screen.
 */
@ExperimentalComposeUiApi
@ExperimentalPermissionsApi
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun DeviceActivationScreen(
    onScreenChange: (WholeSpyScreen) -> Unit,
    viewModel: DeviceActivationViewModel = hiltViewModel()
) {

    // soft keyboard controller
    val keyboardController = LocalSoftwareKeyboardController.current

    // Scaffold State
    val scaffoldState = rememberScaffoldState()

    // Coroutine Scope
    val scope = rememberCoroutineScope()

    // activation code text field state
    val activationCodeTextState = viewModel.activationCodeTextFieldState.value

    // Validates if activation code is empty or not
    val isActivationCodeValid by derivedStateOf { activationCodeTextState.text.isNotEmpty() }

    var showLoader by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is DeviceActivationViewModel.UiEvent.ShowSnackBar -> {
                    showLoader = false
                    onScreenChange(WholeSpyScreen.DeviceAdminScreen)
                }
                is DeviceActivationViewModel.UiEvent.DeviceActivationSuccess -> {
                    showLoader = false
                }
                is DeviceActivationViewModel.UiEvent.ActivatingService -> {
                    showLoader = true
                }
            }
        }
    }

    Scaffold(backgroundColor = Color.White, scaffoldState = scaffoldState) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(SlateBlue)
                .padding(MaterialTheme.spacing.mediumLarge),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                modifier = Modifier
                    .padding(bottom = MaterialTheme.spacing.x3Dp)
                    .fillMaxWidth(),
                text = "CallSmsScreening",
                color = WhiteColor,
                style = MaterialTheme.typography.h1,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.x2Dp))

            Text(
                modifier = Modifier.padding(
                    top = MaterialTheme.spacing.xDp,
                    bottom = MaterialTheme.spacing.xDp
                ),
                text = "Please Enter the License Code provided and Tap on the Activate to initiate the app services.",
                color = WhiteColor,
                style = MaterialTheme.typography.h3,
                textAlign = TextAlign.Center
            )

            HintTextField(
                text = activationCodeTextState.text,
                hint = activationCodeTextState.hint,
                modifier = Modifier
                    .border(
                        width = 2.dp, color = WhiteColor, shape = RoundedCornerShape(
                            MaterialTheme.spacing.large
                        )
                    ),
                onValueChange = {
                    viewModel.onEvent(ServiceActivationEvent.EnteredActivationCode(it))
                },
                onFocusChange = {
                    viewModel.onEvent(ServiceActivationEvent.ChangeTextFocus(it))
                },
                isHintVisible = activationCodeTextState.isHintVisible,
                singleLine = true,
                textStyle = MaterialTheme.typography.h6,
                onKeyboardActionDone = {
                    keyboardController?.hide()
                }
            )

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.mediumLarge))

            Button(
                onClick = {
                    viewModel.onEvent(ServiceActivationEvent.ActivateService)
                },
                enabled = isActivationCodeValid,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(MaterialTheme.spacing.x4Dp)
                    .border(
                        width = 2.dp, color = WhiteColor, shape = RoundedCornerShape(
                            MaterialTheme.spacing.large
                        )
                    ),
                shape = RoundedCornerShape(MaterialTheme.spacing.large)
            ) {
                Text(
                    text = "Activate",
                    style = MaterialTheme.typography.button
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            AnimatedVisibility(
                visible = showLoader,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                CircularProgressIndicator(color = WhiteColor)
            }
        }
    }
}