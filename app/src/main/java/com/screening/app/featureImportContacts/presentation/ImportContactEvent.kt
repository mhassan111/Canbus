package com.screening.app.featureImportContacts.presentation

import com.screening.app.featureImportContacts.domain.model.Contact

sealed class ImportContactEvent {
    class ImportContact(val contact: Contact) : ImportContactEvent()
    object ImportAllContacts : ImportContactEvent()
    object UnImportAllContacts : ImportContactEvent()
}
