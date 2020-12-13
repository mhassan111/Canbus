package com.im.bin.appUtils

import android.content.Context
import android.media.MediaScannerConnection
import com.im.bin.appUtils.Util.md5Hash
import com.im.bin.db.entities.WhatsAppMedia
import com.im.bin.enums.WhatsAppMediaType
import com.im.bin.respository.WhatsAppMediaRepository
import org.apache.commons.io.FileUtils
import org.apache.commons.io.FilenameUtils
import timber.log.Timber
import java.io.File
import java.util.*

object WhatsAppMediaUtil {

    fun deleteDirectory(context: Context, mediaType: String) {
        var dirPath = ""
        when (mediaType) {
            WhatsAppMediaType.WHATS_APP_PHOTOS.toString() -> {
                dirPath = context.getExternalFilesDir(Constants.DIR_PHOTOS)!!.absolutePath
            }
            WhatsAppMediaType.WHATS_APP_VIDEOS.toString() -> {
                dirPath = context.getExternalFilesDir(Constants.DIR_VIDEOS)!!.absolutePath
            }
            WhatsAppMediaType.WHATS_APP_VOICE_NOTES.toString() -> {
                dirPath = context.getExternalFilesDir(Constants.DIR_VOICE_NOTES)!!.absolutePath
            }
            WhatsAppMediaType.WHATS_APP_STATUSES.toString() -> {
                dirPath = context.getExternalFilesDir(Constants.DIR_STATUSES)!!.absolutePath
            }
        }
        val dir = File(dirPath)
        try {
            if (dir.exists() && dir.isDirectory) FileUtils.deleteDirectory(dir)
            MediaScannerConnection.scanFile(
                context,
                arrayOf(dirPath),
                null,
                null
            )
        } catch (e: Exception) {
            Timber.d("Error Deleting Directory: ${e.message}")
        }
    }

    fun traverseThroughWhatsAppDirectory(
        context: Context,
        mediaType: String
    ): ArrayList<WhatsAppMedia> {

        val whatsAppMediaList = ArrayList<WhatsAppMedia>()
        var whatsAppDirPath = ""

        when (mediaType) {
            WhatsAppMediaType.WHATS_APP_PHOTOS.toString() -> {
                whatsAppDirPath = Constants.WHATS_APP_STORAGE + "WhatsApp Images/"
            }
            WhatsAppMediaType.WHATS_APP_VIDEOS.toString() -> {
                whatsAppDirPath = Constants.WHATS_APP_STORAGE + "WhatsApp Video/"
            }
            WhatsAppMediaType.WHATS_APP_VOICE_NOTES.toString() -> {
                whatsAppDirPath = Constants.WHATS_APP_STORAGE + "WhatsApp Voice Notes/"
            }
            WhatsAppMediaType.WHATS_APP_STATUSES.toString() -> {
                whatsAppDirPath = "${Constants.WHATS_APP_STORAGE}.Statuses/"
            }
        }

        val file = File(whatsAppDirPath)
        if (file.exists() && file.isDirectory) {
            try {
                val dirFiles: List<String> = FileUtil.getListFiles(file)
                for (i in dirFiles.indices) {
                    val dirName = dirFiles[i]
                    val receiveFilePath = whatsAppDirPath + dirName
                    val receiveFile = File(receiveFilePath)
                    if (receiveFile.isDirectory) {
                        val sentDirPath = "$receiveFilePath/"
                        val sentDir = File(sentDirPath)
                        val sentPhotos: List<String> = FileUtil.getListFiles(sentDir)
                        for (j in sentPhotos.indices) {
                            val sentPhotoPath = sentDirPath + sentPhotos[j]
                            val sentFile = File(sentPhotoPath)
                            val sentTimeStamp = sentFile.lastModified()
                            if (!sentPhotoPath.contains(".nomedia")) {
                                var photoName: String = FilenameUtils.getName(sentPhotoPath)
                                if (FilenameUtils.getExtension(photoName).equals("opus")) {
                                    photoName = FilenameUtils.removeExtension(photoName)
                                    photoName = "$photoName.mp3"
                                }

                                val destPath = getDestinationFilePath(context, mediaType, photoName)
                                val mediaId = md5Hash(mediaType + photoName + sentTimeStamp)
                                val whatsAppMediaRepository =
                                    WhatsAppMediaRepository(context, mediaType)
                                if (whatsAppMediaRepository.checkMediaNotExitsAlready(mediaId)) {
                                    val whatsAppMedia =
                                        getMedia(
                                            destPath,
                                            mediaId,
                                            "Sent",
                                            sentTimeStamp,
                                            mediaType
                                        )
                                    FileUtil.copyFile(sentFile, File(destPath))
                                    whatsAppMediaList.add(whatsAppMedia)
                                }
                            }
                        }
                    } else if (receiveFile.isFile && !receiveFilePath.contains(".nomedia")) {
                        val receiveTimeStamp = receiveFile.lastModified()
                        var mediaName: String = FilenameUtils.getName(receiveFilePath)
                        if (FilenameUtils.getExtension(mediaName).equals("opus")) {
                            mediaName = FilenameUtils.removeExtension(mediaName)
                            mediaName = "$mediaName.mp3"
                        }
                        val destPath = getDestinationFilePath(context, mediaType, mediaName)
                        val mediaId: String = md5Hash(mediaType + mediaName + receiveTimeStamp)
                        val whatsAppMediaRepository = WhatsAppMediaRepository(context, mediaType)
                        if (whatsAppMediaRepository.checkMediaNotExitsAlready(mediaId)) {
                            val whatsAppMedia =
                                getMedia(destPath, mediaId, "Received", receiveTimeStamp, mediaType)
                            FileUtil.copyFile(receiveFile, File(destPath))
                            whatsAppMediaList.add(whatsAppMedia)
                        }
                    }
                }
            } catch (e: Exception) {
                Timber.d("Error retrieving WhatsApp Photos: %s", e.message)
            }
            if (whatsAppMediaList.size > 0) {
                val whatsAppMediaRepository = WhatsAppMediaRepository(context, mediaType)
                whatsAppMediaRepository.insertMedia(whatsAppMediaList)
            }
        }
        return whatsAppMediaList
    }

