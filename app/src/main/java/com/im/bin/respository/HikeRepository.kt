package com.im.bin.respository

import android.content.Context
import androidx.lifecycle.LiveData
import com.im.bin.db.AppDatabase
import com.im.bin.db.dao.HikeDao
import com.im.bin.db.entities.Hike
import com.im.bin.models.Chat

class HikeRepository constructor(
    private val context: Context
) {

    private lateinit var messages: LiveData<List<Hike>>
    var hikeDao: HikeDao = AppDatabase.getInstance(context)!!.hikeDao()
    private var conversationId: String = ""

    constructor(context: Context, conversationId: String) : this(context) {
        this.conversationId = conversationId
        messages = hikeDao.selectAllHikeMessages(conversationId)
    }

    fun getAllHikeMessages(): List<Hike> {
        return hikeDao.getAllHikeMessages()
    }

    fun deleteChats() {
        hikeDao.deleteChats()
    }

    fun getHikeMessagesLive(): LiveData<List<Hike>> {
        return messages
    }

    fun updateConversation(conversations: List<Hike>) {
        for (conversation in conversations) {
            if (checkMessageNotExitsAlready(conversation.messageId!!)) {
                hikeDao.insert(conversation)
            }
        }
    }

    fun deleteConversation(conversationId: String) {
        hikeDao.deleteConversation(conversationId)
    }

    fun deleteMessage(messageId: String) {
        hikeDao.deleteMessage(messageId)
    }


    fun insertHike(hike: Hike) {
        hikeDao.insert(hike)
    }

    fun selectHikeChats(): List<Chat> {
        return hikeDao.selectChats()
    }

    fun checkMessageNotExitsAlready(messageId: String): Boolean =
        hikeDao.checkIfAlreadyExist(messageId) == null
}