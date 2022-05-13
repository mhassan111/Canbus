package com.screening.app.featureSmsScreening.presentation.newMessage.selectContact

import androidx.compose.ui.focus.FocusState

sealed class SelectContactEvent {
    data class SearchedContact(val value : String) : SelectContactEvent()
    data class ChangeSearchedContactFocusState(val focusState: FocusState) : SelectContactEvent()
}
