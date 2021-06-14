package com.tugrulbo.kotlinflightapp.utils

import java.text.SimpleDateFormat
import java.util.*

class FlightDateFormatter {

    fun getDate(formatType:String, formatDate:String): String{
        var parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        val formatter = SimpleDateFormat(formatType, Locale.getDefault())
        return formatter.format(parser.parse(formatDate)).toString()
    }
}

//E, MMM yy HH:mm a