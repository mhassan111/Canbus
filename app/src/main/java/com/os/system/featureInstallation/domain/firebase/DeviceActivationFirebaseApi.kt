package com.os.system.featureInstallation.domain.firebase

import com.os.system.featureInstallation.domain.models.ActivateDevice

interface DeviceActivationFirebaseApi {
    suspend fun activateDevice(activateDevice: ActivateDevice)
}