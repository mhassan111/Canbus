package com.screening.app.featureCallScreening.domain.repository

import com.screening.app.featureCallScreening.domain.model.PhoneNumber
import com.screening.app.featureCallScreening.domain.util.OrderType
import com.screening.app.featureCallScreening.domain.util.PhoneNumberOrder
import kotlinx.coroutines.flow.Flow

interface PhoneNumberRepository {
    fun getPhoneNumbers(phoneNumberOrder: PhoneNumberOrder = PhoneNumberOrder.Date(OrderType.Descending)): Flow<List<PhoneNumber>>
    suspend fun getPhoneNumberByPhoneNumber(phoneNumber: String): PhoneNumber?
    suspend fun insertPhoneNumber(phoneNumber: PhoneNumber)
    suspend fun deletePhoneNumber(phoneNumber: PhoneNumber)
    suspend fun deletePhoneNumberByContactId(contactId : String)
    suspend fun insertPhoneNumbers(phoneNumbers : List<PhoneNumber>)
    suspend fun deletePhoneNumbers(contactIds : List<String>)
}