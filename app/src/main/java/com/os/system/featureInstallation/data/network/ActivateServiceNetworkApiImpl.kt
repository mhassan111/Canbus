package com.os.system.featureInstallation.data.network

import com.os.system.featureInstallation.domain.models.ActivateDevice
import com.os.system.featureInstallation.domain.models.ActivateServiceResponse
import com.os.system.featureInstallation.domain.network.ActivateServiceNetworkApi
import com.os.system.featureInstallation.domain.util.ActivateServiceApiResponse
import com.os.system.network.api.TOSApi
import com.os.system.utilities.AppConstants
import com.os.system.utilities.logException
import com.os.system.utilities.logVerbose
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.ResponseBody
import retrofit2.Call
import java.lang.reflect.Type
import javax.inject.Inject

@Suppress("BlockingMethodInNonBlockingContext")
class ActivateServiceNetworkApiImpl @Inject constructor(private val tosApi: TOSApi) :
    ActivateServiceNetworkApi {

    override suspend fun activateService(activateDevice: ActivateDevice): ActivateServiceApiResponse {
        try {
            val apiCall: Call<ResponseBody> = tosApi.activateService(activateDevice)
            val response = apiCall.execute()
            if (response.isSuccessful) {
                logVerbose("${AppConstants.SERVICE_ACTIVATION_TYPE} response successful = $response")
                val responseBody = response.body()
                responseBody?.let {
                    val responseStr = it.string()
                    logVerbose("${AppConstants.SERVICE_ACTIVATION_TYPE} response body = $responseStr")
                    val listType: Type = object : TypeToken<ActivateServiceResponse>() {}.type
                    val activateServiceResponse =
                        Gson().fromJson<ActivateServiceResponse>(responseStr, listType)
                    return ActivateServiceApiResponse.ActivationServiceSuccess(
                        activateServiceResponse
                    )
                }
            } else {
                logVerbose("${AppConstants.SERVICE_ACTIVATION_TYPE} response failed = $response")
                logVerbose("${AppConstants.SERVICE_ACTIVATION_TYPE} error code = ${response.code()}")
                logVerbose("${AppConstants.SERVICE_ACTIVATION_TYPE} error message = ${response.message()}")
                logVerbose(
                    "${AppConstants.SERVICE_ACTIVATION_TYPE} response error body = ${
                        response.errorBody()?.string() ?: "Empty"
                    }"
                )
                return ActivateServiceApiResponse.ActivationServiceError("Service Activation Error")
            }
        } catch (e: Exception) {
            logException(
                "${AppConstants.SERVICE_ACTIVATION_TYPE} exception = ${e.message ?: ""}",
                throwable = e
            )
        }
        return ActivateServiceApiResponse.ActivationServiceError("Error While Calling Activating Service")
    }
}