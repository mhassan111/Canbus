package com.screening.app.featureSmsScreening.data.dataSource

import androidx.room.*
import com.screening.app.featureImportContacts.domain.model.Contact
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertContact(contact: Contact)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertContacts(contacts: List<Contact>)

    @Query("Select * from contacts where contactId=:contactId")
    fun selectContactById(contactId: String) : Contact

    @Delete
    fun deleteContact(contact: Contact)

    @Query("Select * from contacts")
    fun getContacts(): Flow<List<Contact>>
}