package com.im.bin.viewModel

import android.content.Context
import androidx.lifecycle.*
import com.im.bin.appUtils.WhatsAppMediaUtil
import com.im.bin.db.entities.WhatsAppMedia
import com.im.bin.respository.WhatsAppMediaRepository
import io.reactivex.Single
import kotlinx.coroutines.withContext
import java.util.*

class WhatsAppPhotosViewModel constructor(
    private val context: Context,
    private val mediaType: String
) : ViewModel() {

    var whatsAppMediaRepository: WhatsAppMediaRepository =
        WhatsAppMediaRepository(context, mediaType)
    var whatsAppMediaList: LiveData<List<WhatsAppMedia>>

    private val _mediaList = MutableLiveData<List<WhatsAppMedia>>()
    private val _filteredMediaList = MutableLiveData<List<WhatsAppMedia>>()
    private val filteredMediaList: LiveData<List<WhatsAppMedia>> = _filteredMediaList

    private var _loadDataSection = MutableLiveData(true)
    val loadDataSection: LiveData<Boolean> = _loadDataSection
    private var _loadProgressBar = MutableLiveData(false)
    private val _selectedMedia = MutableLiveData<WhatsAppMedia>()
    private val selectedWhatsAppMedia: LiveData<WhatsAppMedia> = _selectedMedia

    init {
        whatsAppMediaList = liveData {
            val messages = whatsAppMediaRepository.getWhatsAppMediaListLive()
            emitSource(messages)
        }
    }

    suspend fun retrieveAllPhotos() =
        withContext(viewModelScope.coroutineContext) {
            WhatsAppMediaUtil.traverseThroughWhatsAppDirectory(
                context,
                mediaType
            )
        }

    suspend fun deleteMedia(mediaId: String) =
        withContext(viewModelScope.coroutineContext) {
            whatsAppMediaRepository.deleteMedia(mediaId)
        }

    suspend fun deleteAllMedia() =
        withContext(viewModelScope.coroutineContext) {
            whatsAppMediaRepository.deleteAll(mediaType)
        }

    fun setOriginalWhatsAppMediaList(medias: List<WhatsAppMedia>) {
        _mediaList.value = medias
    }

    fun setWhatsAppMediaList(medias: List<WhatsAppMedia>) {
        _filteredMediaList.value = medias
    }

    fun getMediaList(): LiveData<List<WhatsAppMedia>> {
        return filteredMediaList
    }

    fun selectWhatsAppMedia(media: WhatsAppMedia) {
        _selectedMedia.value = media
    }

    fun getSelectedWhatsAppMedia(): LiveData<WhatsAppMedia> {
        return selectedWhatsAppMedia
    }

    fun performSearchQuery(query: String): Single<List<WhatsAppMedia>> = Single.create { emitter ->
        if (query.isNotEmpty()) {
            val list = _mediaList.value!!
            val filteredList = list.filter {
                it.mediaDate?.toLowerCase(Locale.ROOT)!!
                    .startsWith(query.toLowerCase(Locale.ROOT))
            }.toList()
            emitter.onSuccess(filteredList)
        } else {
            emitter.onSuccess(_mediaList.value!!)
        }
    }

    fun setLoadDataSection(load: Boolean) {
        _loadDataSection.value = load
    }
}