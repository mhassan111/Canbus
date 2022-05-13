package com.screening.app.featureImportContacts.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.screening.app.featureCallScreening.domain.model.PhoneNumber
import com.screening.app.featureCallScreening.domain.repository.PhoneNumberRepository
import com.screening.app.featureImportContacts.domain.repository.GetContactsRepository
import com.screening.app.utilities.DateTimeUtil
import com.screening.app.utilities.Util
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ImportContactViewModel @Inject constructor(
    private val phoneNumberRepository: PhoneNumberRepository,
    private val getContactsRepository: GetContactsRepository
) : ViewModel() {

    private var _importContactState = mutableStateOf<ImportContactState>(ImportContactState())
    val importContactState: MutableState<ImportContactState> = _importContactState

//    init {
//        importContacts()
//    }

    fun onEvent(event: ImportContactEvent) {
        when (event) {
            is ImportContactEvent.ImportAllContacts -> {
                viewModelScope.launch {
                    withContext(Dispatchers.IO) {
                        val contactsList = importContactState.value.contactsList
                        val phoneNumbers = mutableListOf<PhoneNumber>()
                        contactsList.forEach { contact ->
                            contact.isSelected = true
                            phoneNumbers.add(
                                PhoneNumber(
                                    Util.parsePhoneNumber(contact.contactNumber),
                                    contact.contactId,
                                    contact.contactFirstName,
                                    DateTimeUtil.getDate(System.currentTimeMillis())
                                )
                            )
                        }
                        phoneNumberRepository.insertPhoneNumbers(phoneNumbers)

                        importContactState.value = importContactState.value.copy(
                            contactsList = emptyList()
                        )
                        _importContactState.value = importContactState.value.copy(
                            contactsList = contactsList
                        )
                    }
                }
            }

            is ImportContactEvent.UnImportAllContacts -> {
                viewModelScope.launch {
                    withContext(Dispatchers.IO) {
                        val contactsList = importContactState.value.contactsList
                        val contactIds = contactsList.map { it.contactId }
                        phoneNumberRepository.deletePhoneNumbers(contactIds)
                        contactsList.forEach { contact ->
                            contact.isSelected = false
                        }
                        importContactState.value = importContactState.value.copy(
                            contactsList = emptyList()
                        )
                        _importContactState.value = importContactState.value.copy(
                            contactsList = contactsList
                        )
                    }
                }
            }

            is ImportContactEvent.ImportContact -> {
                viewModelScope.launch {
                    val eventContact = event.contact
                    withContext(Dispatchers.IO) {
                        if (!eventContact.isSelected) {
                            // Insert into white listed phone numbers list
                            val phoneNumber = PhoneNumber(
                                Util.parsePhoneNumber(eventContact.contactNumber),
                                eventContact.contactId,
                                eventContact.contactFirstName,
                                DateTimeUtil.getDate(System.currentTimeMillis())
                            )
                            phoneNumberRepository.insertPhoneNumber(phoneNumber)
                        } else {
                            phoneNumberRepository.deletePhoneNumberByContactId(eventContact.contactId)
                        }
                    }

                    // update contacts list
                    withContext(Dispatchers.IO) {
                        val contactsList = importContactState.value.contactsList.toMutableList()
                        val contact = contactsList.find { it == event.contact }
                        contact?.let { updatedContact ->
                            updatedContact.apply {
                                this.isSelected = !event.contact.isSelected
                            }
                            contactsList[contactsList.indexOf(event.contact)] = updatedContact
                            importContactState.value = importContactState.value.copy(
                                contactsList = contactsList.toList()
                            )
                        }
                    }

                }
            }
        }
    }

    fun importContacts(phoneNumbers: List<PhoneNumber>) {
        _importContactState.value = importContactState.value.copy(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            getContactsRepository.getContacts().collectLatest { contactList ->
                val contactsIds = phoneNumbers.map { it.contactId }.filter { it.isNotBlank() }
                contactList.forEachIndexed { index, contact ->
                    if (contactsIds.contains(contact.contactId)) {
                        contact.isSelected = true
                    }
                }

                _importContactState.value = importContactState.value.copy(
                    isLoading = false,
                    contactsList = contactList
                )
            }
        }
    }
}