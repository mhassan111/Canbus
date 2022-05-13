package com.screening.app.featureSmsScreening.domain.useCase

import com.screening.app.featureImportContacts.domain.model.Contact
import com.screening.app.featureSmsScreening.domain.repository.ContactRepository

class DeleteContactUseCase(private val contactRepository: ContactRepository) {
    suspend operator fun invoke(contact: Contact) {
        contactRepository.deleteContact(contact)
    }
}