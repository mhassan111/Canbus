package com.os.system.network.api

import com.os.system.featureInstallation.domain.models.ActivateDevice
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface TOSApi {

    @POST("activate-license")
    @Headers("Content-Type: application/json")
    fun activateService(@Body activateDevice: ActivateDevice) : Call<ResponseBody>

}