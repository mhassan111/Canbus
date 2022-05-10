package com.screening.app.featureSmsScreening.presentation.conversations

import com.screening.app.featureSmsScreening.domain.model.Conversation

data class ConversationsState(
    val conversations : List<Conversation> = emptyList()
)
