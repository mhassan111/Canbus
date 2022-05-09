package com.screening.app.featureCallScreening.domain.useCase

import com.screening.app.featureCallScreening.domain.model.PhoneNumber
import com.screening.app.featureCallScreening.domain.repository.PhoneNumberRepository

class DeletePhoneNumberUseCase(private val repository: PhoneNumberRepository) {
    suspend operator fun invoke(phoneNumber: PhoneNumber) {
        repository.deletePhoneNumber(phoneNumber)
    }
}