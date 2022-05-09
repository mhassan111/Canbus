package com.screening.app.utilities

import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


object DateTimeUtil {

    const val DATE_FORMAT_1 = "yyyy-MM-dd HH:mm:ss"
    const val DATE_FORMAT_2 = "EEE, d MMM yyyy HH:mm:ss"
    const val DATE_FORMAT_3 = "yyyy-MM-dd hh:mm"
    const val DATE_FORMAT_4 = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
    const val DATE_FORMAT_5 = "yyyy-MM-dd"

    fun formatDate(date: String): String {
        val dateFormat = SimpleDateFormat(DATE_FORMAT_1, Locale.ROOT)
        val dateTime = Date(date.toLong())
        return dateFormat.format(dateTime)
    }

    /** Get Date from timestamp **/
    fun getDate(timeStamp: Long): Date {
        val calendar = Calendar.getInstance(Locale.getDefault())
        calendar.timeInMillis = timeStamp
        return calendar.time
    }
}