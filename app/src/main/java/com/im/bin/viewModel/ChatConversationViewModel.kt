package com.im.bin.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.im.bin.appUtils.*
import com.im.bin.db.entities.*
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

class ChatConversationViewModel(
    context: Context,
    private val imType: String,
    private val conversationId: String,
    private val firebaseSource: FirebaseSource
) : BaseViewModel() {

    lateinit var whatsAppRepository: WhatsAppRepository
    lateinit var viberRepository: ViberRepository
    lateinit var lineRepository: LineRepository
    lateinit var imoRepository: IMORepository
    lateinit var instagramRepository: InstagramRepository
    lateinit var snapChatRepository: SnapChatRepository
    lateinit var hikeRepository: HikeRepository

//    val messageList: LiveData<List<WhatsApp>> = whatsAppRepository.getWhatsAppMessagesLive()

//    val messageList: LiveData<List<IMMessage>> = liveData {
//        supervisorScope {
//            try {
//                val messages =
//                    whatsAppRepository.getWhatsAppMessagesLive()
//                val imMessages = messages.value!!
//                val list = imMessages.map {
//                    it.toIMMessage()
//                }.toMutableList()
//                val mutableLiveData = MutableLiveData<List<IMMessage>>()
//                mutableLiveData.value = list
//                emitSource(mutableLiveData)
//            } catch (e: Exception) {
//                Timber.d(e.message)
//            }
//        }
//    }

    lateinit var whatsAppMessagesList: LiveData<List<WhatsApp>>
    lateinit var viberMessagesList: LiveData<List<Viber>>
    lateinit var lineMessagesList: LiveData<List<Line>>
    lateinit var imoMessagesList: LiveData<List<IMO>>
    lateinit var instagramMessagesList: LiveData<List<Instagram>>
    lateinit var snapChatMessagesList: LiveData<List<SnapChat>>
    lateinit var hikeMessagesList: LiveData<List<Hike>>

    private val networkState = MutableLiveData<NetworkState>()
    private val _conversations: MutableList<IMMessage> = mutableListOf()
    private val _filteredConversations = MutableLiveData<List<IMMessage>>()
    private val filteredConversations: LiveData<List<IMMessage>> = _filteredConversations
    private val _messageRemoved = MutableLiveData<IMMessage>()
    private val messageRemoved: LiveData<IMMessage> = _messageRemoved

    init {
        when (imType) {
            IMType.WhatsApp.toString() -> {
                whatsAppRepository = WhatsAppRepository(context, conversationId)
                whatsAppMessagesList = liveData {
                    val messages =
                        whatsAppRepository.getWhatsAppMessagesLive()
                    emitSource(messages)
                }
            }
            IMType.Viber.toString() -> {
                viberRepository = ViberRepository(context, conversationId)
                viberMessagesList = liveData {
                    val messages =
                        viberRepository.getViberMessagesLive()
                    emitSource(messages)
                }
            }
            IMType.Line.toString() -> {
                lineRepository = LineRepository(context, conversationId)
                lineMessagesList = liveData {
                    val messages =
                        lineRepository.getLineMessagesLive()
                    emitSource(messages)
                }
            }
            IMType.Instagram.toString() -> {
                instagramRepository = InstagramRepository(context, conversationId)
                instagramMessagesList = liveData {
                    val messages =
                        instagramRepository.getInstagramMessagesLive()
                    emitSource(messages)
                }
            }
            IMType.IMO.toString() -> {
                imoRepository = IMORepository(context, conversationId)
                imoMessagesList = liveData {
                    val messages =
                        imoRepository.getIMOMessagesLive()
                    emitSource(messages)
                }
            }
            IMType.SnapChat.toString() -> {
                snapChatRepository = SnapChatRepository(context, conversationId)
                snapChatMessagesList = liveData {
                    val messages =
                        snapChatRepository.getSnapChatMessagesLive()
                    emitSource(messages)
                }
            }
            IMType.Hike.toString() -> {
                hikeRepository = HikeRepository(context, conversationId)
                hikeMessagesList = liveData {
                    val messages =
                        hikeRepository.getHikeMessagesLive()
                    emitSource(messages)
                }
            }
        }
    }


//    fun loadChatConversation(imType: String, conversationId: String) {
//        viewModelScope.launch(Dispatchers.Main) {
//            var imConversations = listOf<IMMessage>()
//            when (imType) {
//                IMType.WhatsApp.toString() -> {
//                    val conversations = databaseRepository.getWhatsAppMessages(conversationId)
//                    imConversations = conversations.map {
//                        it.toIMMessage()
//                    }
//                }
//                IMType.Viber.toString() -> {
//                    val conversations = databaseRepository.getViberMessages(conversationId)
//                    imConversations = conversations.map {
//                        it.toIMMessage()
//                    }
//                }
//                IMType.Line.toString() -> {
//                    val conversations = databaseRepository.getLineMessages(conversationId)
//                    imConversations = conversations.map {
//                        it.toIMMessage()
//                    }
//                }
//                IMType.Instagram.toString() -> {
//                    val conversations = databaseRepository.getInstagramMessages(conversationId)
//                    imConversations = conversations.map {
//                        it.toIMMessage()
//                    }
//                }
//                IMType.SnapChat.toString() -> {
//                    val conversations = databaseRepository.getSnapChatMessages(conversationId)
//                    imConversations = conversations.map {
//                        it.toIMMessage()
//                    }
//                }
//                IMType.IMO.toString() -> {
//                    val conversations = databaseRepository.getIMOMessages(conversationId)
//                    imConversations = conversations.map {
//                        it.toIMMessage()
//                    }
//                }
//                IMType.Hike.toString() -> {
//                    val conversations = databaseRepository.getHikeMessages(conversationId)
//                    imConversations = conversations.map {
//                        it.toIMMessage()
//                    }
//                }
//            }
//        }
//    }

    fun setOriginalConversation(conversations: List<IMMessage>) {
        _conversations.clear()
        _conversations.addAll(conversations)
    }

    fun setFilteredConversation(conversations: List<IMMessage>) {
        _filteredConversations.value = conversations
    }

    fun getFilteredConversation(): LiveData<List<IMMessage>> {
        return filteredConversations
    }

    fun getOriginalConversation(): MutableList<IMMessage> {
        return _conversations
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

    fun deleteMessage(position: Int) {
        val message = _filteredConversations.value!![position]
        when (imType) {
            IMType.WhatsApp.toString() -> {
                viewModelScope.launch(Dispatchers.Main) {
                    whatsAppRepository.deleteMessage(message.messageId)
                    _messageRemoved.value = message
                }
            }
            IMType.Viber.toString() -> {
                viewModelScope.launch(Dispatchers.Main) {
                    viberRepository.deleteMessage(message.messageId)
                    _messageRemoved.value = message
                }
            }
            IMType.Line.toString() -> {
                viewModelScope.launch(Dispatchers.Main) {
                    lineRepository.deleteMessage(message.messageId)
                    _messageRemoved.value = message
                }
            }
            IMType.Instagram.toString() -> {
                viewModelScope.launch(Dispatchers.Main) {
                    instagramRepository.deleteMessage(message.messageId)
                    _messageRemoved.value = message
                }
            }
            IMType.IMO.toString() -> {
                viewModelScope.launch(Dispatchers.Main) {
                    imoRepository.deleteMessage(message.messageId)
                    _messageRemoved.value = message
                }
            }
            IMType.SnapChat.toString() -> {
                viewModelScope.launch(Dispatchers.Main) {
                    snapChatRepository.deleteMessage(message.messageId)
                    _messageRemoved.value = message
                }
            }
            IMType.Hike.toString() -> {
                viewModelScope.launch(Dispatchers.Main) {
                    hikeRepository.deleteMessage(message.messageId)
                    _messageRemoved.value = message
                }
            }
        }
    }

    suspend fun deleteConversation(conversationId: String) {
        withContext(viewModelScope.coroutineContext) {
            when (imType) {
                IMType.WhatsApp.toString() -> {
                    whatsAppRepository.deleteConversation(conversationId)
                }
                IMType.Viber.toString() -> {
                    viberRepository.deleteConversation(conversationId)
                }
                IMType.Line.toString() -> {
                    lineRepository.deleteConversation(conversationId)
                }
                IMType.Instagram.toString() -> {
                    instagramRepository.deleteConversation(conversationId)
                }
                IMType.IMO.toString() -> {
                    imoRepository.deleteConversation(conversationId)
                }
                IMType.SnapChat.toString() -> {
                    snapChatRepository.deleteConversation(conversationId)
                }
                IMType.Hike.toString() -> {
                    hikeRepository.deleteConversation(conversationId)
                }
            }
        }
    }

    fun fetchConversation(imType: String, conversationId: String) {
        val disposable = firebaseSource.fetchCovnersation(imType, conversationId)
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

    fun uploadConversation(
        imType: String,
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

    fun performSearchQuery(query: String): Single<List<IMMessage>> = Single.create { emitter ->
        if (query.isNotEmpty()) {
            val list = _conversations
            val filteredList = list.filter {
                it.message.toLowerCase(Locale.ROOT).contains(query.toLowerCase(Locale.ROOT)) ||
                        it.messageDatetime.startsWith(query)
            }.toList()
            emitter.onSuccess(filteredList)
        } else {
            emitter.onSuccess(_conversations)
        }
    }

    fun getNetworkState(): LiveData<NetworkState> {
        return networkState
    }

    fun getMessageRemoved(): LiveData<IMMessage> {
        return messageRemoved
    }
}