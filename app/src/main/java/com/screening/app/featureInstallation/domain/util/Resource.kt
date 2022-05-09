package com.screening.app.featureInstallation.domain.util

import java.lang.Exception

sealed class Resource<T> {

    data class Success<T>(
        val data: T? = null,
        val exception: Exception? = null,
        val message: String? = null
    ) : Resource<T>()

    class Error<T>(val exception: Exception? = null, val message: String? = null) : Resource<T>()
}
