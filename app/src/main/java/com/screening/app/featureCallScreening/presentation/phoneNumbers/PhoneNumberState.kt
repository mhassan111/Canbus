package com.screening.app.featureCallScreening.presentation.phoneNumbers

import com.screening.app.featureCallScreening.domain.model.PhoneNumber
import com.screening.app.featureCallScreening.domain.util.OrderType
import com.screening.app.featureCallScreening.domain.util.PhoneNumberOrder

data class PhoneNumberState(
    val numbers: List<PhoneNumber> = emptyList(),
    val phoneNumberOrder: PhoneNumberOrder = PhoneNumberOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)