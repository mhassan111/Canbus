package com.screening.app.featureSmsScreening.presentation.newMessage.message

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
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
import com.screening.app.featureCallScreening.presentation.addEditPhoneNumbers.AddEditPhoneNumberEvent
import com.screening.app.featureCallScreening.presentation.addEditPhoneNumbers.components.HintTextField
import com.screening.app.featureSmsScreening.domain.model.Message
import com.screening.app.featureSmsScreening.presentation.newMessage.components.MessageItem
import com.screening.app.ui.spacing
import com.screening.app.ui.theme.WhiteColor
import kotlinx.coroutines.Dispatchers
import java.util.*

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun NewMessageScreen(
    onNavigateBack: () -> Unit,
    name: String,
    id: String,
    viewModel: NewMessageViewModel = hiltViewModel()
) {

    val lifeCycleOwner = LocalLifecycleOwner.current
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    val keyboardController = LocalSoftwareKeyboardController.current

    val systemUiController = rememberSystemUiController()
    val backgroundColor = MaterialTheme.colors.primaryVariant
    val contentColor = Color.White
    val statusBarColor = MaterialTheme.colors.primaryVariant
    val messageState = viewModel.messageState.value

    DisposableEffect(key1 = lifeCycleOwner, effect = {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_START) {

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
            darkIcons = false
        )
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        name,
                        style = MaterialTheme.typography.body1,
                        color = contentColor
                    )
                },
                backgroundColor = backgroundColor,
                navigationIcon = {
                    IconButton(onClick = {
                        onNavigateBack()
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Navigate Back",
                            tint = contentColor
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {

                    }) {
                        Icon(
                            imageVector = Icons.Default.Phone,
                            contentDescription = "Dial Phone",
                            tint = contentColor
                        )
                    }
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "Settings",
                            tint = contentColor
                        )
                    }
                }
            )
        },
        floatingActionButtonPosition = FabPosition.End
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(MaterialTheme.spacing.xDp),
            verticalArrangement = Arrangement.Bottom
        ) {

            ConversationMessages(
                modifier = Modifier
                    .weight(1f)
            )

            SendMessageFooter(
                modifier = Modifier.fillMaxWidth(),
                messageState.text,
                messageState.hint,
                messageState.isHintVisible,
                onValueChange = {
                    viewModel.onEvent(NewMessageEvent.EnteredMessage(it))
                },
                onFocusChange = {
                    viewModel.onEvent(NewMessageEvent.EnteredMessageFocusStateChange(it))
                },
                onAttachFile = {

                },
                onSendMessage = {

                }
            )
        }
    }
}

@Composable
fun ConversationMessages(modifier: Modifier) {
    val messages = mutableListOf<Message>()
    (1..20).forEach {
        messages.add(
            Message(
                id = it.toString(),
                body = "This is the sample body for Message $it",
                type = if (it % 2 == 0) "Inbox" else "Outgoing",
                date = Date()
            )
        )
    }
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(MaterialTheme.spacing.x2Dp),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.xDp)
    ) {
        items(messages) { message ->
            MessageItem(modifier = Modifier.fillMaxWidth(), message = message)
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SendMessageFooter(
    modifier: Modifier,
    text: String,
    hint: String,
    isHintVisible: Boolean,
    onValueChange: (String) -> Unit,
    onFocusChange: (FocusState) -> Unit,
    onAttachFile: () -> Unit,
    onSendMessage: () -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {

            IconButton(
                onClick = { onAttachFile() },
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Files")
            }

            HintTextField(
                text = text,
                hint = hint,
                modifier = Modifier.wrapContentHeight(),
                onValueChange = {
                    onValueChange(it)
                },
                onFocusChange = {
                    onFocusChange(it)
                },
                isHintVisible = isHintVisible,
                singleLine = true,
                textStyle = MaterialTheme.typography.h5,
                onKeyboardActionDone = {
                    keyboardController?.hide()
                }
            )
        }

        IconButton(
            onClick = { onSendMessage() },
        ) {
            Icon(imageVector = Icons.Default.Send, contentDescription = "Send Message")
        }

    }
}

