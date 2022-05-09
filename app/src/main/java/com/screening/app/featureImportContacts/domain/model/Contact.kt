package com.screening.app.featureImportContacts.domain.model

data class Contact(
    var contactId: String,
    var contactFirstName: String,
    var contactLastName: String,
    var contactNumber: String,
    var contactHomeNumber: String,
    var contactOfficeNumber: String,
    var isSelected : Boolean
) {
    constructor() : this("", "", "", "", "", "", false)
}
