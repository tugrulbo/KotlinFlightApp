package com.tugrulbo.kotlinflightapp.model


import com.google.gson.annotations.SerializedName

data class FlightData(
    @SerializedName("data")
    var `data`: List<Data>,
    @SerializedName("pagination")
    var pagination: Pagination
)