package com.screening.app.featureCallScreening.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.lang.Exception
import java.util.*

@Parcelize
@Entity(tableName = "phone_number")
data class PhoneNumber(
    var phoneNumber: String,
    val contactId: String,
    val contactName : String,
    val date: Date,
    @PrimaryKey val id: Int? = null
) : Parcelable

class InvalidPhoneNumberException(message: String) : Exception(message)