package com.screening.app.featureCallScreening.data.repository

import com.screening.app.featureCallScreening.data.dataSource.PhoneNumberDao
import com.screening.app.featureCallScreening.domain.model.PhoneNumber
import com.screening.app.featureCallScreening.domain.repository.PhoneNumberRepository
import com.screening.app.featureCallScreening.domain.util.OrderType
import com.screening.app.featureCallScreening.domain.util.PhoneNumberOrder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PhoneNumberRepositoryImpl(private val phoneNumberDao: PhoneNumberDao) :
    PhoneNumberRepository {

    override fun getPhoneNumbers(phoneNumberOrder: PhoneNumberOrder): Flow<List<PhoneNumber>> {
        return phoneNumberDao.getPhoneNumbers().map { phoneNumbers ->
            when (phoneNumberOrder.orderType) {
                OrderType.Ascending -> {
                    when (phoneNumberOrder) {
                        is PhoneNumberOrder.Date -> phoneNumbers.sortedBy { it.date }
                        is PhoneNumberOrder.Number -> phoneNumbers.sortedBy { it.phoneNumber }
                    }
                }
                OrderType.Descending -> {
                    when (phoneNumberOrder) {
                        is PhoneNumberOrder.Date -> phoneNumbers.sortedByDescending { it.date }
                        is PhoneNumberOrder.Number -> phoneNumbers.sortedByDescending { it.phoneNumber }
                    }
                }
            }
        }
    }

    override suspend fun getPhoneNumberByPhoneNumber(phoneNumber: String): PhoneNumber? {
        return phoneNumberDao.getPhoneNumberByPhoneNumber(phoneNumber)
    }

    override suspend fun insertPhoneNumber(phoneNumber: PhoneNumber) {
        phoneNumberDao.insertPhoneNumber(phoneNumber)
    }

    override suspend fun deletePhoneNumber(phoneNumber: PhoneNumber) {
        phoneNumberDao.deletePhoneNumber(phoneNumber)
    }

    override suspend fun deletePhoneNumberByContactId(contactId: String) {
        phoneNumberDao.deleteByContactId(contactId)
    }

    override suspend fun deletePhoneNumbers(contactIds: List<String>) {
        phoneNumberDao.deleteForContactIds(
            contactIds = contactIds
        )
    }

    override suspend fun insertPhoneNumbers(phoneNumbers: List<PhoneNumber>) {
        phoneNumberDao.insertPhoneNumbers(phoneNumbers)
    }
}