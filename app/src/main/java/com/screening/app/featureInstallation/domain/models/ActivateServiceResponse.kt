package com.screening.app.featureInstallation.domain.models

data class ActivateServiceResponse(
    val statusCode: String,
    val userId: String,
    val phoneServiceId: String
)
