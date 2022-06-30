package com.canbus.app.featureUartSerial.presentation

import androidx.compose.ui.focus.FocusState

sealed class UartCommunicationEvent {
    data class EnteredUartMessage(val value : String) : UartCommunicationEvent()
    data class ChangeUartMessageFocus(val focusState: FocusState) : UartCommunicationEvent()
}
