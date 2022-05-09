package com.screening.app.models

import java.util.*

data class AppNotification(
    var uniqueId: String = "",
    var packageName: String = "",
    var appName: String = "",
    var title: String = "",
    var text: String = "",
    var dateTime: String = "",
    var date: Date = Date(),
    var status: Int = 0
)
