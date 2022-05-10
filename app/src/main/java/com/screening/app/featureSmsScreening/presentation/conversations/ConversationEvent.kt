package com.screening.app.featureSmsScreening.presentation.conversations

import com.screening.app.featureSmsScreening.domain.model.Conversation

sealed class ConversationEvent {
    data class ConversationTapped(val conversation: Conversation) : ConversationEvent()
    object StartNewConversation : ConversationEvent()
}
