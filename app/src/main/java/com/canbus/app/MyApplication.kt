package com.canbus.app

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        myApplication = this
    }

    companion object {
        var myApplication: MyApplication? = null
            private set

        @JvmStatic
        val appContext: Context
            get() = myApplication!!.applicationContext
    }
}