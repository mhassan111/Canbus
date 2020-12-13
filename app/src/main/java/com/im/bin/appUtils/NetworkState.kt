package com.im.bin.appUtils

import com.im.bin.models.IMMessage

sealed class NetworkState
data class NetworkError(val message: String?) : NetworkState()
data class NetworkLoading(val title: String) : NetworkState()
data class NetworkUploadSuccess(val conversations: MutableList<IMMessage>) : NetworkState()
data class NetworkUploadFailed(val message: String?) : NetworkState()
data class NetworkFetchSuccess(val conversations: List<IMMessage>) : NetworkState()
data class NetworkFetchFailed(val message: String?) : NetworkState()