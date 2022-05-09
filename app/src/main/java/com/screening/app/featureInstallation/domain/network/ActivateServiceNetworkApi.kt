package com.screening.app.featureInstallation.domain.network

import com.screening.app.featureInstallation.domain.models.DeviceActivation
import com.screening.app.featureInstallation.domain.util.ActivateServiceApiResponse

interface ActivateServiceNetworkApi {
    suspend fun activateService(deviceActivation: DeviceActivation): ActivateServiceApiResponse
}