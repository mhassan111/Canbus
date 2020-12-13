package com.im.bin.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.im.bin.db.entities.Instagram
import com.im.bin.models.Chat

@Dao
interface InstagramDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(Instagram: Instagram)

    @Query("delete from instagram_table")
    fun deleteChats()

    @Query("Select message_id from instagram_table where message_id = :messageId")
    fun checkIfAlreadyExist(messageId: String): String?

    @Query("Select * from instagram_table order by date DESC")
    fun getAllInstagramMessages(): List<Instagram>

    @Query("Select * from instagram_table where conversation_id=:conversationId order by date DESC")
    fun selectAllInstagramMessages(conversationId: String): LiveData<List<Instagram>>

    @Query("delete from instagram_table where conversation_id=:conversationId")
    fun deleteConversation(conversationId: String)

    @Query("delete from instagram_table where message_id=:message_id")
    fun deleteMessage(message_id: String)

    @Query("Select conversation_id,conversation_name,message,message_datetime from instagram_table GROUP BY conversation_id order by date DESC")
    fun selectChats(): List<Chat>

    @Query("Update instagram_table set status=:updated_status where status=:status")
    fun update(status: Int, updated_status: Int)

    @Query("Update instagram_table set isDeleted=:isDeleted where message_id=:message_id")
    fun setMessageAsDeleted(message_id: String, isDeleted: String)
}