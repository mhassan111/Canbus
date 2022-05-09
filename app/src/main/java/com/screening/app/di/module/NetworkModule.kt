package com.screening.app.di.module

import android.content.Context
import com.screening.app.utilities.AppConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module(includes = [NetworkApiModule::class])
object NetworkModule {

    @Provides
    @Named(AppConstants.TOS_BASE_URL_TAG)
    fun provideBaseUrlString(): String {
        return AppConstants.TOS_BASE_URL
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        httpLoggingInterceptor: OkHttpLoggingInterceptor,
        cache: Cache
    ): OkHttpClient {
        val okhttpBuilder = OkHttpClient.Builder()
//        if (BuildConfig.DEBUG) {
//            okhttpBuilder.addInterceptor(HttpLoggingInterceptor().apply {
//                this.level = HttpLoggingInterceptor.Level.BODY
//            })
//        }

        okhttpBuilder.addInterceptor(httpLoggingInterceptor)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .cache(cache)
        return okhttpBuilder.build()
    }

    @Provides
    @Singleton
    fun provideCache(@ApplicationContext context: Context): Cache {
        val cacheSize = 5 * 1024 * 1024
        val cacheDir = context.cacheDir
        return Cache(cacheDir, cacheSize.toLong())
    }
}