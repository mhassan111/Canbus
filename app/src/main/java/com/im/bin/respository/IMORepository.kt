package com.im.bin.respository

import android.content.Context
import androidx.lifecycle.LiveData
import com.im.bin.db.AppDatabase
import com.im.bin.db.dao.IMODao
import com.im.bin.db.entities.IMO
import com.im.bin.models.Chat

class IMORepository constructor(
    private val context: Context
) {

    private lateinit var messages: LiveData<List<IMO>>
    var imoDao: IMODao = AppDatabase.getInstance(context)!!.imoDao()
    private var conversationId: String = ""

    constructor(context: Context, conversationId: String) : this(context) {
        this.conversationId = conversationId
        messages = imoDao.selectAllIMOMessages(conversationId)
    }

    fun getAllIMOMessages(): List<IMO> {
        return imoDao.getAllIMOMessages()
    }

    fun deleteChats() {
        imoDao.deleteChats()
    }

    fun getIMOMessagesLive(): LiveData<List<IMO>> {
        return messages
    }

    fun updateConversation(conversations: List<IMO>) {
        for (conversation in conversations) {
            if (checkMessageNotExitsAlready(conversation.messageId!!)) {
                imoDao.insert(conversation)
            }
        }
    }

    fun deleteConversation(conversationId: String) {
        imoDao.deleteConversation(conversationId)
    }

    fun deleteMessage(messageId: String) {
        imoDao.deleteMessage(messageId)
    }


    fun insertIMO(imo: IMO) {
        imoDao.insert(imo)
    }

    fun selectIMOChats(): List<Chat> {
        return imoDao.selectChats()
    }

    fun checkMessageNotExitsAlready(messageId: String): Boolean =
        imoDao.checkIfAlreadyExist(messageId) == null
}