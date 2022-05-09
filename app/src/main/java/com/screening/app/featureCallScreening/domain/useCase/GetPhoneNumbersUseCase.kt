package com.screening.app.featureCallScreening.domain.useCase

import com.screening.app.featureCallScreening.domain.model.PhoneNumber
import com.screening.app.featureCallScreening.domain.repository.PhoneNumberRepository
import com.screening.app.featureCallScreening.domain.util.OrderType
import com.screening.app.featureCallScreening.domain.util.PhoneNumberOrder
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow

class GetPhoneNumbersUseCase(private val repository: PhoneNumberRepository) {
    operator fun invoke(phoneNumberOrder: PhoneNumberOrder = PhoneNumberOrder.Date(OrderType.Descending)): Flow<List<PhoneNumber>> {
        return repository.getPhoneNumbers(phoneNumberOrder)
    }
}