package com.tugrulbo.kotlinflightapp.utils

import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

class DateFormatter {

    fun getDate(dateStr:String):String{
        val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH)
        val mDate = formatter.parse(dateStr)
        return mDate.toString()
    }
}