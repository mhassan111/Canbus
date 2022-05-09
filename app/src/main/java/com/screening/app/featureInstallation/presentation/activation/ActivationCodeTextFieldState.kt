package com.screening.app.featureInstallation.presentation.activation

data class ActivationCodeTextFieldState(
    val text : String = "",
    val hint : String = "Enter Activation Code...",
    val isHintVisible : Boolean = true
)
