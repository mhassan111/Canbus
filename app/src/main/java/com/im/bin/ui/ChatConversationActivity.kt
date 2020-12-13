package com.im.bin.ui

import android.os.Bundle
import com.im.bin.R
import com.im.bin.appUtils.AdMobUtil
import com.im.bin.appUtils.Util
import com.im.bin.fragments.im.ChatConversationFragment
import com.im.bin.interfaces.OnSearchFilter
import com.im.bin.models.Chat
import com.im.bin.ui.base.BaseActivity

class ChatConversationActivity : BaseActivity() {

    companion object {
        const val EXTRA_IM_TYPE = "EXTRA_IM_TYPE"
        const val EXTRA_CHAT = "EXTRA_CHAT"
    }

    private lateinit var mChatConversationFragment: ChatConversationFragment
    private lateinit var imType: String
    private lateinit var chat: Chat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_conversation)
        setUpToolBar()
        displayHomeButton()
        initViews()
        loadIMFragment()
        setOnSearchQueryListener(object : OnSearchFilter {
            override fun onSearch(searchText: String) {
                onSearchQuery(searchText)
            }
        })
    }

    private fun onSearchQuery(query: String) {
        when (supportFragmentManager.findFragmentById(R.id.fragment_container)) {
            is ChatConversationFragment -> mChatConversationFragment.onSearchQuery(query)
        }
    }

    override fun initViews() {
        imType = intent.getStringExtra(EXTRA_IM_TYPE)!!
        chat = intent.getParcelableExtra(EXTRA_CHAT)!!
        setToolBarTitle(chat.conversationName)
    }

    private fun loadIMFragment() {
        mChatConversationFragment = ChatConversationFragment.newInstance(imType, chat)
        val supportFragmentManager = supportFragmentManager
        mChatConversationFragment.let {
            supportFragmentManager.beginTransaction().replace(
                R.id.fragment_container,
                mChatConversationFragment
            ).commit()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        finish()
        return true
    }
}