package com.im.bin.fragments.im

import android.content.Context
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.ads.AdView
import com.im.bin.R
import com.im.bin.adapter.WhatsAppChatAdapter
import com.im.bin.appUtils.*
import com.im.bin.databinding.WhatsAppChatBinding
import com.im.bin.dialog.CustomProgressDialog
import com.im.bin.dialog.DeleteAlertDialog
import com.im.bin.firebase.FirebaseSource
import com.im.bin.interfaces.CustomerAlertListener
import com.im.bin.models.IMMessage
import com.im.bin.ui.ChatConversationActivity
import com.im.bin.ui.base.BaseFragment
import com.im.bin.viewModel.WhatsAppChatViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_whats_app_chat.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class WhatsAppChatFragment : BaseFragment() {

    private lateinit var binding: WhatsAppChatBinding
    private lateinit var mContext: Context
    private lateinit var mAdapter: WhatsAppChatAdapter
    private lateinit var imType: String
    private val progressDialog = CustomProgressDialog()

    @Inject
    lateinit var firebaseSource: FirebaseSource

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val whatsAppChatViewModel: WhatsAppChatViewModel by viewModels {
        viewModelFactory {
            WhatsAppChatViewModel(mContext, imType, firebaseSource)
        }
    }

    override fun onCreateFragment() {
        mContext = requireContext()
        val bundle = arguments
        bundle?.let { imType = bundle.getString(EXTRA_IM_TYPE)!! }
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<WhatsAppChatBinding>(
            inflater, R.layout.fragment_whats_app_chat, container, false
        ).apply {
            viewModel = whatsAppChatViewModel
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun initViews(view: View) {
        adContainerView = view.findViewById(R.id.ad_view_container)
        adView = AdView(mContext)
        adView.adUnitId = Constants.bannerAdId
        adContainerView.addView(adView)

        mAdapter = WhatsAppChatAdapter(whatsAppChatViewModel)
        howItWorks.movementMethod = LinkMovementMethod.getInstance()
        recycler_view.adapter = mAdapter
        recycler_view.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
        setChatsObserver()
        setSelectedChatObserver()
        setNetworkStateObserver()
        setOnRefreshListener()
        setReloadListener()

        if (Util.showLoadAds(mContext)) {
            loadBannerAd()
            AdMobUtil.loadInterstitialAds(mContext)
        }
        loadChats()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.backup_chats -> {
                if (Util.freeVersionInstalled(mContext)) {
                    ActivityUtil.launchPremiumFeaturesActivity(mContext)
                } else {
                    uploadChats()
                }
            }
            R.id.refresh_chats -> {
                if (Util.freeVersionInstalled(mContext)) {
                    ActivityUtil.launchPremiumFeaturesActivity(mContext)
                } else {
                    fetchChats()
                }
            }
            R.id.delete_all -> {
                if (Util.freeVersionInstalled(mContext)) {
                    ActivityUtil.launchPremiumFeaturesActivity(mContext)
                } else {
                    deleteAllChats()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllChats() {
        val dialog = DeleteAlertDialog(
            mContext,
            "Are you sure you want to delete All the $imType Chats?",
            DeleteAlertDialog.TYPE_DELETE,
            object : CustomerAlertListener {
                override fun onCancel() {

                }

                override fun onYes(isChecked: Boolean) {
                    deleteChats(isChecked)
                }
            })
        dialog.show()
    }

    private fun deleteChats(deleteBackUp: Boolean) {
        if (deleteBackUp) {
            val compositeDisposable = CompositeDisposable()
            val disposable = firebaseSource.deleteChats(imType)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    showProgressDialog("Deleting All Chats...")
                }.subscribe(
                    {
                        onDeleteSuccess()
                    }, {
                        onDeleteFailure()
                    }
                )
            compositeDisposable.add(disposable)
        } else {
            deleteConversationFromDB()
        }
    }

    private fun onDeleteSuccess() {
        deleteConversationFromDB()
        hideProgressDialog()
    }

    private fun onDeleteFailure() {
        hideProgressDialog()
        Util.showAlertDialog(mContext, "Error", "Couldn't Delete. Try Again")
    }

    private fun deleteConversationFromDB() {
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
            whatsAppChatViewModel.deleteChats()
        }
    }

    private fun fetchChats() {
        whatsAppChatViewModel.fetchConversations()
    }

    private fun uploadChats() {
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
            val conversations = whatsAppChatViewModel.getAllConversations()
            onAllConversationsLoaded(conversations)
        }
    }

    private fun onAllConversationsLoaded(allConversations: List<IMMessage>) {
        if (!allConversations.isNullOrEmpty()) {
            val conversationList = mutableListOf<IMMessage>()
            conversationList.addAll(allConversations)
            whatsAppChatViewModel.uploadConversations(conversationList, conversationList[0])
        }
    }

    private fun setNetworkStateObserver() {
        whatsAppChatViewModel.getNetworkState().observe(viewLifecycleOwner, Observer {
            it?.let {
                when (it) {
                    is NetworkLoading -> {
                        showProgressDialog(it.title)
                    }
                    is NetworkUploadSuccess -> {
                        onConversationUploaded(it.conversations)
                    }
                    is NetworkUploadFailed -> {
                        onConversationUploadFailed(it.message)
                    }
                    is NetworkFetchSuccess -> {
                        onConversationFetchSuccess(it.conversations)
                    }
                    is NetworkFetchFailed -> {
                        onConversationFetchFailed(it.message)
                    }
                }
            }
        })
    }

    private fun onConversationFetchSuccess(conversations: List<IMMessage>) {
        hideProgressDialog()
        if (conversations.isNotEmpty()) {
            viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
                whatsAppChatViewModel.updateConversation(conversations)
            }
            return
        }
    }

    private fun onConversationFetchFailed(message: String?) {
        hideProgressDialog()
        message?.let {
            Util.showAlertDialog(mContext, "Error Fetching Conversation", message)
        }
    }

    private fun onConversationUploaded(conversations: MutableList<IMMessage>) {
        conversations.removeAt(0)
        if (conversations.isEmpty()) {
            hideProgressDialog()
            Util.showAlertDialog(mContext, "Success", "Conversation Uploaded Successfully")
            return
        }
        whatsAppChatViewModel.uploadConversations(conversations, conversations[0])
    }

    private fun onConversationUploadFailed(message: String?) {
        hideProgressDialog()
        message?.let {
            Util.showAlertDialog(mContext, "Error Uploading Conversation", message)
        }
    }

    private fun showProgressDialog(title: String) {
        if (!progressDialog.isShowing())
            progressDialog.show(mContext, title)
    }

    private fun hideProgressDialog() {
        progressDialog.dialog?.dismiss()
    }

    private fun setReloadListener() {
        refresh_data.setOnClickListener {
            loadChats()
        }
    }

    private fun setOnRefreshListener() {
        swipe_refresh_layout.setOnRefreshListener {
            if (swipe_refresh_layout.isRefreshing) {
                loadChats()
            }
        }
    }

    private fun setChatsObserver() {
        whatsAppChatViewModel.getChats().observe(viewLifecycleOwner, Observer {
            if (it.isNullOrEmpty()) {
                hideDataSection()
            } else {
                showDataSection()
                mAdapter.submitList(it)
            }
            if (swipe_refresh_layout.isRefreshing) {
                swipe_refresh_layout.isRefreshing = false
            }
        })
    }

    private fun setSelectedChatObserver() {
        whatsAppChatViewModel.getSelectedChat().observe(viewLifecycleOwner, Observer {
            it?.let {
                ActivityUtil.launchChatConversationActivity(
                    mContext,
                    imType,
                    it
                )
            }
        })
    }

    fun onSearchQuery(query: String) {
        val compositeDisposable = CompositeDisposable()
        val disposable = whatsAppChatViewModel.performSearchQuery(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
            }.subscribe(
                {
                    whatsAppChatViewModel.setChatMessages(it)
                }, {
                }
            )
        compositeDisposable.add(disposable)
    }

    private fun loadChats() {
        whatsAppChatViewModel.loadChats()
    }

    private fun showDataSection() {
        whatsAppChatViewModel.setLoadDataSection(true)
    }

    private fun hideDataSection() {
        whatsAppChatViewModel.setLoadDataSection(false)
    }

    companion object {
        val TAG = WhatsAppChatFragment::class.java.simpleName
        const val EXTRA_IM_TYPE = "EXTRA_IM_TYPE"
        fun newInstance(imType: String): WhatsAppChatFragment {
            val args = Bundle()
            val fragment = WhatsAppChatFragment()
            args.putString(ChatConversationActivity.EXTRA_IM_TYPE, imType)
            fragment.arguments = args
            return fragment
        }
    }
}
