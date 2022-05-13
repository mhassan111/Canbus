package com.screening.app.featureSmsScreening.domain.repository

import com.screening.app.featureImportContacts.domain.model.Contact
import kotlinx.coroutines.flow.Flow

interface ContactRepository {
    suspend fun insertContact(contact: Contact)
    suspend fun insertContacts(contacts : List<Contact>)
    suspend fun getContacts() : Flow<List<Contact>>
    suspend fun deleteContact(contact: Contact)
    suspend fun selectContactById(contactId : String) : Contact
}