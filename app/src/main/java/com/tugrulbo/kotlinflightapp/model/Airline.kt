package com.tugrulbo.kotlinflightapp.model


import com.google.gson.annotations.SerializedName


data class Airline(
    @SerializedName("iata")
    var iata: String,
    @SerializedName("icao")
    var icao: String,
    @SerializedName("name")
    var name: String
)