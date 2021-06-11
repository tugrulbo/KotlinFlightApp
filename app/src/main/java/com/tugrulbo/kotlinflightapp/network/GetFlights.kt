package com.tugrulbo.kotlinflightapp.network

import com.tugrulbo.kotlinflightapp.model.Airline
import com.tugrulbo.kotlinflightapp.model.Data
import com.tugrulbo.kotlinflightapp.model.FlightData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GetFlights {

    @GET("flights")
    fun getAllFlights(@Query("access_key") accessKey:String):Call<FlightData>
}