package com.im.bin.di.module

import com.im.bin.fragments.im.ChatConversationFragment
import com.im.bin.fragments.im.WhatsAppChatFragment
import com.im.bin.fragments.im.WhatsAppMediaFragment
import com.im.bin.fragments.im.WhatsAppVoipFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun bindWhatsAppVoipFragment(): WhatsAppVoipFragment

    @ContributesAndroidInjector
    abstract fun bindWhatsAppPhotosFragment(): WhatsAppMediaFragment

    @ContributesAndroidInjector
    abstract fun bindWhatsAppChatFragment(): WhatsAppChatFragment

    @ContributesAndroidInjector
    abstract fun bindChatConversationFragment(): ChatConversationFragment
}