package com.tugrulbo.kotlinflightapp.utils

import java.text.SimpleDateFormat
import java.util.*

class DateFormatter {

    fun getDate(dateStr:String): String? {
        var parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        val formatter = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.ENGLISH)
        val mDate = formatter.format(parser.parse(dateStr))
        return mDate
    }
}