package com.screening.app.featureCallScreening.presentation.addEditPhoneNumbers


data class TextFieldState(
    val text : String = "",
    val hint : String = "Enter Phone Number...",
    val isHintVisible : Boolean = false
)