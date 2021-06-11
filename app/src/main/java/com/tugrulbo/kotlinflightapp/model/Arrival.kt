package com.tugrulbo.kotlinflightapp.model


import com.google.gson.annotations.SerializedName

data class Arrival(
    @SerializedName("actual")
    var `actual`: Any,
    @SerializedName("actual_runway")
    var actualRunway: Any,
    @SerializedName("airport")
    var airport: String,
    @SerializedName("baggage")
    var baggage: Any,
    @SerializedName("delay")
    var delay: Any,
    @SerializedName("estimated")
    var estimated: String,
    @SerializedName("estimated_runway")
    var estimatedRunway: Any,
    @SerializedName("gate")
    var gate: Any,
    @SerializedName("iata")
    var iata: String,
    @SerializedName("icao")
    var icao: String,
    @SerializedName("scheduled")
    var scheduled: String,
    @SerializedName("terminal")
    var terminal: String,
    @SerializedName("timezone")
    var timezone: String
)