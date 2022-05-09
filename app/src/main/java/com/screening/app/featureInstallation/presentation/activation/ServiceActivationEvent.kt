package com.screening.app.featureInstallation.presentation.activation

import androidx.compose.ui.focus.FocusState

sealed class ServiceActivationEvent{
    data class EnteredActivationCode(val code : String) : ServiceActivationEvent()
    data class ChangeTextFocus(val focusState: FocusState) : ServiceActivationEvent()
    object ActivateService : ServiceActivationEvent()
}
