package com.im.bin.fragments.im

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.ads.AdView
import com.google.android.material.snackbar.Snackbar
import com.im.bin.R
import com.im.bin.adapter.ChatConversationAdapter
import com.im.bin.appUtils.*
import com.im.bin.databinding.ChatConversationBinding
import com.im.bin.db.entities.toIMMessage
import com.im.bin.dialog.CustomProgressDialog
import com.im.bin.dialog.DeleteAlertDialog
import com.im.bin.enums.IMType
import com.im.bin.firebase.FirebaseSource
import com.im.bin.interfaces.CustomerAlertListener
import com.im.bin.models.Chat
import com.im.bin.models.IMMessage
import com.im.bin.recyclerView.ItemTouchCallbacListener
import com.im.bin.ui.ChatConversationActivity
import com.im.bin.ui.base.BaseFragment
import com.im.bin.viewModel.ChatConversationViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_chat_conversation.*
import kotlinx.android.synthetic.main.fragment_whats_app_chat.swipe_refresh_layout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ChatConversationFragment : BaseFragment() {

    private lateinit var imType: String
    private lateinit var chat: Chat
    private lateinit var binding: ChatConversationBinding
    private lateinit var mContext: Context
    private lateinit var mAdapter: ChatConversationAdapter
    private val progressDialog = CustomProgressDialog()
    private lateinit var itemMoveCallbackListener: ItemTouchCallbacListener

    @Inject
    lateinit var firebaseSource: FirebaseSource

    private val chatConversationViewModel: ChatConversationViewModel by viewModels {
        viewModelFactory {
            ChatConversationViewModel(
                requireContext(),
                imType,
                chat.conversationId,
                firebaseSource
            )
        }
    }

    override fun onCreateFragment() {
        mContext = requireContext()
        val bundle = arguments
        bundle?.let {
            imType = bundle.getString(ChatConversationActivity.EXTRA_IM_TYPE)!!
            chat = bundle.getParcelable(ChatConversationActivity.EXTRA_CHAT)!!
        }
        setHasOptionsMenu(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.upload -> {
                if (Util.freeVersionInstalled(mContext)) {
                    ActivityUtil.launchPremiumFeaturesActivity(mContext)
                } else {
                    uploadConversation()
                }
            }
            R.id.refresh -> {
                if (Util.freeVersionInstalled(mContext)) {
                    ActivityUtil.launchPremiumFeaturesActivity(mContext)
                } else {
                    fetchConversation()
                }
            }
            R.id.delete -> {
                if (Util.freeVersionInstalled(mContext)) {
                    ActivityUtil.launchPremiumFeaturesActivity(mContext)
                } else {
                    delete()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<ChatConversationBinding>(
            inflater, R.layout.fragment_chat_conversation, container, false
        ).apply {
            viewModel = chatConversationViewModel
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun initViews(view: View) {
        adContainerView = view.findViewById(R.id.ad_view_container)
        adView = AdView(mContext)
        adView.adUnitId = Constants.bannerAdId
        adContainerView.addView(adView)

        mAdapter = ChatConversationAdapter(chatConversationViewModel)
        recycler_view.adapter = mAdapter
        itemMoveCallbackListener = ItemTouchCallbacListener(mContext, mAdapter)
        val touchHelper = ItemTouchHelper(itemMoveCallbackListener)
        mAdapter.setTouchHelper(touchHelper)
        touchHelper.attachToRecyclerView(recycler_view)
        recycler_view.apply {
            layoutManager = LinearLayoutManager(context)
        }

        setWhatsAppConversationObserver()
        setFilteredConversationObserver()
        setMessageRemovedObserver()
        setNetworkStateObserver()
        setOnRefreshListener()

        if (Util.showLoadAds(mContext)) {
            AdMobUtil.loadInterstitialAds(mContext)
            loadBannerAd()
        }
    }

    private fun setOnRefreshListener() {
        swipe_refresh_layout.setOnRefreshListener {
            fetchConversation()
            swipe_refresh_layout.isRefreshing = false
        }
    }

    private fun fetchConversation() {
        chatConversationViewModel.fetchConversation(imType, chat.conversationId)
    }

    private fun delete() {
        val dialog = DeleteAlertDialog(
            mContext,
            "Are you sure you want to delete this Conversation?",
            DeleteAlertDialog.TYPE_DELETE,
            object : CustomerAlertListener {
                override fun onCancel() {

                }

                override fun onYes(isChecked: Boolean) {
                    deleteConversation(isChecked)
                }
            })
        dialog.show()
    }

    private fun deleteConversation(deleteBackUp: Boolean) {
        if (deleteBackUp) {
            val compositeDisposable = CompositeDisposable()
            val disposable = firebaseSource.deleteConversation(imType, chat.conversationId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    showProgressDialog("Deleting...")
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
            chatConversationViewModel.deleteConversation(chat.conversationId)
        }
    }

    private fun showProgressDialog(title: String) {
        if (!progressDialog.isShowing())
            progressDialog.show(mContext, title)
    }

    private fun hideProgressDialog() {
        progressDialog.dialog?.dismiss()
    }

    private fun uploadConversation() {
        val conversations = chatConversationViewModel.getOriginalConversation()
        if (!conversations.isNullOrEmpty()) {
            val conversationList = mutableListOf<IMMessage>()
            conversationList.addAll(conversations)
            chatConversationViewModel.uploadConversation(imType, conversationList, conversations[0])
        }
    }

    fun onSearchQuery(query: String) {
        val compositeDisposable = CompositeDisposable()
        val disposable = chatConversationViewModel.performSearchQuery(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
            }.subscribe(
                {
                    chatConversationViewModel.setFilteredConversation(it)
                }, {
                }
            )
        compositeDisposable.add(disposable)
    }

    private fun setNetworkStateObserver() {
        chatConversationViewModel.getNetworkState().observe(viewLifecycleOwner, Observer {
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

    private fun setMessageRemovedObserver() {
        chatConversationViewModel.getMessageRemoved().observe(viewLifecycleOwner, Observer {
            it?.let {
                val snackbar =
                    Snackbar.make(conversation_layout, "Message Deleted", Snackbar.LENGTH_LONG)
                snackbar.duration = 4 * 1000
                snackbar.show()
            }
        })
    }

    private fun onConversationFetchSuccess(conversations: List<IMMessage>) {
        hideProgressDialog()
        if (conversations.isNotEmpty()) {
            viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
                chatConversationViewModel.updateConversation(conversations)
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
        chatConversationViewModel.uploadConversation(imType, conversations, conversations[0])
    }

    private fun onConversationUploadFailed(message: String?) {
        hideProgressDialog()
        message?.let {
            Util.showAlertDialog(mContext, "Error Uploading Conversation", message)
        }
    }

    private fun setWhatsAppConversationObserver() {
        when (imType) {
            IMType.WhatsApp.toString() -> {
                chatConversationViewModel.whatsAppMessagesList.observe(
                    viewLifecycleOwner,
                    Observer {
                        it?.let {
                            val whatsAppMessages = it.filter { message ->
                                message.date != null
                            }
                            val whatsAppConversations =
                                whatsAppMessages.map { imMessage ->
                                    imMessage.toIMMessage()
                                }
                            onConversationLisUpdated(whatsAppConversations)
                        }
                    })
            }
            IMType.Viber.toString() -> {
                chatConversationViewModel.viberMessagesList.observe(viewLifecycleOwner, Observer {
                    it?.let {
                        val viberConversations = it.map { imMessage -> imMessage.toIMMessage() }
                        onConversationLisUpdated(viberConversations)
                    }
                })
            }
            IMType.Line.toString() -> {
                chatConversationViewModel.lineMessagesList.observe(viewLifecycleOwner, Observer {
                    it?.let {
                        val viberConversations = it.map { imMessage -> imMessage.toIMMessage() }
                        onConversationLisUpdated(viberConversations)
                    }
                })
            }
            IMType.Instagram.toString() -> {
                chatConversationViewModel.instagramMessagesList.observe(
                    viewLifecycleOwner,
                    Observer {
                        it?.let {
                            val viberConversations = it.map { imMessage -> imMessage.toIMMessage() }
                            onConversationLisUpdated(viberConversations)
                        }
                    })
            }
            IMType.IMO.toString() -> {
                chatConversationViewModel.imoMessagesList.observe(viewLifecycleOwner, Observer {
                    it?.let {
                        val viberConversations = it.map { imMessage -> imMessage.toIMMessage() }
                        onConversationLisUpdated(viberConversations)
                    }
                })
            }
            IMType.SnapChat.toString() -> {
                chatConversationViewModel.snapChatMessagesList.observe(
                    viewLifecycleOwner,
                    Observer {
                        it?.let {
                            val viberConversations = it.map { imMessage -> imMessage.toIMMessage() }
                            onConversationLisUpdated(viberConversations)
                        }
                    })
            }
            IMType.Hike.toString() -> {
                chatConversationViewModel.hikeMessagesList.observe(viewLifecycleOwner, Observer {
                    it?.let {
                        val viberConversations = it.map { imMessage -> imMessage.toIMMessage() }
                        onConversationLisUpdated(viberConversations)
                    }
                })
            }
        }
    }

    private fun onConversationLisUpdated(imMessages: List<IMMessage>) {
        chatConversationViewModel.setOriginalConversation(imMessages)
        chatConversationViewModel.setFilteredConversation(imMessages)
    }

    private fun setFilteredConversationObserver() {
        chatConversationViewModel.getFilteredConversation().observe(viewLifecycleOwner, Observer {
            it?.let {
                mAdapter.submitList(it)
            }
        })
    }

    companion object {

        fun newInstance(imType: String, chat: Chat): ChatConversationFragment {
            val args = Bundle()
            val fragment = ChatConversationFragment()
            args.putString(ChatConversationActivity.EXTRA_IM_TYPE, imType)
            args.putParcelable(ChatConversationActivity.EXTRA_CHAT, chat)
            fragment.arguments = args
            return fragment
        }
    }
}