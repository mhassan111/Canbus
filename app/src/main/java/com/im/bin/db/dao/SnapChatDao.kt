package com.im.bin.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.im.bin.db.entities.SnapChat
import com.im.bin.db.entities.WhatsApp
import com.im.bin.models.Chat

@Dao
interface SnapChatDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(snapChat: SnapChat)

    @Query("Select message_id from snap_chat_table where message_id = :messageId")
    fun checkIfAlreadyExist(messageId: String): String?

    @Query("Select * from snap_chat_table order by date DESC")
    fun getAllSnapChatMessages(): List<SnapChat>

    @Query("delete from snap_chat_table")
    fun deleteChats()

    @Query("Select * from snap_chat_table where conversation_id=:conversationId order by date DESC")
    fun selectAllSnapChatMessages(conversationId: String): LiveData<List<SnapChat>>

    @Query("delete from snap_chat_table where conversation_id=:conversationId")
    fun deleteConversation(conversationId: String)

    @Query("delete from snap_chat_table where message_id=:message_id")
    fun deleteMessage(message_id: String)

    @Query("Select conversation_id,conversation_name,message,message_datetime from snap_chat_table GROUP BY conversation_id order by date DESC")
    fun selectChats(): List<Chat>

    @Query("Update snap_chat_table set status=:updated_status where status=:status")
    fun update(status: Int, updated_status: Int)

    @Query("Update snap_chat_table set isDeleted=:isDeleted where message_id=:message_id")
    fun setMessageAsDeleted(message_id: String, isDeleted: String)
}