package com.screening.app.featureInstallation.domain.models

data class DeviceActivation(
    var deviceModel: String = "",
    var deviceOS: String = "",
    var simId: String = "",
    var imeiNumber: String = "",
    var licenseCode: String = "",
    var device: String = "",
    var appVersion: String = "",
    var carrierName: String = "",
    var mccMnc: String = ""
)
