package com.screening.app.featureSmsScreening.presentation.conversations

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.screening.app.featureSmsScreening.domain.model.Conversation
import com.screening.app.featureSmsScreening.domain.model.Message
import com.screening.app.utilities.logVerbose
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ConversationsViewModel @Inject constructor() : ViewModel() {

    private var _conversationState = mutableStateOf(ConversationsState())
    val conversationState = _conversationState

    init {
        viewModelScope.launch {
            delay(1000)
            val newConversations = mutableListOf<Conversation>()
            (1..100).forEach {
                newConversations.add(
                    Conversation(
                        "id: ${it.toString()}",
                        "name: $it",
                        "phoneNumber: $it",
                        Message(
                            "$it",
                            "This is a sample Message Text This is a sample Message Text $it",
                            "Inbox",
                            Date()
                        ),
                        emptyList()
                    )
                )
            }

            conversationState.value =
                _conversationState.value.copy(conversations = newConversations)
        }
    }

    fun onEvent(event: ConversationEvent) {
        logVerbose(" event called ${event.javaClass.name}")
        when (event) {
            is ConversationEvent.ConversationTapped -> {

            }
            ConversationEvent.StartNewConversation -> {

            }
        }
    }
}