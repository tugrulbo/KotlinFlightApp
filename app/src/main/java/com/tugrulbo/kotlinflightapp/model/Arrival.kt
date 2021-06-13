package com.tugrulbo.kotlinflightapp.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Arrival(
    @SerializedName("airport")
    var airport: String?,
    @SerializedName("delay")
    var delay: Float?,
    @SerializedName("estimated")
    var estimated: String?,
    @SerializedName("gate")
    var gate: String?,
    @SerializedName("iata")
    var iata: String?,
    @SerializedName("icao")
    var icao: String?,
    @SerializedName("scheduled")
    var scheduled: String?,
    @SerializedName("terminal")
    var terminal: String?
):Parcelable