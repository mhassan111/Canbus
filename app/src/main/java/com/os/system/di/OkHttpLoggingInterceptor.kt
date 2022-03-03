package com.os.system.di

import android.content.Context
import com.os.system.utilities.AppConstants
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class OkHttpLoggingInterceptor @Inject constructor(@ApplicationContext val context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val url = originalRequest.url.toString()
        val token: String = getServerToken(url)
        val request = if (token.isNotEmpty()) {
            chain.request()
                .newBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("x-access-token", token)
                .build()
        } else {
            chain.request()
                .newBuilder()
                .addHeader("Content-Type", "application/json")
                .build()
        }
        return chain.proceed(request)
    }

    companion object {
        private fun getServerToken(url: String): String {
            when {
//                url.startsWith(AppConstants.TOS_BASE_URL.lowercase()) -> {
//                    return AppConstants.serverFourAuth ?: ""
//                }
                else -> return ""
            }
        }
    }
}