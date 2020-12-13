package com.im.bin.di.module;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\'\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\'J\b\u0010\u0005\u001a\u00020\u0006H\'J\b\u0010\u0007\u001a\u00020\bH\'J\b\u0010\t\u001a\u00020\nH\'\u00a8\u0006\u000b"}, d2 = {"Lcom/im/bin/di/module/FragmentBuilderModule;", "", "()V", "bindChatConversationFragment", "Lcom/im/bin/fragments/im/ChatConversationFragment;", "bindWhatsAppChatFragment", "Lcom/im/bin/fragments/im/WhatsAppChatFragment;", "bindWhatsAppPhotosFragment", "Lcom/im/bin/fragments/im/WhatsAppMediaFragment;", "bindWhatsAppVoipFragment", "Lcom/im/bin/fragments/im/WhatsAppVoipFragment;", "app_debug"})
@dagger.Module()
public abstract class FragmentBuilderModule {
    
    @org.jetbrains.annotations.NotNull()
    @dagger.android.ContributesAndroidInjector()
    public abstract com.im.bin.fragments.im.WhatsAppVoipFragment bindWhatsAppVoipFragment();
    
    @org.jetbrains.annotations.NotNull()
    @dagger.android.ContributesAndroidInjector()
    public abstract com.im.bin.fragments.im.WhatsAppMediaFragment bindWhatsAppPhotosFragment();
    
    @org.jetbrains.annotations.NotNull()
    @dagger.android.ContributesAndroidInjector()
    public abstract com.im.bin.fragments.im.WhatsAppChatFragment bindWhatsAppChatFragment();
    
    @org.jetbrains.annotations.NotNull()
    @dagger.android.ContributesAndroidInjector()
    public abstract com.im.bin.fragments.im.ChatConversationFragment bindChatConversationFragment();
    
    public FragmentBuilderModule() {
        super();
    }
}