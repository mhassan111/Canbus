package com.screening.app.featureCallScreening.presentation.addEditPhoneNumbers

import androidx.compose.ui.focus.FocusState

sealed class AddEditPhoneNumberEvent {
    data class EnteredPhoneNumber(val value : String) : AddEditPhoneNumberEvent()
    data class ChangePhoneNumberFocus(val focusState: FocusState) : AddEditPhoneNumberEvent()
    data class EnteredContactName(val value : String) : AddEditPhoneNumberEvent()
    data class ChangeContactNameFocus(val focusState: FocusState) : AddEditPhoneNumberEvent()
    object SavePhoneNumber : AddEditPhoneNumberEvent()
}
