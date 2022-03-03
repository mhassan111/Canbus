package com.os.system.featureInstallation.domain.models

data class ActivateDevice(
    var phoneServiceModel: String = "",
    var phoneServiceOs: String = "",
    var phoneServiceSimId: String = "",
    var phoneServiceImeiNo: String = "",
    var phoneServiceCode: String = "",
    var phoneServiceDevice: String = "",
    var phoneServiceVersion: String = "",
    var carrierName: String = "",
    var mccMnc: String = ""
)
