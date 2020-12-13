package com.im.bin.dialog

import android.app.Dialog
import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer
import android.media.MediaPlayer.OnCompletionListener
import android.os.Handler
import android.view.Window
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import com.im.bin.R
import com.im.bin.appUtils.FontManager
import kotlinx.android.synthetic.main.audio_player_dialog.*
import java.io.IOException

@Suppress("DEPRECATION")
class AudioPlayerDialog(
    context: Context,
    private val titleTxt: String,
    private val filePath: String
) :
    Dialog(context, R.style.CustomAlertDialogTheme) {

    var mMediaPlayer: MediaPlayer? = null
    private val mHandler = Handler()
    private var isPlaying = false

    var playIcon: TextView? = null
    var duration: TextView? = null
    var title: TextView? = null
    var mSeekBar: SeekBar? = null
    var closeButton: ImageView? = null

    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.audio_player_dialog)
        FontManager.markAsIconContainer(
            root_layout,
            FontManager.getTypeface(context, FontManager.FONT_AWESOME)
        )

        title = findViewById(R.id.title)
        closeButton = findViewById(R.id.closeButton)
        duration = findViewById(R.id.duration)
        mSeekBar = findViewById(R.id.seekbar)
        playIcon = findViewById(R.id.playIcon)
        mMediaPlayer = MediaPlayer()
        addSeekBarChangeListener()
        initializeAudioPlayer()

        title!!.text = titleTxt
        mMediaPlayer!!.setOnPreparedListener {
            startPlayer()
            playIcon!!.text = context.resources.getString(R.string.fa_pause)
        }
        playIcon!!.setOnClickListener {
            if (!isPlaying) {
                startPlayer()
                playIcon!!.text = context.resources.getString(R.string.fa_pause)
            } else {
                pausePlayer()
                playIcon!!.text = context.resources.getString(R.string.fa_play)
            }
        }
        closeButton!!.setOnClickListener {
            stopPlayer()
            dismiss()
        }
    }

    private val runnable = Runnable {
        if (mMediaPlayer != null) {
            val currentPosition = mMediaPlayer!!.currentPosition / 1000
            mSeekBar!!.progress = currentPosition
            setDuration(currentPosition)
        }
        recursiveCallHandler()
    }

    private fun recursiveCallHandler() {
        mHandler.postDelayed(runnable, 1000)
    }

    private fun initializeSeekBar() {
        mSeekBar!!.progress = 0
        mSeekBar!!.max = mMediaPlayer!!.duration / 1000
        updateSeekBar()
    }

    private fun setDuration(dur: Int) {
        duration!!.text = "$dur Sec"
    }

    private fun updateSeekBar() {
        if (mMediaPlayer != null) {
            val currentPosition = mMediaPlayer!!.currentPosition / 1000
            mSeekBar!!.progress = currentPosition
        }
        mHandler.postDelayed(runnable, 1000)
    }

    private fun initializeAudioPlayer() {
        stopPlayer()
        try {
            mMediaPlayer = MediaPlayer()
            mMediaPlayer!!.setAudioStreamType(AudioManager.STREAM_MUSIC)
            mMediaPlayer!!.setDataSource(filePath)
            mMediaPlayer!!.prepareAsync()
            isPlaying = true
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: IllegalArgumentException) {
            e.message
        }
    }

    private fun resetPlayingSetting() {
        if (mMediaPlayer != null) {
            mSeekBar!!.progress = 0
            mMediaPlayer!!.seekTo(0)
        }
        playIcon!!.text = context.resources.getString(R.string.fa_play)
        setDuration(0)
    }

    private fun startPlayer() {
        if (mMediaPlayer != null) {
            mMediaPlayer!!.start()
            mMediaPlayer!!.setOnCompletionListener(onCompletionListener)
            initializeSeekBar()
            isPlaying = true
        }
    }

    private fun pausePlayer() {
        if (mMediaPlayer != null) {
            mMediaPlayer!!.pause()
        }
        isPlaying = false
    }

    private fun stopPlayer() {
        if (mMediaPlayer != null) {
            try {
                mMediaPlayer!!.stop()
                mMediaPlayer!!.reset()
                mMediaPlayer!!.release()
                mMediaPlayer = null
            } catch (e: Exception) {
                e.message
            }
        }
    }

    private val onCompletionListener = OnCompletionListener {
        isPlaying = false
        resetPlayingSetting()
    }

    private fun addSeekBarChangeListener() {
        mSeekBar!!.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(
                seekBar: SeekBar,
                progress: Int,
                fromUser: Boolean
            ) {
                if (mMediaPlayer != null && fromUser) {
                    mMediaPlayer!!.seekTo(progress * 1000)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
    }
}