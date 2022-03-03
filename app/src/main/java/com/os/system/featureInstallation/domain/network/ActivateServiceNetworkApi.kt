package com.os.system.featureInstallation.domain.network

import com.os.system.featureInstallation.domain.models.ActivateDevice
import com.os.system.featureInstallation.domain.util.ActivateServiceApiResponse

interface ActivateServiceNetworkApi {
    suspend fun activateService(activateDevice: ActivateDevice): ActivateServiceApiResponse
}