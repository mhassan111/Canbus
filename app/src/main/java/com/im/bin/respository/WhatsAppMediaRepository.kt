package com.im.bin.respository

import android.content.Context
import androidx.lifecycle.LiveData
import com.im.bin.db.AppDatabase
import com.im.bin.db.dao.WhatsAppMediaDao
import com.im.bin.db.entities.WhatsAppMedia

class WhatsAppMediaRepository constructor(
    private val context: Context
) {

    private lateinit var mediaList: LiveData<List<WhatsAppMedia>>
    var whatsAppMediaDao: WhatsAppMediaDao = AppDatabase.getInstance(context)!!.whatsAppMediaDao()
    private var mediaType: String = ""

    constructor(context: Context, mediaType: String) : this(context) {
        this.mediaType = mediaType
        mediaList = whatsAppMediaDao.selectAllWhatsAppMedia(mediaType)
    }

    fun getWhatsAppMediaListLive(): LiveData<List<WhatsAppMedia>> {
        return mediaList
    }

    fun insertMedia(whatsAppMedia: WhatsAppMedia) {
        whatsAppMediaDao.insert(whatsAppMedia)
    }

    fun insertMedia(whatsAppMediaList: List<WhatsAppMedia>) {
        for (media in whatsAppMediaList) {
            if (whatsAppMediaDao.checkIfAlreadyExists(media.mediaId) == null) {
                whatsAppMediaDao.insert(whatsAppMediaList)
            }
        }
    }

    fun deleteMedia(mediaId: String) {
        whatsAppMediaDao.update(mediaId, "1")
    }

    fun deleteAll(mediaType: String) {
        whatsAppMediaDao.deleteAll(mediaType, "1")
    }

    fun checkMediaNotExitsAlready(mediaId: String): Boolean =
        whatsAppMediaDao.checkIfAlreadyExists(mediaId) == null
}