package com.screening.app.di.module

import com.screening.app.network.api.TOSApi
import com.screening.app.utilities.AppConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkApiModule {

    @Provides
    @Singleton
    fun provideTOSApi(
        okHttpClient: OkHttpClient,
        @Named(AppConstants.TOS_BASE_URL_TAG) baseUrl: String,
    ): TOSApi {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
            .create(TOSApi::class.java)
    }
}
