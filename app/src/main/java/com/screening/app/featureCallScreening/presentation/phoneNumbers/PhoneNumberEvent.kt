package com.screening.app.featureCallScreening.presentation.phoneNumbers

import com.screening.app.featureCallScreening.domain.model.PhoneNumber
import com.screening.app.featureCallScreening.domain.util.PhoneNumberOrder

sealed class PhoneNumberEvent {
    data class Order(val phoneNumberOrder: PhoneNumberOrder) : PhoneNumberEvent()
    data class DeletePhoneNumber(val phoneNumber: PhoneNumber) : PhoneNumberEvent()
    object RestorePhoneNumber : PhoneNumberEvent()
    object ToggleOrderSection : PhoneNumberEvent()
}
