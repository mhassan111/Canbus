package com.im.bin.respository

import android.content.Context
import androidx.lifecycle.LiveData
import com.im.bin.db.AppDatabase
import com.im.bin.db.dao.WhatsAppDao
import com.im.bin.db.entities.WhatsApp
import com.im.bin.models.Chat

class WhatsAppRepository constructor(
    private val context: Context
) {

    private lateinit var messages: LiveData<List<WhatsApp>>
    var whatsAppDao: WhatsAppDao = AppDatabase.getInstance(context)!!.whatsAppDao()
    private var conversationId: String = ""

    constructor(context: Context, conversationId: String) : this(context) {
        this.conversationId = conversationId
        messages = whatsAppDao.selectAllWhatsAppMessages(conversationId)
    }

    fun getWhatsAppMessagesLive(): LiveData<List<WhatsApp>> {
        return messages
    }

    fun getAllWhatsAppMessages(): List<WhatsApp> {
        return whatsAppDao.getAllWhatsAppMessages()
    }

    fun updateConversation(conversations: List<WhatsApp>) {
        for (conversation in conversations) {
            if (checkMessageNotExitsAlready(conversation.messageId!!)) {
                whatsAppDao.insert(conversation)
            }
        }
    }

    fun deleteChats() {
        whatsAppDao.deleteChats()
    }

    fun deleteConversation(conversationId: String) {
        whatsAppDao.deleteConversation(conversationId)
    }

    fun deleteMessage(messageId: String) {
        whatsAppDao.deleteMessage(messageId)
    }

    fun insertWhatsApp(whatsApp: WhatsApp) {
        whatsAppDao.insert(whatsApp)
    }

    fun selectWhatsAppChats(): List<Chat> {
        return whatsAppDao.selectChats()
    }

    fun checkMessageNotExitsAlready(messageId: String): Boolean =
        whatsAppDao.checkIfAlreadyExist(messageId) == null

    fun updateStatus(status: Int): Int = whatsAppDao.updateStatus(status)

//    companion object {
//        @Volatile
//        private var instance: WhatsAppRepository? = null
//        fun getInstance(context: Context) =
//            instance ?: synchronized(this) {
//                instance ?: WhatsAppRepository(
//                    AppDatabase.getInstance(context)!!.whatsAppDao()
//                ).also { instance = it }
//            }
//    }
}