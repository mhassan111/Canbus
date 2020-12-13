package com.im.bin.respository

import android.content.Context
import com.im.bin.appUtils.InjectorUtils
import com.im.bin.models.Chat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DatabaseRepositoryImpl @Inject constructor() : DatabaseRepository {

    @Inject
    lateinit var context: Context

    override suspend fun getWhatsAppChats(): List<Chat> {
        return withContext(Dispatchers.IO) {
            InjectorUtils.provideWhatsAppRepository(context).selectWhatsAppChats()
        }
    }

    override suspend fun getViberChats(): List<Chat> {
        return withContext(Dispatchers.IO) {
            InjectorUtils.provideViberRepository(context).selectViberChats()
        }
    }

    override suspend fun getLineChats(): List<Chat> {
        return withContext(Dispatchers.IO) {
            InjectorUtils.provideLineRepository(context).selectLineChats()
        }
    }

    override suspend fun getIMOChats(): List<Chat> {
        return withContext(Dispatchers.IO) {
            InjectorUtils.provideIMORepository(context).selectIMOChats()
        }
    }

    override suspend fun getSnapChatChats(): List<Chat> {
        return withContext(Dispatchers.IO) {
            InjectorUtils.provideSnapChatRepository(context).selectSnapChatChats()
        }
    }


    override suspend fun getInstagramChats(): List<Chat> {
        return withContext(Dispatchers.IO) {
            InjectorUtils.provideInstagramRepository(context).selectInstagramChats()
        }
    }

    override suspend fun getHikeChats(): List<Chat> {
        return withContext(Dispatchers.IO) {
            InjectorUtils.provideHikeRepository(context).selectHikeChats()
        }
    }
}