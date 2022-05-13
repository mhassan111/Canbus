package com.screening.app.featureSmsScreening.presentation.conversations

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.screening.app.featureSmsScreening.domain.model.Conversation
import com.screening.app.featureSmsScreening.presentation.conversations.componets.ConversationItem
import com.screening.app.ui.spacing

@Composable
fun ConversationsScreen(
    viewModel: ConversationsViewModel = hiltViewModel()
) {

    val conversationState = viewModel.conversationState.value

    // set status bar color to White
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(
            color = Color.White,
            darkIcons = true
        )
    }

    ConversationsList(
        conversations = conversationState.conversations,
        modifier = Modifier.fillMaxSize(),
        onConversationTapped = { conversation ->
            viewModel.onEvent(ConversationEvent.ConversationTapped(conversation))
        }
    )
}

@Composable
fun ConversationsList(
    onConversationTapped: (Conversation) -> Unit,
    conversations: List<Conversation>,
    modifier: Modifier
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
        contentPadding = PaddingValues(horizontal = 0.dp, vertical = MaterialTheme.spacing.x2Dp)
    ) {
        items(conversations) { conversation ->
            ConversationItem(
                modifier = Modifier.fillMaxWidth(),
                conversation = conversation,
                onConversationTapped = {
                    onConversationTapped(conversation)
                }
            )
        }
    }
}