package com.im.bin.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.im.bin.db.entities.VoipCall

@Dao
interface VoipCallDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(snapChat: VoipCall)

    @Query("Select voipId from voip_call_table where voipId = :voipId")
    fun checkIfAlreadyExist(voipId: String): String?

    @Query("Select * from voip_call_table where voipMessenger=:voipMessenger order by date DESC")
    fun getAllVoipCalls(voipMessenger: String): List<VoipCall>

    @Query("delete from voip_call_table where voipMessenger=:voipMessenger")
    fun deleteVoipCalls(voipMessenger: String)

    @Query("Select * from voip_call_table where voipMessenger=:voipMessenger order by date DESC")
    fun selectAllVoipCalls(voipMessenger: String): LiveData<List<VoipCall>>

    @Query("delete from voip_call_table where voipId=:voipId")
    fun deleteVoipCall(voipId: String)

    @Query("Update voip_call_table set status=:updated_status where status=:status")
    fun update(status: Int, updated_status: Int)
}