package com.screening.app.featureSmsScreening.presentation.newMessage.message

import androidx.compose.ui.focus.FocusState

sealed class NewMessageEvent {
    data class EnteredMessage(val text : String) : NewMessageEvent()
    data class EnteredMessageFocusStateChange(val focusState: FocusState) : NewMessageEvent()
    object SendMessage : NewMessageEvent()
    object StartPhoneCall : NewMessageEvent()
}
