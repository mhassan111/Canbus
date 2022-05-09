package com.screening.app.network.api

import com.screening.app.featureInstallation.domain.models.DeviceActivation
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface TOSApi {

    @POST("activate-license")
    @Headers("Content-Type: application/json")
    fun activateService(@Body deviceActivation: DeviceActivation) : Call<ResponseBody>

}