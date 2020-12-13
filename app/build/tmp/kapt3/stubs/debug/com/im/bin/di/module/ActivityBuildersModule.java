package com.im.bin.di.module;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\'\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\'J\b\u0010\u0005\u001a\u00020\u0006H\'J\b\u0010\u0007\u001a\u00020\bH\'J\b\u0010\t\u001a\u00020\nH\'J\b\u0010\u000b\u001a\u00020\fH\'\u00a8\u0006\r"}, d2 = {"Lcom/im/bin/di/module/ActivityBuildersModule;", "", "()V", "bindChatConversationActivity", "Lcom/im/bin/ui/ChatConversationActivity;", "bindHomeActivity", "Lcom/im/bin/ui/HomeActivity;", "bindLoginActivity", "Lcom/im/bin/ui/auth/LoginActivity;", "bindMainActivity", "Lcom/im/bin/ui/MainActivity;", "bindRegistrationActivity", "Lcom/im/bin/ui/auth/SignUpActivity;", "app_debug"})
@dagger.Module()
public abstract class ActivityBuildersModule {
    
    @org.jetbrains.annotations.NotNull()
    @dagger.android.ContributesAndroidInjector()
    public abstract com.im.bin.ui.MainActivity bindMainActivity();
    
    @org.jetbrains.annotations.NotNull()
    @dagger.android.ContributesAndroidInjector()
    public abstract com.im.bin.ui.auth.LoginActivity bindLoginActivity();
    
    @org.jetbrains.annotations.NotNull()
    @dagger.android.ContributesAndroidInjector()
    public abstract com.im.bin.ui.HomeActivity bindHomeActivity();
    
    @org.jetbrains.annotations.NotNull()
    @dagger.android.ContributesAndroidInjector()
    public abstract com.im.bin.ui.ChatConversationActivity bindChatConversationActivity();
    
    @org.jetbrains.annotations.NotNull()
    @dagger.android.ContributesAndroidInjector()
    public abstract com.im.bin.ui.auth.SignUpActivity bindRegistrationActivity();
    
    public ActivityBuildersModule() {
        super();
    }
}