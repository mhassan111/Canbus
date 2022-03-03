package com.os.system.featureInstallation.domain.util

import com.os.system.featureInstallation.domain.models.ActivateServiceResponse

sealed class ActivateServiceApiResponse {

    data class ActivationServiceSuccess(val activateServiceResponse: ActivateServiceResponse) : ActivateServiceApiResponse()
    data class ActivationServiceError(val error: String) : ActivateServiceApiResponse()


}
