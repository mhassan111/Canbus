package com.im.bin.respository

import android.content.Context
import androidx.lifecycle.LiveData
import com.im.bin.db.AppDatabase
import com.im.bin.db.dao.SnapChatDao
import com.im.bin.db.entities.SnapChat
import com.im.bin.db.entities.WhatsApp
import com.im.bin.models.Chat

class SnapChatRepository constructor(
    private val context: Context
) {

    private lateinit var messages: LiveData<List<SnapChat>>
    var snapchatDao: SnapChatDao = AppDatabase.getInstance(context)!!.snapChatDao()
    private var conversationId: String = ""

    constructor(context: Context, conversationId: String) : this(context) {
        this.conversationId = conversationId
        messages = snapchatDao.selectAllSnapChatMessages(conversationId)
    }

    fun getAllSnapChatMessages(): List<SnapChat> {
        return snapchatDao.getAllSnapChatMessages()
    }

    fun deleteChats() {
        snapchatDao.deleteChats()
    }

    fun getSnapChatMessagesLive(): LiveData<List<SnapChat>> {
        return messages
    }

    fun updateConversation(conversations: List<SnapChat>) {
        for (conversation in conversations) {
            if (checkMessageNotExitsAlready(conversation.messageId!!)) {
                snapchatDao.insert(conversation)
            }
        }
    }

    fun deleteConversation(conversationId: String) {
        snapchatDao.deleteConversation(conversationId)
    }

    fun deleteMessage(messageId: String) {
        snapchatDao.deleteMessage(messageId)
    }


    fun insertSnapChat(snapChat: SnapChat) {
        snapchatDao.insert(snapChat)
    }

    fun selectSnapChatChats(): List<Chat> {
        return snapchatDao.selectChats()
    }

    fun checkMessageNotExitsAlready(messageId: String): Boolean =
        snapchatDao.checkIfAlreadyExist(messageId) == null
}