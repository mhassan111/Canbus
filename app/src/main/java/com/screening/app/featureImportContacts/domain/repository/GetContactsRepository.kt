package com.screening.app.featureImportContacts.domain.repository

import com.screening.app.featureImportContacts.domain.model.Contact
import kotlinx.coroutines.flow.Flow

interface GetContactsRepository {
    fun getContacts() : Flow<List<Contact>>
}