package com.screening.app.featureSmsScreening.presentation.newMessage.selectContact

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.screening.app.featureImportContacts.domain.model.Contact
import com.screening.app.featureInstallation.presentation.activation.components.HintTextField
import com.screening.app.featureSmsScreening.presentation.conversations.componets.InitialImage
import com.screening.app.ui.spacing
import com.screening.app.ui.theme.WhiteColor

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SelectNewContactScreen(
    onContactSelected: (Contact) -> Unit,
    onNavigateBack: () -> Unit,
    viewModel: SelectContactViewModel = hiltViewModel()
) {

    val lifeCycleOwner = LocalLifecycleOwner.current
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    val keyboardController = LocalSoftwareKeyboardController.current

    val systemUiController = rememberSystemUiController()
    val backgroundColor = Color.White
    val contentColor = MaterialTheme.colors.primaryVariant
    val statusBarColor = Color.White
    val startConversationState = viewModel.startConversationState.value
    val importContactState = viewModel.importContactState.value

    DisposableEffect(key1 = lifeCycleOwner, effect = {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_START) {
                // Observer permission screen state
//                viewModel.importContacts(phoneNumbers)
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
            darkIcons = true
        )
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "New Conversation",
                        style = MaterialTheme.typography.body1,
                        color = contentColor
                    )
                },
                backgroundColor = backgroundColor,
                navigationIcon = {
                    IconButton(onClick = { onNavigateBack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Navigate Back",
                            tint = contentColor
                        )
                    }
                }
            )
        },
        floatingActionButtonPosition = FabPosition.End
    )
    { paddingValues ->


        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(top = MaterialTheme.spacing.xDp, bottom = MaterialTheme.spacing.xDp)
        ) {

            StartConversationSearchBar(
                text = startConversationState.text,
                hint = startConversationState.hint,
                isHintVisible = startConversationState.isHintVisible,
                onValueChange = {
                    viewModel.onEvent(SelectContactEvent.SearchedContact(it))
                },
                onFocusStateChange = {
                    viewModel.onEvent(SelectContactEvent.ChangeSearchedContactFocusState(it))
                }
            )
            SuggestedConversations()
            PhoneContactsList(
                onContactSelected = { contact ->
                    onContactSelected(contact)
                },
                importContactState.filteredContactList,
                importContactState.isLoading,
            )
        }
    }
}

@Composable
fun SuggestedConversations() {

}

@Composable
fun PhoneContactsList(
    onContactSelected: (Contact) -> Unit,
    contactsList: List<Contact>,
    isLoading: Boolean
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        if (isLoading) {
            CircularProgressIndicator()
        }

        if (!isLoading) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(
                    horizontal = 10.dp,
                    vertical = 15.dp
                )
            ) {
                items(contactsList) { contactItem ->
                    Row(
                        modifier = Modifier
                            .clickable {
                                onContactSelected(contactItem)
                            }
                            .padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        InitialImage(
                            text = contactItem.contactFirstName.first().toString(),
                            size = MaterialTheme.spacing.x4Dp,
                            textStyle = MaterialTheme.typography.h3
                        )
                        Spacer(modifier = Modifier.width(MaterialTheme.spacing.xDp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = contactItem.contactFirstName,
                                style = MaterialTheme.typography.h4,
                                color = Color.Black
                            )
                            Spacer(modifier = Modifier.height(MaterialTheme.spacing.smallest))
                            Text(
                                text = contactItem.contactNumber,
                                style = MaterialTheme.typography.h5,
                                color = Color.Black
                            )
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun StartConversationSearchBar(
    text: String,
    hint: String,
    isHintVisible: Boolean,
    onValueChange: (String) -> Unit,
    onFocusStateChange: (FocusState) -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    HintTextField(
        text = text,
        hint = hint,
        modifier = Modifier
            .padding(MaterialTheme.spacing.xDp)
            .border(
                width = 2.dp,
                color = WhiteColor,
                shape = RoundedCornerShape(
                    MaterialTheme.spacing.large
                )
            ),
        onValueChange = {
            onValueChange(it)
        },
        onFocusChange = {
            onFocusStateChange(it)
        },
        isHintVisible = isHintVisible,
        singleLine = true,
        textStyle = MaterialTheme.typography.h5,
        textColor = Color.Black,
        onKeyboardActionDone = {
            keyboardController?.hide()
        }
    )
}