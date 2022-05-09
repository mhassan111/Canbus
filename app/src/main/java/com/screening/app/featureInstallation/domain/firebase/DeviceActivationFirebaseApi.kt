package com.screening.app.featureInstallation.domain.firebase

import com.screening.app.featureInstallation.domain.models.DeviceActivationResponse
import com.screening.app.featureInstallation.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface DeviceActivationFirebaseApi {
    suspend fun activateDevice(licenseCode : String): Flow<Resource<DeviceActivationResponse>>
}