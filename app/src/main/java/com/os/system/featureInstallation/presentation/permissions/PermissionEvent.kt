package com.os.system.featureInstallation.presentation.permissions

sealed class PermissionEvent
object NoEvent : PermissionEvent()
object DeviceAdminPermissionEvent : PermissionEvent()
object LocationPermissionEvent : PermissionEvent()
object ManagementOfAllFilesPermissionEVent : PermissionEvent()
object DisplayOverAppsPermissionEvent : PermissionEvent()
object NotificationAccessPermissionEvent : PermissionEvent()
object DisableNotificationAccessEvent : PermissionEvent()
object ScreenRecordPermissionEvent : PermissionEvent()
object AccessibilityPermissionEvent : PermissionEvent()
object HideAppEvent : PermissionEvent()
