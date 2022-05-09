package com.screening.app.featureInstallation.domain.useCases

import com.screening.app.featureInstallation.domain.firebase.DeviceActivationFirebaseApi
import com.screening.app.featureInstallation.domain.models.DeviceActivationResponse
import com.screening.app.featureInstallation.domain.util.Resource
import kotlinx.coroutines.flow.Flow

class DeviceActivationUseCase(private val deviceActivationFirebaseApi: DeviceActivationFirebaseApi) {
    suspend operator fun invoke(licenseCode : String): Flow<Resource<DeviceActivationResponse>> {
        return deviceActivationFirebaseApi.activateDevice(licenseCode)
    }
}