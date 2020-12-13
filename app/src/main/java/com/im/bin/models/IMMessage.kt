package com.im.bin.models

import com.google.firebase.firestore.ServerTimestamp
import com.im.bin.db.entities.*
import java.util.*

data class IMMessage(
    var id: Int = 0,
    var messageId: String = "",
    var conversationId: String = "",
    var conversationName: String = "",
    var senderName: String = "",
    var message: String = "",
    var type: String = "",
    var messageDatetime: String = "",
    var timeStamp: Long = 0L,
    var date: Date = Date(),
    var isDeleted: String = "",
    var status: Int = 0,
    @ServerTimestamp
    var serverTimeStamp: Date = Date()
)

fun IMMessage.toWhatsApp() = WhatsApp(
    id = id,
    messageId = messageId,
    conversationId = conversationId,
    conversationName = conversationName,
    senderName = senderName,
    message = message,
    type = type,
    messageDatetime = messageDatetime,
    timeStamp = timeStamp,
    date = date,
    isDeleted = isDeleted,
    status = status
)

fun IMMessage.toViber() = Viber(
    id = id,
    messageId = messageId,
    conversationId = conversationId,
    conversationName = conversationName,
    senderName = senderName,
    message = message,
    type = type,
    messageDatetime = messageDatetime,
    timeStamp = timeStamp,
    date = date,
    isDeleted = isDeleted,
    status = status
)

fun IMMessage.toLine() = Line(
    id = id,
    messageId = messageId,
    conversationId = conversationId,
    conversationName = conversationName,
    senderName = senderName,
    message = message,
    type = type,
    messageDatetime = messageDatetime,
    timeStamp = timeStamp,
    date = date,
    isDeleted = isDeleted,
    status = status
)

fun IMMessage.toIMO() = IMO(
    id = id,
    messageId = messageId,
    conversationId = conversationId,
    conversationName = conversationName,
    senderName = senderName,
    message = message,
    type = type,
    messageDatetime = messageDatetime,
    timeStamp = timeStamp,
    date = date,
    isDeleted = isDeleted,
    status = status
)

fun IMMessage.toInstagram() = Instagram(
    id = id,
    messageId = messageId,
    conversationId = conversationId,
    conversationName = conversationName,
    senderName = senderName,
    message = message,
    type = type,
    messageDatetime = messageDatetime,
    timeStamp = timeStamp,
    date = date,
    isDeleted = isDeleted,
    status = status
)

fun IMMessage.toSnapChat() = SnapChat(
    id = id,
    messageId = messageId,
    conversationId = conversationId,
    conversationName = conversationName,
    senderName = senderName,
    message = message,
    type = type,
    messageDatetime = messageDatetime,
    timeStamp = timeStamp,
    date = date,
    isDeleted = isDeleted,
    status = status
)

fun IMMessage.toHike() = Hike(
    id = id,
    messageId = messageId,
    conversationId = conversationId,
    conversationName = conversationName,
    senderName = senderName,
    message = message,
    type = type,
    messageDatetime = messageDatetime,
    timeStamp = timeStamp,
    date = date,
    isDeleted = isDeleted,
    status = status
)