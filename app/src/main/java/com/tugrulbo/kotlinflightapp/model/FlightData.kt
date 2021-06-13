package com.tugrulbo.kotlinflightapp.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class FlightData(
    @SerializedName("data")
    var data: List<Data>?,
    @SerializedName("pagination")
    var pagination: Pagination?
):Parcelable