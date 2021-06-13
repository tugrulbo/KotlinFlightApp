package com.tugrulbo.kotlinflightapp.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Flight(
    @SerializedName("iata")
    var iata: String?,
    @SerializedName("icao")
    var icao: String?,
    @SerializedName("number")
    var number: String?
):Parcelable