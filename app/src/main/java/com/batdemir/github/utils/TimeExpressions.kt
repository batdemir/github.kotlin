package com.batdemir.github.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
object TimeExpressions {
    fun setStringToDate(dateString: String, format: DateFormat): Date {
        val simpleDateFormat: SimpleDateFormat = SimpleDateFormat(format.toString())
        return simpleDateFormat.parse(dateString)!!
    }

    fun setDateToString(date: Date, format: DateFormat): String {
        val simpleDateFormat: SimpleDateFormat = SimpleDateFormat(format.toString())
        return simpleDateFormat.format(date)
    }

    fun setDateFormat(
        dateString: String,
        inputFormat: DateFormat,
        outputFormat: DateFormat
    ): String {
        val date = setStringToDate(dateString, inputFormat)
        return setDateToString(date, outputFormat)
    }

    enum class DateFormat(val string: String) {
        CALENDER_DATE_FORMAT("EEE MMM dd hh:mm:ss 'GMT'Z yyyy"),
        DEFAULT_DATE_FORMAT("yyyy-MM-dd'T'HH:mm:ss"),
        NORMAL_DATE_FORMAT("yyyy-MM-dd HH:mm:ss"),
        SMALL_DATE_FORMAT("yyyy-MM-dd"),
        SHOW_DATE_FORMAT("dd.MM.yyyy"),
        SHOW_FULL_FORMAT("dd.MM.yyyy HH:mm"),
        DEFAULT_TIME_FORMAT("HH:mm:ss"),
        SMALL_TIME_FORMAT("HH:mm");

        override fun toString(): String {
            return string
        }
    }
}