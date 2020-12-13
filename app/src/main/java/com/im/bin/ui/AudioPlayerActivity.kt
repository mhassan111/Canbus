package com.im.bin.ui

import android.app.Activity
import android.content.Intent
import android.graphics.Typeface
import android.media.AudioManager
import android.media.MediaPlayer
import android.media.MediaPlayer.OnCompletionListener
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.provider.DocumentsContract
import android.text.TextUtils
import android.view.View
import android.widget.FrameLayout
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ShareCompat
import androidx.core.content.FileProvider
import com.im.bin.R
import com.im.bin.appUtils.FontManager
import com.im.bin.db.entities.WhatsAppMedia
import com.im.bin.dialog.DeleteAlertDialog
import com.im.bin.interfaces.CustomerAlertListener
import kotlinx.android.synthetic.main.activity_audio_play.*
import kotlinx.android.synthetic.main.activity_audio_play.seekbar
import kotlinx.android.synthetic.main.audio_player_dialog.*
import kotlinx.android.synthetic.main.audio_player_dialog.playIcon
import timber.log.Timber
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException

class AudioPlayerActivity : AppCompatActivity() {

    lateinit var filePath: String
    lateinit var whatsAppMedia: WhatsAppMedia
    private var mMediaPlayer: MediaPlayer? = null
    private val mHandler = Handler()
    private var isPlaying = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_audio_play)
        whatsAppMedia = intent.getParcelableExtra(EXTRA_MEDIA)!!
        filePath = whatsAppMedia.mediaPath!!

        val toolbar = findViewById<Toolbar>(R.id.tool_bar)
        val title = findViewById<TextView>(R.id.toolbar_title)
        setSupportActionBar(toolbar)
        val mActionBar = supportActionBar
        if (mActionBar != null) {
            mActionBar.setDisplayHomeAsUpEnabled(true)
            mActionBar.setHomeButtonEnabled(true)
        }

        val frameLayout = findViewById<FrameLayout>(R.id.audio_play_layout)
        val typeface = Typeface.createFromAsset(assets, "font/Roboto-Regular.ttf")
        val iconFont = FontManager.getTypeface(this, FontManager.FONT_AWESOME)
        FontManager.markAsIconContainer(frameLayout, iconFont)
        title.ellipsize = TextUtils.TruncateAt.END
        title.typeface = typeface

        val delete = findViewById<TextView>(R.id.delete)
        val share = findViewById<TextView>(R.id.share)
        title.text = whatsAppMedia.mediaName!!

        addSeekBarChangeListener()
        initializeAudioPlayer()

        mMediaPlayer!!.setOnPreparedListener { mp: MediaPlayer? ->
            startPlayer()
            playIcon.text = getString(R.string.fa_pause)
        }

        playIcon.setOnClickListener { v: View? ->
            if (!isPlaying) {
                startPlayer()
                playIcon.text = getString(R.string.fa_pause)
            } else {
                pausePlayer()
                playIcon.text = getString(R.string.fa_play_icon)
            }
        }

        share.setOnClickListener { shareFile() }
        delete.setOnClickListener { deleteFile() }
    }

    private fun shareFile() {
        val file = File(filePath)
        val uri = FileProvider.getUriForFile(
            this@AudioPlayerActivity,
            "$packageName.provider",
            file
        )
        val intent1 = ShareCompat.IntentBuilder.from(this@AudioPlayerActivity)
            .setType("image/*")
            .setStream(uri)
            .createChooserIntent()
            .addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        startActivity(intent1)
    }

    private fun deleteFile() {
        val dialog = DeleteAlertDialog(
            this,
            "Are you sure you want to delete this Voice Note ?",
            DeleteAlertDialog.TYPE_WHATS_APP_MEDIA,
            object : CustomerAlertListener {
                override fun onCancel() {

                }

                override fun onYes(isChecked: Boolean) {
                    val file = File(filePath)
                    if (file.exists()) {
                        if (!file.canWrite()) {
                            performFileSearch(file.absolutePath);
                        } else {
                            val isDeleted = file.delete()
                            if (isDeleted) {
                                MediaScannerConnection.scanFile(
                                    this@AudioPlayerActivity,
                                    arrayOf(filePath),
                                    null
                                ) { _, _ ->
                                    val resultIntent = Intent()
                                    resultIntent.putExtra("media", whatsAppMedia)
                                    setResult(Activity.RESULT_OK, resultIntent)
                                    finish()
                                }
                            }
                        }
                    }
                }
            })
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show()
    }

    public override fun onActivityResult(
        requestCode: Int, resultCode: Int,
        resultData: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, resultData)
        // The ACTION_OPEN_DOCUMENT intent was sent with the request code
        // READ_REQUEST_CODE. If the request code seen here doesn't match, it's the
        // response to some other intent, and the code below shouldn't run at all.
        if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            // The document selected by the user won't be returned in the intent.
            // Instead, a URI to that document will be contained in the return intent
            // provided to this method as a parameter.
            // Pull that URI using resultData.getData().
            var uri: Uri? = null
            if (resultData != null) {
                uri = resultData.data
                Timber.i("Uri: " + uri.toString())
                try {
                    DocumentsContract.deleteDocument(contentResolver, uri)
                    val resultIntent = Intent()
                    resultIntent.putExtra("media", whatsAppMedia)
                    setResult(Activity.RESULT_OK, resultIntent)
                    finish()
                } catch (e: FileNotFoundException) {
                    e.printStackTrace()
                }
            }
        }
    }

    /**
     * Fires an intent to spin up the "file chooser" UI and select an image.
     */
    fun performFileSearch(path: String?) {
        // ACTION_OPEN_DOCUMENT is the intent to choose a file via the system's file
        // browser.
        val imageUriLcl = FileProvider.getUriForFile(
            this@AudioPlayerActivity,
            "$packageName.provider",
            File(path)
        )
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT, imageUriLcl)
        // Filter to only show results that can be "opened", such as a
        // file (as opposed to a list of contacts or timezones)
        intent.addCategory(Intent.CATEGORY_OPENABLE)

        // Filter to show only images, using the image MIME data type.
        // If one wanted to search for ogg vorbis files, the type would be "audio/ogg".
        // To search for all documents available via installed storage providers,
        // it would be "*/*".
