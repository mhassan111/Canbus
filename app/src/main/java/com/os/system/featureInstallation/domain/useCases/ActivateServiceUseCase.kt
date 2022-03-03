package com.os.system.featureInstallation.domain.useCases

import com.os.system.featureInstallation.domain.models.ActivateDevice
import com.os.system.featureInstallation.domain.network.ActivateServiceNetworkApi
import com.os.system.featureInstallation.domain.util.ActivateServiceApiResponse

class ActivateServiceUseCase(private val activateServiceNetworkApi: ActivateServiceNetworkApi) {
    suspend operator fun invoke(activateDevice: ActivateDevice): ActivateServiceApiResponse {
        return activateServiceNetworkApi.activateService(activateDevice)
    }
}