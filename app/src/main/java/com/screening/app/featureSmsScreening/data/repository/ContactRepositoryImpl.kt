package com.screening.app.featureSmsScreening.data.repository

import com.screening.app.featureImportContacts.domain.model.Contact
import com.screening.app.featureSmsScreening.data.dataSource.ContactsDao
import com.screening.app.featureSmsScreening.domain.repository.ContactRepository
import kotlinx.coroutines.flow.Flow

class ContactRepositoryImpl(private val contactsDao: ContactsDao) : ContactRepository {

    override suspend fun getContacts(): Flow<List<Contact>> {
        return contactsDao.getContacts()
    }

    override suspend fun insertContact(contact: Contact) {
        contactsDao.insertContact(contact)
    }

    override suspend fun insertContacts(contacts: List<Contact>) {
        contactsDao.insertContacts(contacts)
    }

    override suspend fun deleteContact(contact: Contact) {
        contactsDao.deleteContact(contact)
    }

    override suspend fun selectContactById(contactId: String): Contact {
        return contactsDao.selectContactById(contactId)
    }
}