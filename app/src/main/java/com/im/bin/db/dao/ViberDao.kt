package com.im.bin.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.im.bin.db.entities.Viber
import com.im.bin.db.entities.WhatsApp
import com.im.bin.models.Chat

@Dao
interface ViberDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(Viber: Viber)

    @Query("Select message_id from viber_table where message_id = :messageId")
    fun checkIfAlreadyExist(messageId: String): String?

    @Query("Select * from viber_table order by date DESC")
    fun getAllViberMessages(): List<Viber>

    @Query("delete from viber_table")
    fun deleteChats()

    @Query("Select * from viber_table where conversation_id=:conversationId")
    fun selectAllViberMessagesList(conversationId: String): LiveData<List<Viber>>

    @Query("Select * from viber_table where conversation_name =:conversation_name order by date DESC")
    fun selectViberMessages(conversation_name: String): List<Viber>

    @Query("Select conversation_id,conversation_name,message,message_datetime from viber_table GROUP BY conversation_id order by date DESC")
    fun selectChats(): List<Chat>

    @Query("delete from viber_table where conversation_id=:conversationId")
    fun deleteConversation(conversationId: String)

    @Query("delete from viber_table where message_id=:message_id")
    fun deleteMessage(message_id: String)

    @Query("Update viber_table set status=:updated_status where status=:status")
    fun update(status: Int, updated_status: Int)

    @Query("Update viber_table set isDeleted=:isDeleted where message_id=:message_id")
    fun setMessageAsDeleted(message_id: String, isDeleted: String)
}