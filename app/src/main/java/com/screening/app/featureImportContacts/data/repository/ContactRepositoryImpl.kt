package com.screening.app.featureImportContacts.data.repository

import android.annotation.SuppressLint
import android.content.Context
import android.provider.ContactsContract
import com.screening.app.MyApplication.Companion.appContext
import com.screening.app.featureImportContacts.domain.model.Contact
import com.screening.app.featureImportContacts.domain.repository.ContactRepository
import com.screening.app.utilities.Util
import com.screening.app.utilities.logException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ContactRepositoryImpl(private val context: Context) : ContactRepository {

    override fun getPhoneContacts(): Flow<List<Contact>> {
        return flow { emit(retrieveContacts(context)) }
    }

    @SuppressLint("Range")
    private fun retrieveContacts(context: Context): List<Contact> {
        val contacts = mutableListOf<Contact>()
        try {
            val managedCursor = context.contentResolver.query(
                ContactsContract.Contacts.CONTENT_URI, null, null, null,
                ContactsContract.Contacts._ID + " DESC"
            )
            if (managedCursor != null) {
                while (managedCursor.moveToNext()) {
                    try {
                        val contactId = managedCursor.getString(
                            managedCursor.getColumnIndex(
                                ContactsContract.Contacts._ID
                            )
                        )
                        val whereNameClause =
                            ContactsContract.Data.MIMETYPE + " = ? AND " + ContactsContract.CommonDataKinds.StructuredName.CONTACT_ID + " = ?"
                        val whereNameParams = arrayOf(
                            ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE,
                            contactId
                        )
                        val cursor = context.contentResolver.query(
                            ContactsContract.Data.CONTENT_URI,
                            null,
                            whereNameClause,
                            whereNameParams,
                            ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME
                        )
                        var firstName: String = ""
                        var lastName: String = ""
                        var mobileNumber = ""
                        var homeNumber = ""
                        var officeNumber = ""
                        if (cursor != null) {
                            while (cursor.moveToNext()) {
                                firstName =
                                    cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME))
                                        ?: ""
                                lastName =
                                    cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.FAMILY_NAME))
                                        ?: ""
                            }
                            cursor.close()
                        }
                        val phoneCursor = context.contentResolver.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId,
                            null,
                            null
                        )

                        if (phoneCursor != null) {
                            while (phoneCursor.moveToNext()) {
                                val phoneType = phoneCursor.getInt(
                                    phoneCursor.getColumnIndex(
                                        ContactsContract.CommonDataKinds.Phone.TYPE
                                    )
                                )
                                when (phoneType) {
                                    ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE -> {
                                        mobileNumber = phoneCursor.getString(
                                            phoneCursor.getColumnIndex(
                                                ContactsContract.CommonDataKinds.Phone.DATA
                                            )
                                        )
                                    }
                                    ContactsContract.CommonDataKinds.Phone.TYPE_HOME -> {
                                        homeNumber = phoneCursor.getString(
                                            phoneCursor.getColumnIndex(
                                                ContactsContract.CommonDataKinds.Phone.DATA
                                            )
                                        )
                                    }
                                    ContactsContract.CommonDataKinds.Phone.TYPE_WORK -> {
                                        officeNumber = phoneCursor.getString(
                                            phoneCursor.getColumnIndex(
                                                ContactsContract.CommonDataKinds.Phone.DATA
                                            )
                                        )
                                    }
                                }
                            }
                            phoneCursor.close()
                        }
                        if (mobileNumber.isEmpty()) {
                            mobileNumber = Util.retrievePhoneNumberFromDisplayName(firstName)
                        }
                        val contact = Contact()
                        contact.apply {
                            this.contactId = contactId
                            this.contactFirstName = firstName
                            this.contactLastName = lastName
                            this.contactNumber = mobileNumber
                            this.contactHomeNumber = homeNumber
                            this.contactOfficeNumber = officeNumber
                        }
                        contacts.add(contact)
                    } catch (e: Exception) {
                        logException("Error getting Contact: ${e.message}")
                    }
                }
                managedCursor.close()
            }
        } catch (exception: Exception) {
            logException("Error getting Contact List: ${exception.message}")
        }
        return contacts
    }
}