package com.im.bin.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.im.bin.db.entities.Line
import com.im.bin.db.entities.WhatsApp
import com.im.bin.models.Chat

@Dao
interface LineDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(Line: Line)

    @Query("Select message_id from line_table where message_id = :messageId")
    fun checkIfAlreadyExist(messageId: String): String?

    @Query("Select * from line_table order by date DESC")
    fun getAllLineMessages(): List<Line>

    @Query("delete from line_table")
    fun deleteChats()

    @Query("Select * from line_table where conversation_id=:conversationId order by date DESC")
    fun selectAllLineMessages(conversationId: String): LiveData<List<Line>>

    @Query("delete from line_table where conversation_id=:conversationId")
    fun deleteConversation(conversationId: String)

    @Query("delete from line_table where message_id=:message_id")
    fun deleteMessage(message_id: String)

    @Query("Select conversation_id,conversation_name,message,message_datetime from line_table GROUP BY conversation_id order by date DESC")
    fun selectChats(): List<Chat>

    @Query("Update line_table set status=:updated_status where status=:status")
    fun update(status: Int, updated_status: Int)

    @Query("Update line_table set isDeleted=:isDeleted where message_id=:message_id")
    fun setMessageAsDeleted(message_id: String, isDeleted: String)
}