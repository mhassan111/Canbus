package com.os.system.featureInstallation.domain.models

data class ActivateServiceResponse(
    val statusCode: String,
    val userId: String,
    val phoneServiceId: String
)
