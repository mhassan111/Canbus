package com.im.bin.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.im.bin.appUtils.*
import com.im.bin.db.entities.toIMMessage
import com.im.bin.enums.IMType
import com.im.bin.firebase.FirebaseSource
import com.im.bin.models.*
import com.im.bin.respository.*
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class WhatsAppChatViewModel(
    private val context: Context,
    private val imType: String,
    private val firebaseSource: FirebaseSource
) : BaseViewModel() {

    lateinit var whatsAppRepository: WhatsAppRepository
    lateinit var viberRepository: ViberRepository
    lateinit var lineRepository: LineRepository
    lateinit var imoRepository: IMORepository
    lateinit var instagramRepository: InstagramRepository
    lateinit var snapChatRepository: SnapChatRepository
    lateinit var hikeRepository: HikeRepository

    private val networkState = MutableLiveData<NetworkState>()
    private val _chats = MutableLiveData<List<Chat>>()
    private val _filteredChats = MutableLiveData<List<Chat>>()
    private val filteredChats: LiveData<List<Chat>> = _filteredChats
    private var _loadDataSection = MutableLiveData(true)
    val loadDataSection: LiveData<Boolean> = _loadDataSection
    private val _selectedChat = MutableLiveData<Chat>()
    private val selectedChat: LiveData<Chat> = _selectedChat

    fun loadChats() {
        viewModelScope.launch(Dispatchers.Main) {
            var chats = listOf<Chat>()
            when (imType) {
                IMType.WhatsApp.toString() -> {
                    whatsAppRepository = WhatsAppRepository(context)
                    chats = whatsAppRepository.selectWhatsAppChats()
                }
                IMType.Viber.toString() -> {
                    viberRepository = ViberRepository(context)
                    chats = viberRepository.selectViberChats()
                }
                IMType.Line.toString() -> {
                    lineRepository = LineRepository(context)
                    chats = lineRepository.selectLineChats()
                }
                IMType.SnapChat.toString() -> {
                    snapChatRepository = SnapChatRepository(context)
                    chats = snapChatRepository.selectSnapChatChats()
                }
                IMType.Instagram.toString() -> {
                    instagramRepository = InstagramRepository(context)
                    chats = instagramRepository.selectInstagramChats()
                }
                IMType.Hike.toString() -> {
                    hikeRepository = HikeRepository(context)
                    chats = hikeRepository.selectHikeChats()
                }
                IMType.IMO.toString() -> {
                    imoRepository = IMORepository(context)
                    chats = imoRepository.selectIMOChats()
                }
            }
            setOriginalChats(chats)
            setChatMessages(chats)
        }
    }

    suspend fun updateConversation(conversations: List<IMMessage>) {
        withContext(viewModelScope.coroutineContext) {
            when (imType) {
                IMType.WhatsApp.toString() -> {
                    val messages = conversations.map { it.toWhatsApp() }
                    whatsAppRepository.updateConversation(messages)
                }
                IMType.Viber.toString() -> {
                    val messages = conversations.map { it.toViber() }
                    viberRepository.updateConversation(messages)
                }
                IMType.Line.toString() -> {
                    val messages = conversations.map { it.toLine() }
                    lineRepository.updateConversation(messages)
                }
                IMType.Instagram.toString() -> {
                    val messages = conversations.map { it.toInstagram() }
                    instagramRepository.updateConversation(messages)
                }
                IMType.IMO.toString() -> {
                    val messages = conversations.map { it.toIMO() }
                    imoRepository.updateConversation(messages)
                }
                IMType.SnapChat.toString() -> {
                    val messages = conversations.map { it.toSnapChat() }
                    snapChatRepository.updateConversation(messages)
                }
                IMType.Hike.toString() -> {
                    val messages = conversations.map { it.toHike() }
                    hikeRepository.updateConversation(messages)
                }
            }
        }
    }

    suspend fun deleteChats() {
        withContext(viewModelScope.coroutineContext) {
            when (imType) {
                IMType.WhatsApp.toString() -> {
                    whatsAppRepository.deleteChats()
                }
                IMType.Viber.toString() -> {
                    viberRepository.deleteChats()
                }
                IMType.Line.toString() -> {
                    lineRepository.deleteChats()
                }
                IMType.Instagram.toString() -> {
                    instagramRepository.deleteChats()
                }
                IMType.IMO.toString() -> {
                    imoRepository.deleteChats()
                }
                IMType.SnapChat.toString() -> {
                    snapChatRepository.deleteChats()
                }
                IMType.Hike.toString() -> {
                    hikeRepository.deleteChats()
                }
            }
        }
    }

    suspend fun getAllConversations(): List<IMMessage> =
        withContext(viewModelScope.coroutineContext) {
            when (imType) {
                IMType.WhatsApp.toString() -> {
                    whatsAppRepository.getAllWhatsAppMessages().map {
                        it.toIMMessage()
                    }
                }
                IMType.Viber.toString() -> {
                    viberRepository.getAllViberMessages().map {
                        it.toIMMessage()
                    }
                }
                IMType.Line.toString() -> {
                    lineRepository.getAllLineMessages().map {
                        it.toIMMessage()
                    }
                }
                IMType.Instagram.toString() -> {
                    instagramRepository.getAllInstagramMessages().map {
                        it.toIMMessage()
                    }
                }
                IMType.IMO.toString() -> {
                    imoRepository.getAllIMOMessages().map {
                        it.toIMMessage()
                    }
                }
                IMType.SnapChat.toString() -> {
                    snapChatRepository.getAllSnapChatMessages().map {
                        it.toIMMessage()
                    }
                }
                else -> {
                    withContext(viewModelScope.coroutineContext) {
                        hikeRepository.getAllHikeMessages().map {
                            it.toIMMessage()
                        }
                    }
                }
            }
        }

    fun fetchConversations() {
        val disposable = firebaseSource.fetchConversations(imType)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                networkState.value = NetworkLoading("Fetching...")
            }.subscribe({
                onConversationFetched(it)
            }, { th: Throwable? ->
                onConversationFetchFailed(th)
            })
        compositeDisposable.add(disposable)
    }

    fun uploadConversations(
        conversations: MutableList<IMMessage>,
        imMessage: IMMessage
    ) {
        val disposable = firebaseSource.uploadConversation(imType, imMessage)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                networkState.value = NetworkLoading("Uploading...")
            }.subscribe({
                onConversationUploaded(conversations)
            }, { th: Throwable? ->
                onConversationUploadFailed(th)
            })
        compositeDisposable.add(disposable)
    }

    private fun onConversationFetched(conversations: List<IMMessage>) {
        networkState.value = NetworkFetchSuccess(conversations)
    }

    private fun onConversationFetchFailed(throwable: Throwable?) {
        networkState.value = NetworkFetchFailed(throwable?.message)
    }

    private fun onConversationUploaded(conversations: MutableList<IMMessage>) {
        networkState.value = NetworkUploadSuccess(conversations)
    }

    private fun onConversationUploadFailed(throwable: Throwable?) {
        networkState.value = NetworkUploadFailed(throwable?.message)
    }

    private fun setOriginalChats(chats: List<Chat>) {
        _chats.value = chats
    }

    fun setChatMessages(chats: List<Chat>) {
        _filteredChats.value = chats
    }

    fun getChats(): LiveData<List<Chat>> {
        return filteredChats
    }

    fun selectChat(chat: Chat) {
        _selectedChat.value = chat
    }

    fun getSelectedChat(): LiveData<Chat> {
        return selectedChat
    }

    fun performSearchQuery(query: String): Single<List<Chat>> = Single.create { emitter ->
        if (query.isNotEmpty()) {
            val list = _chats.value!!
            val filteredList = list.filter {
                it.conversationName.toLowerCase(Locale.ROOT)
                    .startsWith(query.toLowerCase(Locale.ROOT))
            }.toList()
            emitter.onSuccess(filteredList)
        } else {
            emitter.onSuccess(_chats.value!!)
        }
    }

    fun setLoadDataSection(load: Boolean) {
        _loadDataSection.value = load
    }

    fun getNetworkState(): LiveData<NetworkState> {
        return networkState
    }
}