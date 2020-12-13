package com.im.bin

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.multidex.MultiDexApplication
import com.crashlytics.android.Crashlytics
import com.google.android.gms.ads.MobileAds
import com.im.bin.di.AppInjector
import com.im.bin.di.component.AppComponent
import com.im.bin.di.component.DaggerAppComponent
import com.im.bin.services.ForegroundService
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import io.fabric.sdk.android.Fabric
import timber.log.Timber
import java.util.*
import javax.inject.Inject

class MyApplication : MultiDexApplication(), HasAndroidInjector {

    private lateinit var appComponent: AppComponent

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchingAndroidInjector
    }

    override fun onCreate() {
        super.onCreate()
        MobileAds.initialize(this, getString(R.string.app_id))
        enableFabric(BuildConfig.DEBUG)
        appComponent = DaggerAppComponent.builder().application(this).build()
        appComponent.inject(this)
        AppInjector.init(this)
        context = this
        createNotificationChannel()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun enableFabric(isDebuggable: Boolean?) {
        val fabric = Fabric.Builder(this)
            .kits(Crashlytics())
            .debuggable(isDebuggable!!)
            .build()
        Fabric.with(fabric)
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                ForegroundService.CHANNEL_ID,
                "WhatsDeletedStore Service",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val notificationManager = getSystemService(NotificationManager::class.java)
            Objects.requireNonNull(notificationManager).createNotificationChannel(serviceChannel)
        }
    }

    companion object {
        private lateinit var context: Context
        fun getContext(): Context {
            return context.applicationContext
        }
    }
}