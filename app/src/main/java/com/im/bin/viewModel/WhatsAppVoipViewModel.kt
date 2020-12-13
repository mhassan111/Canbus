package com.im.bin.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.im.bin.db.entities.VoipCall
import com.im.bin.respository.VoipCallRepository
import io.reactivex.Single
import java.util.*

class WhatsAppVoipViewModel(
    private val context: Context,
    private val imType: String
) : BaseViewModel() {

    lateinit var voipCallRepository: VoipCallRepository
    lateinit var voipCallList: LiveData<List<VoipCall>>

    private val _calls: MutableList<VoipCall> = mutableListOf()
    private val _filteredCalls = MutableLiveData<List<VoipCall>>()
    private val filteredCalls: LiveData<List<VoipCall>> = _filteredCalls
    private val _selectedCall = MutableLiveData<VoipCall>()
    private val selectedCall: LiveData<VoipCall> = _selectedCall

    private var _loadDataSection = MutableLiveData(true)
    val loadDataSection: LiveData<Boolean> = _loadDataSection

    init {
        voipCallRepository = VoipCallRepository(context, imType)
        voipCallList = liveData {
            emitSource(voipCallRepository.getVoipCallsLive())
        }
    }

    fun loadVoipCalls() {
        val calls = voipCallRepository.getAllVoipCalls()
        setOriginalCalls(calls)
        setFilteredCalls(calls)
    }

    fun delete(voipCall: VoipCall) {
        voipCallRepository.deleteVoipCall(voipCall.voipId)
    }

    fun setOriginalCalls(calls: List<VoipCall>) {
        _calls.clear()
        _calls.addAll(calls)
    }

    fun setFilteredCalls(calls: List<VoipCall>) {
        _filteredCalls.value = calls
    }

    fun getFilteredVoipCalls(): LiveData<List<VoipCall>> {
        return filteredCalls
    }

    fun selectVoipCall(chat: VoipCall) {
        _selectedCall.value = chat
    }

    fun getSelectedVoipCall(): LiveData<VoipCall> {
        return selectedCall
    }

    fun performSearchQuery(query: String): Single<List<VoipCall>> = Single.create { emitter ->
        if (query.isNotEmpty()) {
            val list = _calls
            val filteredList = list.filter {
                it.voipName.toLowerCase(Locale.ROOT)
                    .startsWith(query.toLowerCase(Locale.ROOT))
            }.toList()
            emitter.onSuccess(filteredList)
        } else {
            emitter.onSuccess(_calls)
        }
    }

    fun setLoadDataSection(load: Boolean) {
        _loadDataSection.value = load
    }
}