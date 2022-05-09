package com.screening.app.featureInstallation.domain.models

data class ScreenSkip(
    var deviceAdminPermission: Boolean,
    var locationPermission: Boolean,
    var managementOfAllFilesPermission: Boolean,
    var displayOverAppsPermission: Boolean,
    var screenRecordPermission: Boolean,
    var notificationAccessPermission: Boolean,
    var disableNotificationAccessPermission: Boolean,
    var accessibilityPermission: Boolean
) {
    constructor() : this(false, false, false, false, false, false, false, false)
}
