package com.im.bin.di.component

import android.app.Application
import com.im.bin.MyApplication
import com.im.bin.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(
    modules = [AndroidSupportInjectionModule::class, ActivityBuildersModule::class,
        FragmentBuilderModule::class,
        ServiceBuildersModule::class,
        AppModule::class]
)
@Singleton
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

    fun inject(myApplication: MyApplication)
}
