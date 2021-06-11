package com.tugrulbo.kotlinflightapp.model


import com.google.gson.annotations.SerializedName

data class Flight(
    @SerializedName("codeshared")
    var codeshared: Any,
    @SerializedName("iata")
    var iata: String,
    @SerializedName("icao")
    var icao: String,
    @SerializedName("number")
    var number: String
)