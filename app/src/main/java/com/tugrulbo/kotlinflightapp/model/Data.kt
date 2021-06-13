package com.tugrulbo.kotlinflightapp.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Data(
    @SerializedName("airline")
    var airline: Airline?,
    @SerializedName("arrival")
    var arrival: Arrival?,
    @SerializedName("departure")
    var departure: Departure?,
    @SerializedName("flight")
    var flight: Flight?,
    @SerializedName("flight_date")
    var flightDate: String?,
    @SerializedName("flight_status")
    var flightStatus: String?,
):Parcelable