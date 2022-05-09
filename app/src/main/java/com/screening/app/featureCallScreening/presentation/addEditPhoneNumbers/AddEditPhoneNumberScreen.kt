package com.screening.app.featureCallScreening.presentation.addEditPhoneNumbers

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.screening.app.featureCallScreening.presentation.addEditPhoneNumbers.components.HintTextField
import com.screening.app.ui.spacing
import com.screening.app.ui.theme.WhiteColor
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AddEditPhoneNumberScreen(
    phoneNumber: String,
    savedPhoneNumber: () -> Unit,
    viewModel: AddEditPhoneNumberViewModel = hiltViewModel()
) {

    val scaffoldState = rememberScaffoldState()
    val phoneNumberState = viewModel.state.value
    val contactNameState = viewModel.contactNameState.value

    val keyboardController = LocalSoftwareKeyboardController.current
    val systemUiController = rememberSystemUiController()
    var isContextual by remember { mutableStateOf(false) }

    val statusBarColor = if (isContextual) {
        MaterialTheme.colors.primaryVariant
    } else {
        Color.White
    }
    val backgroundColor = if (isContextual) MaterialTheme.colors.primary else Color.White
    val contentColor = if (isContextual) Color.White else MaterialTheme.colors.primaryVariant

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is AddEditPhoneNumberViewModel.UiEvent.SavePhoneNumber -> {
                    savedPhoneNumber()
                }
                is AddEditPhoneNumberViewModel.UiEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
            }
        }
    }

    SideEffect {
        systemUiController.setStatusBarColor(
            color = statusBarColor,
            darkIcons = !isContextual
        )
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        if (phoneNumber.isEmpty()) "Add Phone Number" else "Edit Phone Number",
                        style = MaterialTheme.typography.body1,
                        color = contentColor
                    )
                },
                backgroundColor = backgroundColor,
                navigationIcon = {
                    IconButton(onClick = {
                        savedPhoneNumber()
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = contentColor
                        )
                    }
                }
            )
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { viewModel.onEvent(AddEditPhoneNumberEvent.SavePhoneNumber) },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(imageVector = Icons.Default.Save, contentDescription = "Save PhoneNumber")
            }
        })
    {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            HintTextField(
                text = contactNameState.text,
                hint = contactNameState.hint,
                modifier = Modifier
                    .border(
                        width = 2.dp, color = WhiteColor, shape = RoundedCornerShape(
                            MaterialTheme.spacing.large
                        )
                    ),
                onValueChange = {
                    viewModel.onEvent(AddEditPhoneNumberEvent.EnteredContactName(it))
                },
                onFocusChange = {
                    viewModel.onEvent(AddEditPhoneNumberEvent.ChangeContactNameFocus(it))
                },
                isHintVisible = contactNameState.isHintVisible,
                singleLine = true,
                textStyle = MaterialTheme.typography.h3,
                onKeyboardActionDone = {
                    keyboardController?.hide()
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            HintTextField(
                text = phoneNumberState.text,
                hint = phoneNumberState.hint,
                modifier = Modifier
                    .border(
                        width = 2.dp, color = WhiteColor, shape = RoundedCornerShape(
                            MaterialTheme.spacing.large
                        )
                    ),
                onValueChange = {
                    viewModel.onEvent(AddEditPhoneNumberEvent.EnteredPhoneNumber(it))
                },
                onFocusChange = {
                    viewModel.onEvent(AddEditPhoneNumberEvent.ChangePhoneNumberFocus(it))
                },
                isHintVisible = phoneNumberState.isHintVisible,
                singleLine = true,
                textStyle = MaterialTheme.typography.h3,
                onKeyboardActionDone = {
                    keyboardController?.hide()
                }
            )
        }
    }
}