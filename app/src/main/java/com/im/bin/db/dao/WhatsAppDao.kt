package com.im.bin.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.im.bin.db.entities.WhatsApp
import com.im.bin.models.Chat

@Dao
interface WhatsAppDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(imMessageDao: WhatsApp)

    @Query("Select message_id from whats_app_table where message_id = :messageId")
    fun checkIfAlreadyExist(messageId: String): String?

    @Query("Select * from whats_app_table order by date DESC")
    fun getAllWhatsAppMessages(): List<WhatsApp>

    @Query("Select * from whats_app_table where conversation_id=:conversationId order by date DESC")
    fun selectAllWhatsAppMessages(conversationId: String): LiveData<List<WhatsApp>>

    @Query("delete from whats_app_table where conversation_id=:conversationId")
    fun deleteConversation(conversationId: String)

    @Query("delete from whats_app_table")
    fun deleteChats()

    @Query("delete from whats_app_table where message_id=:message_id")
    fun deleteMessage(message_id: String)

    @Query("Select conversation_id,conversation_name,message,message_datetime from whats_app_table GROUP BY conversation_id order by date DESC")
    fun selectChats(): List<Chat>

    @Query("Update whats_app_table set status=:updated_status where status=:status")
    fun update(status: Int, updated_status: Int)

    @Query("Update whats_app_table set isDeleted=:isDeleted where message_id=:message_id")
    fun setMessageAsDeleted(message_id: String, isDeleted: String)

    @Query("Update whats_app_table set status=:status")
    fun updateStatus(status: Int): Int
}