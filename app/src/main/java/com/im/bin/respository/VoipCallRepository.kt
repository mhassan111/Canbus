package com.im.bin.respository

import android.content.Context
import androidx.lifecycle.LiveData
import com.im.bin.db.AppDatabase
import com.im.bin.db.dao.VoipCallDao
import com.im.bin.db.entities.VoipCall

class VoipCallRepository constructor(
    private val context: Context
) {

    private lateinit var calls: LiveData<List<VoipCall>>
    var voipCallDao: VoipCallDao = AppDatabase.getInstance(context)!!.voipCallDao()
    private var voipMessenger: String = ""

    constructor(context: Context, voipMessenger: String) : this(context) {
        this.voipMessenger = voipMessenger
        calls = voipCallDao.selectAllVoipCalls(voipMessenger)
    }

    fun getAllVoipCalls(): List<VoipCall> {
        return voipCallDao.getAllVoipCalls(voipMessenger)
    }

    fun deleteVoipCalls() {
        voipCallDao.deleteVoipCalls(voipMessenger)
    }

    fun getVoipCallsLive(): LiveData<List<VoipCall>> {
        return calls
    }

    fun updateConversation(voipCalls: List<VoipCall>) {
        for (voipCall in voipCalls) {
            if (checkMessageNotExitsAlready(voipCall.voipId)) {
                voipCallDao.insert(voipCall)
            }
        }
    }

    fun deleteVoipCall(voipId: String) {
        voipCallDao.deleteVoipCall(voipId)
    }

    fun insertVoipCall(voipCall: VoipCall) {
        voipCallDao.insert(voipCall)
    }

    fun checkMessageNotExitsAlready(messageId: String): Boolean =
        voipCallDao.checkIfAlreadyExist(messageId) == null
}