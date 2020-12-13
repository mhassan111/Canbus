package com.im.bin.respository

import android.content.Context
import androidx.lifecycle.LiveData
import com.im.bin.db.AppDatabase
import com.im.bin.db.dao.ViberDao
import com.im.bin.db.entities.Viber
import com.im.bin.models.Chat

class ViberRepository constructor(context: Context) {

    private lateinit var messages: LiveData<List<Viber>>
    var viberDao: ViberDao = AppDatabase.getInstance(context)!!.viberDao()
    private var conversationId: String = ""

    constructor(context: Context, conversationId: String) : this(context) {
        this.conversationId = conversationId
        messages = viberDao.selectAllViberMessagesList(conversationId)
    }

    fun getAllViberMessages(): List<Viber> {
        return viberDao.getAllViberMessages()
    }

    fun deleteChats() {
        viberDao.deleteChats()
    }

    fun getViberMessagesLive(): LiveData<List<Viber>> {
        return messages
    }

    fun insertViber(viber: Viber) {
        viberDao.insert(viber)
    }

    fun updateConversation(conversations: List<Viber>) {
        for (conversation in conversations) {
            if (checkMessageNotExitsAlready(conversation.messageId!!)) {
                viberDao.insert(conversation)
            }
        }
    }

    fun deleteConversation(conversationId: String) {
        viberDao.deleteConversation(conversationId)
    }

    fun deleteMessage(messageId: String) {
        viberDao.deleteMessage(messageId)
    }

    fun selectViberChats(): List<Chat> {
        return viberDao.selectChats()
    }

    fun checkMessageNotExitsAlready(messageId: String): Boolean =
        viberDao.checkIfAlreadyExist(messageId) == null
}