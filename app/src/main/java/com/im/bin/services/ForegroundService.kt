package com.im.bin.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.Environment
import android.os.FileObserver
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.im.bin.R
import com.im.bin.appUtils.WhatsAppMediaUtil
import com.im.bin.enums.WhatsAppMediaType
import com.im.bin.observer.RecursiveFileObserver
import com.im.bin.receivers.NotificationBroadcastReceiver
import com.im.bin.threadPool.DefaultExecutorSupplier
import timber.log.Timber
import java.util.*


@Suppress("DEPRECATION")
class ForegroundService : Service() {

    companion object {
        const val CHANNEL_ID = "WhatsDeletedStore Channel"
    }

    private val recursiveFileObserver by lazy {
        RecursiveFileObserver(
            Environment.getExternalStorageDirectory()
                .toString() + "/WhatsApp/",
            FileObserver.CREATE,
            fileObserverEvent
        )
    }

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
        val notificationIntent = Intent(this, NotificationBroadcastReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            this,
            100,
            notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentText("Running in background...")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentIntent(pendingIntent)
            .build()
        startForeground(100, notification)
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                CHANNEL_ID,
                "WhatsDeletedStore Service",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val notificationManager = getSystemService(NotificationManager::class.java)
            Objects.requireNonNull(notificationManager).createNotificationChannel(serviceChannel)
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        recursiveFileObserver.startWatching()
        return START_STICKY
    }

    private val fileObserverEvent =
        RecursiveFileObserver.EventListener { event, file ->
            when (event) {
                FileObserver.CREATE -> {
                    file?.let {
                        Timber.d("FileObserver Create : %s", file.absolutePath)
                        onWhatsAppDirectoryModified()
                    }
                }
                FileObserver.DELETE -> {
                    file?.let {
                        Timber.d("FileObserver Delete : %s", file.absolutePath)
                    }
                }
                FileObserver.ALL_EVENTS -> {
                    file?.let {
                        Timber.d("FileObserver AllEvents : %s", file.absolutePath)
                    }
                }
            }
        }

    private fun onWhatsAppDirectoryModified() {
        DefaultExecutorSupplier.getInstance().fortBackgroundTasks()
            .submit {
                WhatsAppMediaUtil.traverseThroughWhatsAppDirectory(
                    this,
                    WhatsAppMediaType.WHATS_APP_PHOTOS.toString()
                )
                WhatsAppMediaUtil.traverseThroughWhatsAppDirectory(
                    this,
                    WhatsAppMediaType.WHATS_APP_VIDEOS.toString()
                )
                WhatsAppMediaUtil.traverseThroughWhatsAppDirectory(
                    this,
                    WhatsAppMediaType.WHATS_APP_VOICE_NOTES.toString()
                )
                WhatsAppMediaUtil.traverseThroughWhatsAppDirectory(
                    this,
                    WhatsAppMediaType.WHATS_APP_STATUSES.toString()
                )
            }
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        recursiveFileObserver.stopWatching()
    }
}