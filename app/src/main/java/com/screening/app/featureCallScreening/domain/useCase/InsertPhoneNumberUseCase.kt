package com.screening.app.featureCallScreening.domain.useCase

import com.screening.app.featureCallScreening.domain.model.InvalidPhoneNumberException
import com.screening.app.featureCallScreening.domain.model.PhoneNumber
import com.screening.app.featureCallScreening.domain.repository.PhoneNumberRepository
import com.screening.app.utilities.Util
import com.screening.app.utilities.isValidPhoneNumber

class InsertPhoneNumberUseCase(private val repository: PhoneNumberRepository) {

    suspend operator fun invoke(phoneNumber: PhoneNumber) {
        if (phoneNumber.phoneNumber.isBlank()) {
            throw InvalidPhoneNumberException("Phone Number is Blank")
        } else if (phoneNumber.contactName.isBlank()) {
            throw InvalidPhoneNumberException("Contact Name is Blank")
        } else if (!phoneNumber.phoneNumber.isValidPhoneNumber()) {
            throw InvalidPhoneNumberException("Not a valid phone Number")
        }
        repository.insertPhoneNumber(phoneNumber)
    }

}