package com.im.bin.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.im.bin.models.IMMessage
import java.util.*

@Entity(tableName = "hike_table")
data class Hike(
    @field:ColumnInfo(name = "id") @field:PrimaryKey(autoGenerate = true) val id: Int,
    @field:ColumnInfo(name = "message_id") val messageId: String?,
    @field:ColumnInfo(name = "conversation_id") val conversationId: String?,
    @field:ColumnInfo(name = "conversation_name") val conversationName: String?,
    @field:ColumnInfo(name = "sender_name") val senderName: String?,
    @field:ColumnInfo(name = "message") val message: String?,
    @field:ColumnInfo(name = "type") val type: String?,
    @field:ColumnInfo(name = "message_datetime") val messageDatetime: String?,
    @field:ColumnInfo(name = "timeStamp") val timeStamp: Long?,
    @field:ColumnInfo(name = "isDeleted") val isDeleted: String?,
    @field:Transient @field:ColumnInfo(
        name = "date",
        defaultValue = "2020-03-12 17:08:36"
    ) val date: Date?,
    @field:ColumnInfo(name = "status") val status: Int
) {

    class HikeUnrootedBuilder {

        private var id = 0
        private var messageId: String? = null
        private var conversationId: String? = null
        private var conversationName: String? = null
        private var senderName: String? = null
        private var message: String? = null
        private var type: String? = null
        private var messageDatetime: String? = null
        private var timeStamp: Long? = null
        private var date: Date? = null
        private var isDeleted: String? = null
        private var status = 0

        fun setId(id: Int): HikeUnrootedBuilder {
            this.id = id
            return this
        }

        fun setMessageId(messageId: String?): HikeUnrootedBuilder {
            this.messageId = messageId
            return this
        }

        fun setConversationId(conversationId: String?): HikeUnrootedBuilder {
            this.conversationId = conversationId
            return this
        }

        fun setConversationName(conversationName: String?): HikeUnrootedBuilder {
            this.conversationName = conversationName
            return this
        }

        fun setSenderName(senderName: String?): HikeUnrootedBuilder {
            this.senderName = senderName
            return this
        }

        fun setMessage(message: String?): HikeUnrootedBuilder {
            this.message = message
            return this
        }

        fun setType(type: String?): HikeUnrootedBuilder {
            this.type = type
            return this
        }

        fun setMessageDatetime(messageDatetime: String?): HikeUnrootedBuilder {
            this.messageDatetime = messageDatetime
            return this
        }

        fun setTimeStamp(timeStamp: Long?): HikeUnrootedBuilder {
            this.timeStamp = timeStamp
            return this
        }

        fun setDate(date: Date?): HikeUnrootedBuilder {
            this.date = date
            return this
        }

        fun setIsDeleted(isDeleted: String?): HikeUnrootedBuilder {
            this.isDeleted = isDeleted
            return this
        }

        fun setStatus(status: Int): HikeUnrootedBuilder {
            this.status = status
            return this
        }

        fun create(): Hike {
            return Hike(
                id,
                messageId!!,
                conversationId,
                conversationName,
                senderName,
                message,
                type,
                messageDatetime,
                timeStamp,
                isDeleted,
                date,
                status
            )
        }
    }
}

fun Hike.toIMMessage() = IMMessage(
    id = id,
    messageId = "$messageId",
    conversationId = "$conversationId",
    conversationName = "$conversationName",
    senderName = "$senderName",
    message = "$message",
    type = "$type",
    messageDatetime = "$messageDatetime",
    timeStamp = timeStamp!!,
    date = date!!,
    isDeleted = "$isDeleted"
)