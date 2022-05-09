package com.screening.app.featureInstallation.domain.useCases

import com.screening.app.featureInstallation.domain.models.DeviceActivation
import com.screening.app.featureInstallation.domain.network.ActivateServiceNetworkApi
import com.screening.app.featureInstallation.domain.util.ActivateServiceApiResponse

class ActivateServiceUseCase(private val activateServiceNetworkApi: ActivateServiceNetworkApi) {
    suspend operator fun invoke(deviceActivation: DeviceActivation): ActivateServiceApiResponse {
        return activateServiceNetworkApi.activateService(deviceActivation)
    }
}