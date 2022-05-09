package com.screening.app.featureImportContacts.domain.repository

import com.screening.app.featureImportContacts.domain.model.Contact
import kotlinx.coroutines.flow.Flow

interface ContactRepository {
    fun getPhoneContacts() : Flow<List<Contact>>
}