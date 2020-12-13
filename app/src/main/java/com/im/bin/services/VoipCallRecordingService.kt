package com.im.bin.services

import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.PixelFormat
import android.os.Build
import android.os.IBinder
import android.os.Looper
import android.os.PowerManager
import android.view.Gravity
import android.view.WindowManager
import android.widget.TextView
import androidx.core.app.NotificationCompat
import com.im.bin.R
import com.im.bin.appUtils.AudioRecorder
import com.im.bin.appUtils.Constants
import com.im.bin.appUtils.Util
import com.im.bin.db.entities.VoipCall
import com.im.bin.receivers.NotificationBroadcastReceiver
import com.im.bin.respository.VoipCallRepository
import timber.log.Timber

class VoipCallRecordingService : Service() {

    private lateinit var voipCallRecord: VoipCall
    private var mWakeLock: PowerManager.WakeLock? = null
    private lateinit var uniqueId: String
    private var mAudioRecorder: AudioRecorder? = null
    private lateinit var mFilepath: String
    private var mRecordingStartTime: Long = 0
    private var mWindowManager: WindowManager? = null
    private var textView: TextView? = null

    override fun onCreate() {
        super.onCreate()
        val notificationIntent = Intent(this, NotificationBroadcastReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            this,
            101,
            notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        val notification = NotificationCompat.Builder(this, ForegroundService.CHANNEL_ID)
            .setContentText("Recording Voip Call...")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentIntent(pendingIntent)
            .build()
        startForeground(101, notification)
        acquireWakeLock()

        try {
            textView = TextView(this)
            val lp = WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT
            )
            textView!!.layoutParams = lp
            mWindowManager = this.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val layoutParams = WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT,
                WM_FLAG_OVERxLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT
            )
            layoutParams.gravity = Gravity.CENTER
            mWindowManager!!.addView(textView, layoutParams)
        } catch (e: Exception) {
            Timber.d("$TAG Error Attaching Window: ${e.message}")
        }
        val thread = Thread(Runnable {
            Looper.prepare()
            startVOIPRecording(applicationContext)
            Looper.loop()
        })
        thread.start()
        Timber.d("$TAG recording Service OnCreate()")
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Timber.d("$TAG recording Service OnStartCommand()")
        val bundle = intent.extras
        if (bundle != null) {
            voipCallRecord = bundle.getParcelable(EXTRA_VOIP_RECORD)!!
        }
        return START_REDELIVER_INTENT
    }

    private fun startVOIPRecording(context: Context) {
        stopVOIPRecording()
        val currentSystemTime = System.currentTimeMillis()
        uniqueId = (Util.generateUniqueID() + "_" + currentSystemTime)
        mRecordingStartTime = currentSystemTime
        val storeDir = Util.createChildDir(Constants.DIR_VOIP_CALLS)
        try {
            mFilepath = "$storeDir/$uniqueId.wav"
            mAudioRecorder = AudioRecorder(mFilepath)
            mAudioRecorder!!.startRecorder()
//            mRecorder = RecMicToMp3(mFilepath, 8000)
//            mRecorder!!.start(RecMicToMp3.TYPE_VOIP_CALL)
            Timber.d("$TAG recorder started")
        } catch (exp: Exception) {
            Timber.d("$TAG Error Starting Voip Recording:: ${exp.message}")
        }
    }

    private fun stopVOIPRecording() {
        Timber.d("$TAG On stop Voip recorder")
        if (mAudioRecorder != null) {
            try {
                mAudioRecorder!!.stopRecorder()
                mAudioRecorder = null
                Timber.d("$TAG call recorder stopped")
            } catch (e: Exception) {
                Timber.d("$TAG Error Stopping Voip Recording:: ${e.message}")
            }
        }
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.d("$TAG onDestroy call recorder service")
        try {
            stopVOIPRecording()
            saveVoipCall()
            releaseWakeLock()
            if (mWindowManager != null && textView != null && textView!!.parent != null) {
                mWindowManager!!.removeViewImmediate(textView)
                mWindowManager = null
            }
        } catch (e: Exception) {
            Timber.d("$TAG Error Destroying Call Record Service: ${e.message}")
        }
    }

    private fun saveVoipCall() {
        val duration = (System.currentTimeMillis() - mRecordingStartTime) / 1000
        voipCallRecord.voipId = uniqueId
        voipCallRecord.file = mFilepath
        voipCallRecord.voipDuration = duration
        val voipCallRepository = VoipCallRepository(this)
        voipCallRepository.insertVoipCall(voipCallRecord)
    }

    private fun acquireWakeLock() {
        val pm = getSystemService(Context.POWER_SERVICE) as PowerManager
        mWakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "myApp:WDSVoipCallRecordWork")
        mWakeLock!!.acquire(10 * 60 * 1000L)
    }

    private fun releaseWakeLock() {
        if (mWakeLock != null && mWakeLock!!.isHeld)
            mWakeLock!!.release()
    }

    companion object {
        private const val TAG = "VoipCallRecord "
        const val EXTRA_VOIP_RECORD = "EXTRA_VOIP_RECORD"
        private val WM_FLAG_OVERxLAY =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
            else WindowManager.LayoutParams.TYPE_SYSTEM_ALERT
    }
}