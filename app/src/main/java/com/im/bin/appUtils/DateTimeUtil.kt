@file:Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")

package com.im.bin.appUtils

import android.app.DatePickerDialog
import android.content.Context
import com.im.bin.R
import com.im.bin.interfaces.OnDateSelectedListener
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


object DateTimeUtil {

    @JvmStatic
    fun formatDate(date: String?): String {
        if (date == null) {
            return ""
        }
        var timestamp = java.lang.Long.valueOf(date)
        if (date.length < 13) {
            timestamp *= 1000
        }
        return SimpleDateFormat(Constants.DATE_TIME_FORMAT).format(Date(timestamp))
    }

    /**
     * Convert the TimeStamp to Date and Returns the formatted date String
     */
    fun formatTimeStampToDate(timestamp: Long, format: String): String {
        val dateFormat = SimpleDateFormat(format, Locale.ENGLISH)
        val dateTime = Date(timestamp)
        return dateFormat.format(dateTime)
    }

    /**
     * Returns today's date
     */
    fun getTodaysDate(format: String): String {
        val calender = Calendar.getInstance().time
        val dateFormat = SimpleDateFormat(format, Locale.ENGLISH)
        return dateFormat.format(calender)
    }

    /**
     * Adds No of days to a Date
     * returns a new date after adding days
     */
    fun addDaysToDate(date: String, days: Int, format: String): String {
        val dateFormat = SimpleDateFormat(format, Locale.ENGLISH)
        val calender = Calendar.getInstance()
        try {
            calender.time = dateFormat.parse(date)
        } catch (e: ParseException) {
        }
        calender.add(Calendar.DAY_OF_MONTH, days)
        return dateFormat.format(calender.time)
    }

    fun showDatePickerDialog(context: Context, listener: OnDateSelectedListener) {
        val calendar = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog(
            context,
            R.style.DatePickerDialogThem,
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                listener.onDateSelected("$dayOfMonth/$monthOfYear/$year")
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }


}