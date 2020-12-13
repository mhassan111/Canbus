package com.im.bin.respository

import com.im.bin.models.Chat

interface DatabaseRepository {

    suspend fun getWhatsAppChats(): List<Chat>
    suspend fun getViberChats(): List<Chat>
    suspend fun getLineChats(): List<Chat>
    suspend fun getIMOChats(): List<Chat>
    suspend fun getSnapChatChats(): List<Chat>
    suspend fun getInstagramChats(): List<Chat>
    suspend fun getHikeChats(): List<Chat>

}