    private fun getDestinationFilePath(
        context: Context,
        mediaType: String,
        fileName: String
    ): String {
        when (mediaType) {
            WhatsAppMediaType.WHATS_APP_PHOTOS.toString() -> {
                val file = File(context.getExternalFilesDir(Constants.DIR_PHOTOS), fileName)
                return file.absolutePath
            }
            WhatsAppMediaType.WHATS_APP_VIDEOS.toString() -> {
                val file = File(context.getExternalFilesDir(Constants.DIR_VIDEOS), fileName)
                return file.absolutePath
            }
            WhatsAppMediaType.WHATS_APP_VOICE_NOTES.toString() -> {
                val file = File(context.getExternalFilesDir(Constants.DIR_VOICE_NOTES), fileName)
                return file.absolutePath
            }
            WhatsAppMediaType.WHATS_APP_STATUSES.toString() -> {
                val file = File(context.getExternalFilesDir(Constants.DIR_STATUSES), fileName)
                return file.absolutePath
            }
            else -> {
                return ""
            }
        }
    }

    private fun getMedia(
        filePath: String,
        mediaId: String?,
        direction: String,
        timeStamp: Long,
        type: String
    ): WhatsAppMedia {
        val whatsAppPhoto = WhatsAppMedia()
        whatsAppPhoto.mediaId = mediaId!!
        whatsAppPhoto.mediaName = FilenameUtils.getName(filePath)
        whatsAppPhoto.mediaPath = filePath
        whatsAppPhoto.mediaDate = DateTimeUtil.formatDate(timeStamp.toString())
        whatsAppPhoto.mediaDirection = direction
        whatsAppPhoto.mediaTimeStamp = timeStamp
        whatsAppPhoto.mediaType = type
        whatsAppPhoto.isDeleted = "0"
        return whatsAppPhoto
    }

    class ProcessingException : Exception {
        constructor(message: String?) : super(message) {}
        constructor() : super() {}
        constructor(message: String?, cause: Throwable?) : super(message, cause) {}
        constructor(cause: Throwable?) : super(cause) {}
    }
}