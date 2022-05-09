package com.screening.app.featureImportContacts.presentation

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.SelectAll
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.screening.app.featureCallScreening.domain.model.PhoneNumber
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun ImportContactsScreen(
    onCloseClick: () -> Unit,
    phoneNumbers: List<PhoneNumber>,
    viewModel: ImportContactViewModel = hiltViewModel()
) {

    val lifeCycleOwner = LocalLifecycleOwner.current
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    val systemUiController = rememberSystemUiController()
    var isContextual by remember { mutableStateOf(false) }

    val backgroundColor = if (isContextual) MaterialTheme.colors.primary else Color.White
    val contentColor = if (isContextual) Color.White else MaterialTheme.colors.primaryVariant

    val statusBarColor = if (isContextual) {
        MaterialTheme.colors.primaryVariant
    } else {
        Color.White
    }

    var importAllContacts by remember { mutableStateOf(0) }
    val importContactState = viewModel.importContactState.value

    DisposableEffect(key1 = lifeCycleOwner, effect = {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_START) {
                // Observer permission screen state
                viewModel.importContacts(phoneNumbers)
            }
        }
        lifeCycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifeCycleOwner.lifecycle.removeObserver(observer)
        }
    })

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
                        "Import Contacts",
                        style = MaterialTheme.typography.body1,
                        color = contentColor
                    )
                },
                backgroundColor = backgroundColor,
                actions = {
                    IconButton(onClick = {
                        importAllContacts = if (importAllContacts == 0) {
                            viewModel.onEvent(ImportContactEvent.ImportAllContacts)
                            1
                        } else {
                            viewModel.onEvent(ImportContactEvent.UnImportAllContacts)
                            0
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Default.SelectAll,
                            contentDescription = "Select UnSelect",
                            tint = contentColor
                        )
                    }
                    IconButton(onClick = onCloseClick) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            tint = contentColor
                        )
                    }
                }
            )
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onCloseClick()
            }, backgroundColor = MaterialTheme.colors.primary) {
                Icon(
                    imageVector = Icons.Default.Done,
                    contentDescription = "Import Contacts",
                    tint = Color.White
                )
            }
        })
    { paddingValues ->

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            if (importContactState.isLoading) {
                CircularProgressIndicator()
            }

            if (!importContactState.isLoading) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(
                        horizontal = 10.dp,
                        vertical = 15.dp
                    )
                ) {

                    items(importContactState.contactsList) { contactItem ->

                        Row(
                            modifier = Modifier
                                .padding(10.dp)
                                .clickable {
                                    viewModel.onEvent(ImportContactEvent.ImportContact(contactItem))
                                },
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {

                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "${contactItem.contactFirstName}",
                                    style = MaterialTheme.typography.h3,
                                    color = Color.Black
                                )
                                Spacer(modifier = Modifier.height(1.dp))
                                Text(
                                    text = contactItem.contactNumber,
                                    style = MaterialTheme.typography.h5,
                                    color = Color.Black
                                )
                            }

                            Canvas(
                                modifier = Modifier
                                    .width(40.dp)
                                    .height(40.dp)
                            ) {
                                val canvasWidth = size.width
                                val canvasHeight = size.height
                                drawCircle(
                                    color = if (contactItem.isSelected) Color.Green else Color.Gray,
                                    center = Offset(x = canvasWidth / 2, y = canvasHeight / 2),
                                    radius = size.minDimension / 4
                                )
                            }

                        }
                    }

                }
            }
        }
    }
}
