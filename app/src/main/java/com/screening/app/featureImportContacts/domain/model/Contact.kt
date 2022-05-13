package com.screening.app.featureImportContacts.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "contacts")
data class Contact(
    @PrimaryKey
    var contactId: String,
    var contactFirstName: String,
    var contactLastName: String,
    var contactNumber: String,
    var contactHomeNumber: String,
    var contactOfficeNumber: String,
    var isSelected: Boolean
) : Parcelable {
    constructor() : this("", "", "", "", "", "", false)
}
