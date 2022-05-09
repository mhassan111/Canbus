package com.screening.app.featureInstallation.domain.models

import com.screening.app.featureInstallation.WholeSpyScreen

data class PermissionCounter(
    var permission: WholeSpyScreen,
    var permissionNumber: Int,
    var totalPermissions: Int
)
