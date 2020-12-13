package com.im.bin.respository


import android.content.Context
import androidx.lifecycle.LiveData
import com.im.bin.db.AppDatabase
import com.im.bin.db.dao.InstagramDao
import com.im.bin.db.entities.Instagram
import com.im.bin.models.Chat

class InstagramRepository constructor(
    private val context: Context
) {

    private lateinit var messages: LiveData<List<Instagram>>
    var instagramDao: InstagramDao = AppDatabase.getInstance(context)!!.instagramDao()
    private var conversationId: String = ""

    constructor(context: Context, conversationId: String) : this(context) {
        this.conversationId = conversationId
        messages = instagramDao.selectAllInstagramMessages(conversationId)
    }

    fun getAllInstagramMessages(): List<Instagram> {
        return instagramDao.getAllInstagramMessages()
    }

    fun deleteChats() {
        instagramDao.deleteChats()
    }

    fun getInstagramMessagesLive(): LiveData<List<Instagram>> {
        return messages
    }

    fun updateConversation(conversations: List<Instagram>) {
        for (conversation in conversations) {
            if (checkMessageNotExitsAlready(conversation.messageId!!)) {
                instagramDao.insert(conversation)
            }
        }
    }

    fun deleteConversation(conversationId: String) {
        instagramDao.deleteConversation(conversationId)
    }

    fun deleteMessage(messageId: String) {
        instagramDao.deleteMessage(messageId)
    }


    fun insertInstagram(instagram: Instagram) {
        instagramDao.insert(instagram)
    }

    fun selectInstagramChats(): List<Chat> {
        return instagramDao.selectChats()
    }

    fun checkMessageNotExitsAlready(messageId: String): Boolean =
        instagramDao.checkIfAlreadyExist(messageId) == null
}