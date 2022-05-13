package com.screening.app.featureSmsScreening.domain.useCase

import com.screening.app.featureImportContacts.domain.model.Contact
import com.screening.app.featureSmsScreening.domain.repository.ContactRepository

class SelectContactUseCase(private val contactRepository: ContactRepository) {
    suspend operator fun invoke(contactId: String): Contact {
        return contactRepository.selectContactById(contactId)
    }
}