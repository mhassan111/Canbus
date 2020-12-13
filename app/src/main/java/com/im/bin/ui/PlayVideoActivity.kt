package com.im.bin.ui

import android.app.Activity
import android.content.Intent
import android.graphics.Typeface
import android.media.MediaPlayer
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Bundle
import android.provider.DocumentsContract
import android.text.TextUtils
import android.widget.FrameLayout
import android.widget.MediaController
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
import kotlinx.android.synthetic.main.activity_video_play.*
import timber.log.Timber
import java.io.File
import java.io.FileNotFoundException

class PlayVideoActivity : AppCompatActivity() {

    lateinit var filePath: String
    lateinit var whatsAppMedia: WhatsAppMedia

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_play)
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

        val frameLayout = findViewById<FrameLayout>(R.id.video_player_layout)
        val typeface = Typeface.createFromAsset(assets, "font/Roboto-Regular.ttf")
        val iconFont = FontManager.getTypeface(this, FontManager.FONT_AWESOME)
        FontManager.markAsIconContainer(frameLayout, iconFont)
        title.ellipsize = TextUtils.TruncateAt.END
        title.typeface = typeface

        val mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)
        val videoUri = Uri.parse(whatsAppMedia.mediaPath)
        videoView.setMediaController(mediaController)
        videoView.setVideoURI(videoUri)
        videoView.requestFocus()
        videoView.start()
        videoView.setOnPreparedListener { mp: MediaPlayer? -> }
        share.setOnClickListener { shareFile() }
        delete.setOnClickListener { deleteFile() }
    }

    private fun shareFile() {
        val file = File(filePath)
        val uri = FileProvider.getUriForFile(
            this@PlayVideoActivity,
            "$packageName.provider",
            file
        )
        val intent1 = ShareCompat.IntentBuilder.from(this@PlayVideoActivity)
            .setType("video/*")
            .setStream(uri)
            .createChooserIntent()
            .addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        startActivity(intent1)
    }

    private fun deleteFile() {
        val dialog = DeleteAlertDialog(
            this,
            "Are you sure you want to delete this Video ?",
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
                                    this@PlayVideoActivity,
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
            this@PlayVideoActivity,
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

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        private const val READ_REQUEST_CODE = 42
        const val EXTRA_MEDIA = "EXTRA_MEDIA"
    }
}