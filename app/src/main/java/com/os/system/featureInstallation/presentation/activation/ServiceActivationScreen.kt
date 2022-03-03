package com.os.system.featureInstallation.presentation.activation

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.os.system.featureInstallation.presentation.activation.components.HintTextField
import com.os.system.featureInstallation.presentation.destinations.DeviceAdminPermissionScreenDestination
import com.os.system.featureInstallation.presentation.util.Screen
import com.os.system.ui.sizes
import com.os.system.ui.spacing
import com.os.system.ui.theme.BlueColor
import com.os.system.ui.theme.ClayColor
import com.os.system.ui.theme.SkyBlueColor
import com.os.system.ui.theme.WhiteColor
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.collectLatest

/**
 * [ServiceActivationScreen]
 * This composable is responsible for drawing the content of Device Activation Screen. This Screen contains an input text
 * field where User will have to put the Activation Code to activate the service. And a button to start the activation process.
 * These two are the major components of this Activation Screen.
 */
@ExperimentalPermissionsApi
@OptIn(ExperimentalAnimationApi::class)
@Destination(start = true)
@Composable
fun ServiceActivationScreen(
    navigator: DestinationsNavigator,
    viewModel: ServiceActivationViewModel = hiltViewModel()) {

    // Scaffold State
    val scaffoldState = rememberScaffoldState()

    // Coroutine Scope
    val scope = rememberCoroutineScope()

    // activation code text field state
    val activationCodeTextState = viewModel.activationCodeTextFieldState.value

    // Validates if activation code is empty or not
    val isActivationCodeValid by derivedStateOf {
        activationCodeTextState.text.isNotEmpty()
    }

    var showLoader by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is ServiceActivationViewModel.UiEvent.ShowSnackBar -> {
                    showLoader = false
                    navigator.navigate(DeviceAdminPermissionScreenDestination)
                }
                is ServiceActivationViewModel.UiEvent.ServiceActivationSuccess -> {
                    showLoader = false
                }
                is ServiceActivationViewModel.UiEvent.ActivatingService -> {
                    showLoader = true
                }
            }
        }
    }

    Scaffold(backgroundColor = Color.White, scaffoldState = scaffoldState) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(ClayColor)
                .padding(MaterialTheme.spacing.mediumLarge),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                modifier = Modifier
                    .padding(bottom = MaterialTheme.spacing.x3Dp)
                    .fillMaxWidth(),
                text = "TheOneSpy",
                color = SkyBlueColor,
                fontSize = MaterialTheme.sizes.x3Large,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.x2Dp))

            Text(
                modifier = Modifier.padding(top = 5.dp),
                text = "Welcome!",
                color = SkyBlueColor,
                fontSize = MaterialTheme.sizes.extraLarge,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Text(
                modifier = Modifier.padding(
                    top = MaterialTheme.spacing.xDp,
                    bottom = MaterialTheme.spacing.xDp
                ),
                text = "Thanks for taking interest in our services, Please enter Activation License key provided in email credentials or find on your dashboard.",
                color = WhiteColor,
                fontSize = MaterialTheme.sizes.medium,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Normal,
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
                textStyle = TextStyle(
                    color = WhiteColor,
                    fontSize = MaterialTheme.sizes.large,
                    fontWeight = FontWeight.SemiBold
                ),
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
                    fontWeight = FontWeight.Normal,
                    fontSize = MaterialTheme.sizes.large
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