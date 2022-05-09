package com.screening.app.featureCallScreening.presentation.phoneNumbers

import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ImportContacts
import androidx.compose.material.icons.filled.Sort
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.screening.app.featureCallScreening.domain.model.PhoneNumber
import com.screening.app.featureCallScreening.presentation.components.OrderSection
import com.screening.app.featureCallScreening.presentation.components.PhoneNumberItem
import kotlinx.coroutines.launch

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun PhoneNumbersScreen(
    onAddEditPhoneNumber: (String) -> Unit,
    onImportContacts: (phoneNumbers: List<PhoneNumber>) -> Unit,
    viewModel: PhoneNumberViewModel = hiltViewModel()
) {

    val systemUiController = rememberSystemUiController()
    var isContextual by remember { mutableStateOf(false) }

    val statusBarColor = if (isContextual) {
        MaterialTheme.colors.primaryVariant
    } else {
        Color.White
    }
    val backgroundColor = if (isContextual) MaterialTheme.colors.primary else Color.White
    val contentColor = if (isContextual) Color.White else MaterialTheme.colors.primaryVariant

    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

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
                        "Call Screening",
                        style = MaterialTheme.typography.body1,
                        color = contentColor
                    )
                },
                backgroundColor = backgroundColor,
                actions = {
                    IconButton(onClick = { onImportContacts(state.numbers) }) {
                        Icon(
                            imageVector = Icons.Default.ImportContacts,
                            contentDescription = "Import Contacts",
                            tint = contentColor
                        )
                    }
                }
            )
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onAddEditPhoneNumber("") },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add PhoneNumber",
                    tint = Color.White
                )
            }
        })
    {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Your WhiteList Numbers",
                    style = MaterialTheme.typography.h5
                )
                IconButton(
                    onClick = {
                        viewModel.onEvent(PhoneNumberEvent.ToggleOrderSection)
                    },
                ) {
                    Icon(
                        imageVector = Icons.Default.Sort,
                        contentDescription = "Sort"
                    )
                }
            }

            AnimatedVisibility(
                visible = state.isOrderSectionVisible,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ) {
                OrderSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    phoneNumberOrder = state.phoneNumberOrder,
                    onOrderChange = {
                        viewModel.onEvent(PhoneNumberEvent.Order(it))
                    }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.numbers) { phoneNumber ->
                    PhoneNumberItem(
                        phoneNumber = phoneNumber,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
//                                navController.navigate(
//                                    Screen.AddEditNoteScreen.route +
//                                            "?noteId=${phoneNumber.id}&noteColor=${phoneNumber.color}"
//                                )
                            },
                        onDeleteClick = {
                            viewModel.onEvent(PhoneNumberEvent.DeletePhoneNumber(phoneNumber))
                            scope.launch {
                                val result = scaffoldState.snackbarHostState.showSnackbar(
                                    message = "Phone Number deleted",
                                    actionLabel = "Undo"
                                )
                                if (result == SnackbarResult.ActionPerformed) {
                                    viewModel.onEvent(PhoneNumberEvent.RestorePhoneNumber)
                                }
                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}