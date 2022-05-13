package com.screening.app.featureImportContacts.data.repository

import android.content.Context
import com.screening.app.featureImportContacts.domain.model.Contact
import com.screening.app.featureImportContacts.domain.repository.GetContactsRepository
import com.screening.app.utilities.ContactUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetContactsRepositoryImpl(private val context: Context) : GetContactsRepository {

    override fun getContacts(): Flow<List<Contact>> {
        return flow { emit(ContactUtils.retrieveContacts(context)) }
    }
}