//        intent.setType("image/*");
        startActivityForResult(intent, READ_REQUEST_CODE)
    }

    private fun initializeSeekBar() {
        seekbar!!.progress = 0
        seekbar!!.max = mMediaPlayer!!.duration / 1000
        updateSeekBar()
    }

    private fun initializeAudioPlayer() {
        stopPlayer()
        try {
            mMediaPlayer = MediaPlayer()
            mMediaPlayer!!.setAudioStreamType(AudioManager.STREAM_MUSIC)
            mMediaPlayer!!.setDataSource(filePath)
            mMediaPlayer!!.setOnCompletionListener(onCompletionListener)
            mMediaPlayer!!.prepareAsync()
            isPlaying = true
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: IllegalArgumentException) {
            e.message
        }
    }

    private fun resetPlayingSetting() {
        playIcon!!.text = getString(R.string.fa_play_icon)
        if (mMediaPlayer != null) {
            mMediaPlayer!!.seekTo(0)
            initializeSeekBar()
        }
    }

    private fun startPlayer() {
        if (mMediaPlayer != null) {
            mMediaPlayer!!.start()
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

    private fun updateSeekBar() {
        runOnUiThread(object : Runnable {
            override fun run() {
                if (mMediaPlayer != null) {
                    val currentPosition = mMediaPlayer!!.currentPosition / 1000
                    seekbar!!.progress = currentPosition
                }
                mHandler.postDelayed(this, 1000)
            }
        })
    }

    private fun addSeekBarChangeListener() {
        seekbar!!.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
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

    override fun onDestroy() {
        super.onDestroy()
        stopPlayer()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        private const val READ_REQUEST_CODE = 42
        const val EXTRA_MEDIA = "EXTRA_MEDIA"
        const val EXTRA_POSITION = "EXTRA_POSITION"
    }

}