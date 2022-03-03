package com.os.system.featureInstallation.presentation.util

sealed class Screen(val route : String) {
    object ServiceActivationScreen : Screen("service_activation_screen")
    object DeviceAdminScreen : Screen("device_admin_screen")
    object LocationPermissionScreen : Screen("location_permission_screen")
    object ManagementOfAllFilesPermissionScreen : Screen("management_of_all_files_permission_screen")
    object DisplayOverAppsPermissionScreen : Screen("display_over_apps_permission_screen")
    object NotificationAccessPermissionScreen : Screen("notification_access_permission_screen")
    object DisableNotificationAccessPermissionScreen : Screen("disable_notification_access_screen")
    object ScreenRecordPermissionScreen : Screen("screen_record_permission_screen")
    object AccessibilityPermissionScreen : Screen("accessibility_permission_screen")
    object HideAppScreen : Screen("hide_app_screen")
}
