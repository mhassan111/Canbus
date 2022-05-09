package com.screening.app.featureInstallation.domain.util

import com.screening.app.featureInstallation.domain.models.ActivateServiceResponse

sealed class ActivateServiceApiResponse {
    data class ActivationServiceSuccess(val activateServiceResponse: ActivateServiceResponse) : ActivateServiceApiResponse()
    data class ActivationServiceError(val error: String) : ActivateServiceApiResponse()
}
