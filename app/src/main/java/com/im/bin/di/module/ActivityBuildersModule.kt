package com.im.bin.di.module

import com.im.bin.ui.*
import com.im.bin.ui.auth.LoginActivity
import com.im.bin.ui.auth.SignUpActivity

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun bindLoginActivity(): LoginActivity

    @ContributesAndroidInjector
    abstract fun bindHomeActivity(): HomeActivity

    @ContributesAndroidInjector
    abstract fun bindChatConversationActivity(): ChatConversationActivity

    @ContributesAndroidInjector
    abstract fun bindRegistrationActivity(): SignUpActivity
}
