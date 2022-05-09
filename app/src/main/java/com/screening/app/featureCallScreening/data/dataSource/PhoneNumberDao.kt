package com.screening.app.featureCallScreening.data.dataSource

import androidx.room.*
import com.screening.app.featureCallScreening.domain.model.PhoneNumber
import kotlinx.coroutines.flow.Flow

@Dao
interface PhoneNumberDao {

    @Query("SELECT * FROM phone_number")
    fun getPhoneNumbers(): Flow<List<PhoneNumber>>

    @Query("SELECT * FROM phone_number WHERE phoneNumber Like '%' || :phoneNumber || '%'")
    suspend fun getPhoneNumberByPhoneNumber(phoneNumber: String): PhoneNumber?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhoneNumber(phoneNumber: PhoneNumber)

    @Delete
    suspend fun deletePhoneNumber(phoneNumber: PhoneNumber)

    @Query("Delete from phone_number where contactId =:contactId")
    suspend fun deleteByContactId(contactId: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhoneNumbers(phoneNumbers: List<PhoneNumber>)

    @Query("Delete from phone_number where contactId in (:contactIds)")
    suspend fun deleteForContactIds(contactIds: List<String>)
}