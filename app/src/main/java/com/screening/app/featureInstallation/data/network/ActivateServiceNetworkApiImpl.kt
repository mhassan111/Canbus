package com.screening.app.featureInstallation.data.network

import com.screening.app.featureInstallation.domain.models.DeviceActivation
import com.screening.app.featureInstallation.domain.models.ActivateServiceResponse
import com.screening.app.featureInstallation.domain.network.ActivateServiceNetworkApi
import com.screening.app.featureInstallation.domain.util.ActivateServiceApiResponse
import com.screening.app.network.api.TOSApi
import com.screening.app.utilities.AppConstants
import com.screening.app.utilities.logException
import com.screening.app.utilities.logVerbose
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.ResponseBody
import retrofit2.Call
import java.lang.reflect.Type
import javax.inject.Inject

@Suppress("BlockingMethodInNonBlockingContext")
class ActivateServiceNetworkApiImpl @Inject constructor(private val tosApi: TOSApi) :
    ActivateServiceNetworkApi {

    override suspend fun activateService(deviceActivation: DeviceActivation): ActivateServiceApiResponse {
        try {
            val apiCall: Call<ResponseBody> = tosApi.activateService(deviceActivation)
            val response = apiCall.execute()
            if (response.isSuccessful) {
                logVerbose("${AppConstants.DEVICE_ACTIVATION_TYPE} response successful = $response")
                val responseBody = response.body()
                responseBody?.let {
                    val responseStr = it.string()
                    logVerbose("${AppConstants.DEVICE_ACTIVATION_TYPE} response body = $responseStr")
                    val listType: Type = object : TypeToken<ActivateServiceResponse>() {}.type
                    val activateServiceResponse =
                        Gson().fromJson<ActivateServiceResponse>(responseStr, listType)
                    return ActivateServiceApiResponse.ActivationServiceSuccess(
                        activateServiceResponse
                    )
                }
            } else {
                logVerbose("${AppConstants.DEVICE_ACTIVATION_TYPE} response failed = $response")
                logVerbose("${AppConstants.DEVICE_ACTIVATION_TYPE} error code = ${response.code()}")
                logVerbose("${AppConstants.DEVICE_ACTIVATION_TYPE} error message = ${response.message()}")
                logVerbose(
                    "${AppConstants.DEVICE_ACTIVATION_TYPE} response error body = ${
                        response.errorBody()?.string() ?: "Empty"
                    }"
                )
                return ActivateServiceApiResponse.ActivationServiceError("Service Activation Error")
            }
        } catch (e: Exception) {
            logException(
                "${AppConstants.DEVICE_ACTIVATION_TYPE} exception = ${e.message ?: ""}",
                throwable = e
            )
        }
        return ActivateServiceApiResponse.ActivationServiceError("Error While Calling Activating Service")
    }
}