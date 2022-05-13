package com.screening.app.featureImportContacts.presentation

import com.screening.app.featureImportContacts.domain.model.Contact

data class ImportContactState(
    val isLoading : Boolean = false,
    val contactsList : List<Contact> = emptyList(),
    val filteredContactList : List<Contact> = emptyList()
)
