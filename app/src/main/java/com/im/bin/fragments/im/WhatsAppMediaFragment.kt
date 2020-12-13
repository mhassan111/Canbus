package com.im.bin.fragments.im

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.gms.ads.AdView
import com.im.bin.R
import com.im.bin.adapter.WhatsAppMediaAdapter
import com.im.bin.appUtils.Constants
import com.im.bin.appUtils.GridSpacingItemDecoration
import com.im.bin.appUtils.Util
import com.im.bin.appUtils.WhatsAppMediaUtil
import com.im.bin.databinding.WhatsAppPhotosBinding
import com.im.bin.db.entities.WhatsAppMedia
import com.im.bin.enums.WhatsAppMediaType
import com.im.bin.ui.AudioPlayerActivity
import com.im.bin.ui.PlayVideoActivity
import com.im.bin.ui.PreviewImageActivity
import com.im.bin.ui.base.BaseFragment
import com.im.bin.viewModel.WhatsAppPhotosViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_whats_app_media.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WhatsAppMediaFragment : BaseFragment() {

    private lateinit var binding: WhatsAppPhotosBinding
    private lateinit var mContext: Context
    private lateinit var mAdapter: WhatsAppMediaAdapter
    private lateinit var mediaType: String

    private lateinit var upload: MenuItem
    private lateinit var refresh: MenuItem
    private lateinit var backUpChats: MenuItem
    private lateinit var refreshChats: MenuItem

    private val whatsAppPhotoViewModel: WhatsAppPhotosViewModel by viewModels {
        viewModelFactory { WhatsAppPhotosViewModel(mContext, mediaType) }
    }

    override fun onCreateFragment() {
        mContext = requireContext()
        val bundle = arguments
        bundle?.let {
            mediaType = bundle.getString(EXTRA_MEDIA_TYPE)!!
        }
        setHasOptionsMenu(true)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        upload = menu.findItem(R.id.upload)
        refresh = menu.findItem(R.id.refresh)
        backUpChats = menu.findItem(R.id.backup_chats)
        refreshChats = menu.findItem(R.id.refresh_chats)
        upload.isVisible = false
        refresh.isVisible = false
        backUpChats.isVisible = false
        refreshChats.isVisible = false
        refresh.isVisible = false
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.delete_all -> {
                deleteAllMedia()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllMedia() {
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
            WhatsAppMediaUtil.deleteDirectory(requireContext(), mediaType)
            whatsAppPhotoViewModel.deleteAllMedia()
            Toast.makeText(mContext, "All Deleted", Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<WhatsAppPhotosBinding>(
            inflater, R.layout.fragment_whats_app_media, container, false
        ).apply {
            viewModel = whatsAppPhotoViewModel
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun initViews(view: View) {
        adContainerView = view.findViewById(R.id.ad_view_container)
        adView = AdView(mContext)
        adView.adUnitId = Constants.bannerAdId
        adContainerView.addView(adView)

        mAdapter = WhatsAppMediaAdapter(mContext, whatsAppPhotoViewModel)
        howItWorks.movementMethod = LinkMovementMethod.getInstance()
        setHowItWorksText()
        recycler_view.adapter = mAdapter
        recycler_view.apply {
            layoutManager = GridLayoutManager(mContext, 2)
            addItemDecoration(GridSpacingItemDecoration(2, Util.dpToPx(mContext, 12), true))
        }

        setMediaListObserver()
        setWhatsAppMediaListObserver()
        setSelectedMediaObserver()
        setOnRefreshDataListener()
        setOnRefreshListener()
        loadPhotos()

        if (Util.showLoadAds(mContext)) {
            loadBannerAd()
        }
    }

    private fun setHowItWorksText() {
        howItWorksText.text = when (mediaType) {
            WhatsAppMediaType.WHATS_APP_PHOTOS.toString() -> {
                resources.getString(R.string.photos_working_text)
            }
            WhatsAppMediaType.WHATS_APP_VIDEOS.toString() -> {
                resources.getString(R.string.videos_working_text)
            }
            WhatsAppMediaType.WHATS_APP_VOICE_NOTES.toString() -> {
                resources.getString(R.string.voice_notes_working_text)
            }
            WhatsAppMediaType.WHATS_APP_STATUSES.toString() -> {
                resources.getString(R.string.statuses_working_text)
            }
            else -> {
                resources.getString(R.string.photos_working_text)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == 100) {
            val photo = data!!.getParcelableExtra<WhatsAppMedia>("media")!!
            viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
                whatsAppPhotoViewModel.deleteMedia(photo.mediaId)
            }
        }
    }

    private fun setOnRefreshListener() {
        swipe_refresh_layout.setOnRefreshListener {
            loadPhotos()
        }
    }

    private fun setOnRefreshDataListener() {
        refresh_data.setOnClickListener {
            loadPhotos()
        }
    }

    private fun setWhatsAppMediaListObserver() {
        whatsAppPhotoViewModel.whatsAppMediaList.observe(viewLifecycleOwner, Observer {
            it?.let {
                whatsAppPhotoViewModel.setOriginalWhatsAppMediaList(it)
                whatsAppPhotoViewModel.setWhatsAppMediaList(it)
            }
        })
    }

    private fun setMediaListObserver() {
        whatsAppPhotoViewModel.getMediaList().observe(viewLifecycleOwner, Observer {
            if (it.isNullOrEmpty()) {
                hideDataSection()
            } else {
                showDataSection()
                mAdapter.submitList(it)
            }
        })
    }

    private fun setSelectedMediaObserver() {
        whatsAppPhotoViewModel.getSelectedWhatsAppMedia().observe(viewLifecycleOwner, Observer {
            it?.let {
                when (mediaType) {
                    WhatsAppMediaType.WHATS_APP_PHOTOS.toString() -> {
                        val intent = Intent(activity, PreviewImageActivity::class.java)
                        intent.putExtra(PreviewImageActivity.EXTRA_MEDIA, it)
                        startActivityForResult(intent, 100)
                    }
                    WhatsAppMediaType.WHATS_APP_VIDEOS.toString() -> {
                        val intent = Intent(activity, PlayVideoActivity::class.java)
                        intent.putExtra(PreviewImageActivity.EXTRA_MEDIA, it)
                        startActivityForResult(intent, 100)
                    }
                    WhatsAppMediaType.WHATS_APP_VOICE_NOTES.toString() -> {
                        val intent = Intent(activity, AudioPlayerActivity::class.java)
                        intent.putExtra(PreviewImageActivity.EXTRA_MEDIA, it)
                        startActivityForResult(intent, 100)
                    }
                    WhatsAppMediaType.WHATS_APP_STATUSES.toString() -> {
                        if (Util.isImageFileExtension(it.mediaPath)) {
                            val intent = Intent(activity, PreviewImageActivity::class.java)
                            intent.putExtra(PreviewImageActivity.EXTRA_MEDIA, it)
                            startActivityForResult(intent, 100)
                        } else {
                            val intent = Intent(activity, PlayVideoActivity::class.java)
                            intent.putExtra(PreviewImageActivity.EXTRA_MEDIA, it)
                            startActivityForResult(intent, 100)
                        }
                    }
                }
            }
        })
    }

    fun onSearchQuery(query: String) {
        val compositeDisposable = CompositeDisposable()
        val disposable = whatsAppPhotoViewModel.performSearchQuery(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
            }.subscribe(
                {
                    whatsAppPhotoViewModel.setWhatsAppMediaList(it)
                }, {
                }
            )
        compositeDisposable.add(disposable)
    }

    private fun loadPhotos() {
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
            whatsAppPhotoViewModel.retrieveAllPhotos()
            onPhotosLoadingFinished()
        }
    }

    private fun onPhotosLoadingFinished() {
        if (swipe_refresh_layout.isRefreshing)
            swipe_refresh_layout.isRefreshing = false
    }

    private fun showDataSection() {
        whatsAppPhotoViewModel.setLoadDataSection(true)
    }

    private fun hideDataSection() {
        whatsAppPhotoViewModel.setLoadDataSection(false)
    }

    companion object {
        val TAG = WhatsAppMediaAdapter::class.java.simpleName
        private const val EXTRA_MEDIA_TYPE = "EXTRA_MEDIA_TYPE"

        fun newInstance(mediaType: String): WhatsAppMediaFragment {
            val fragment = WhatsAppMediaFragment()
            val extra = Bundle()
            extra.putString(EXTRA_MEDIA_TYPE, mediaType)
            fragment.arguments = extra
            return fragment
        }
    }
}
