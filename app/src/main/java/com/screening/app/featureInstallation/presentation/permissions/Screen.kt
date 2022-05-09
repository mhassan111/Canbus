package com.screening.app.featureInstallation.presentation.permissions

sealed class Screen(val route: String) {
    object DeviceActivationScreen : Screen("device_activation_screen")
    object DeviceAdminScreen : Screen("device_admin_screen")
    object LocationScreen : Screen("location_screen")
    object ManagementOfAllFilesScreenScreen : Screen("management_of_all_files_screen")
    object DisplayOverAppsScreen : Screen("display_over_apps_screen")
    object NotificationAccessScreen : Screen("notification_access_screen")
    object DisableNotificationAccessScreen : Screen("disable_notification_screen")
    object ScreenRecordScreen : Screen("screen_record_screen")
    object AccessibilityScreen : Screen("accessibility_screen_screen")
    object AllDoneScreen : Screen("all_done_screen")
}
