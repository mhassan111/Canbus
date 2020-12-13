package com.im.bin.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Chat(
    @field:ColumnInfo(name = "conversation_id") val conversationId: String,
    @field:ColumnInfo(name = "conversation_name") val conversationName: String,
    @field:ColumnInfo(name = "message") val lastMessage: String,
    @field:ColumnInfo(name = "message_datetime") val messageDateTime: String
) : Parcelable