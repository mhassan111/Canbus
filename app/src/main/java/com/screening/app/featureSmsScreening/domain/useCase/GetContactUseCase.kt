package com.screening.app.featureSmsScreening.domain.useCase

import android.content.Context
import com.screening.app.MyApplication.Companion.appContext
import com.screening.app.featureImportContacts.domain.model.Contact
import com.screening.app.featureSmsScreening.domain.repository.ContactRepository
import com.screening.app.utilities.ContactUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class GetContactUseCase(
    private val contactsRepository: ContactRepository
) {
    suspend operator fun invoke(): Flow<List<Contact>> {
        withContext(Dispatchers.IO) {
            val contacts = ContactUtils.retrieveContacts(appContext)
            contactsRepository.insertContacts(contacts)
        }
        return contactsRepository.getContacts()
    }
}