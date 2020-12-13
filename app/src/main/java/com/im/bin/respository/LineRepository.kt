package com.im.bin.respository


import android.content.Context
import androidx.lifecycle.LiveData
import com.im.bin.db.AppDatabase
import com.im.bin.db.dao.LineDao
import com.im.bin.db.entities.Line
import com.im.bin.db.entities.WhatsApp
import com.im.bin.models.Chat

class LineRepository constructor(
    private val context: Context
) {

    private lateinit var messages: LiveData<List<Line>>
    var lineDao: LineDao = AppDatabase.getInstance(context)!!.lineDao()
    private var conversationId: String = ""

    constructor(context: Context, conversationId: String) : this(context) {
        this.conversationId = conversationId
        messages = lineDao.selectAllLineMessages(conversationId)
    }

    fun getAllLineMessages(): List<Line> {
        return lineDao.getAllLineMessages()
    }

    fun deleteChats() {
        lineDao.deleteChats()
    }

    fun getLineMessagesLive(): LiveData<List<Line>> {
        return messages
    }

    fun updateConversation(conversations: List<Line>) {
        for (conversation in conversations) {
            if (checkMessageNotExitsAlready(conversation.messageId!!)) {
                lineDao.insert(conversation)
            }
        }
    }

    fun deleteConversation(conversationId: String) {
        lineDao.deleteConversation(conversationId)
    }

    fun deleteMessage(messageId: String) {
        lineDao.deleteMessage(messageId)
    }

    fun insertLine(line: Line) {
        lineDao.insert(line)
    }

    fun selectLineChats(): List<Chat> {
        return lineDao.selectChats()
    }

    fun checkMessageNotExitsAlready(messageId: String): Boolean =
        lineDao.checkIfAlreadyExist(messageId) == null
}