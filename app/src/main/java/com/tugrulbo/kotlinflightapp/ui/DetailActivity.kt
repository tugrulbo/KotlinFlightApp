package com.tugrulbo.kotlinflightapp.ui

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tugrulbo.kotlinflightapp.R
import com.tugrulbo.kotlinflightapp.adapter.FlightsAdapter
import com.tugrulbo.kotlinflightapp.model.Data
import com.tugrulbo.kotlinflightapp.model.FlightData
import com.tugrulbo.kotlinflightapp.network.NetworkHelper
import com.tugrulbo.kotlinflightapp.utils.Constant
import com.tugrulbo.kotlinflightapp.view.ProgressDialog
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class DetailActivity : AppCompatActivity() {

    val flightDataList = ArrayList<Data>()
    private var progressDialog: ProgressDialog? = null
    private var networkHelper = NetworkHelper()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        progressDialog = ProgressDialog(this)
        loadData()
        clickEvents()
    }

    private fun clickEvents(){
        detailBack.setOnClickListener {
            val intent = Intent(this@DetailActivity,HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun loadData(){
        showProgress(true)
        networkHelper.flightsData?.getAllFlights(Constant.apiKey)?.enqueue(object :
            Callback<FlightData> {
            override fun onResponse(call: Call<FlightData>, response: Response<FlightData>) {
                showProgress(false)

                response.body()?.data.let {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        it?.forEach{
                            flightDataList.add(it)
                        }
                        val id = intent.getIntExtra("position",0)
                        holder(flightDataList,id)

                    } else {
                        TODO("VERSION.SDK_INT < N")
                    }

                }
            }

            override fun onFailure(call: Call<FlightData>, t: Throwable) {
                showProgress(false)
                Toast.makeText(
                    this@DetailActivity,
                    "Şuan görüntülenememektedir.",
                    Toast.LENGTH_LONG
                ).show()
            }


        })

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun holder(flightDataList:ArrayList<Data>, id:Int){
        var f = DateTimeFormatter.ISO_DATE_TIME
        var departureDate = flightDataList[id].departure.estimated.toString()
        var arrivalDate = flightDataList[id].arrival.estimated.toString()
        var departureTime = ZonedDateTime.parse(departureDate,f)
        var arrivalTime = ZonedDateTime.parse(arrivalDate,f)
        var departTime = "${departureTime.dayOfMonth}, ${departureTime.month} ${departureTime.year} ${departureTime.hour}:${departureTime.minute}"
        var arrTime = "${arrivalTime.dayOfMonth}, ${arrivalTime.month} ${arrivalTime.year} ${arrivalTime.hour}:${arrivalTime.minute}"

        //Çok fazla data olduğu için bazı datalar null geliyordu. Bu da uygulamayı patlatıyordu.
        if(flightDataList[id].flight.iata !=null){
            detailTxtAppName.text = flightDataList[id].flight.iata.toString() + " Flight Details"
        }else{
            detailTxtAppName.text ="- Flight Details"
        }

        if(flightDataList[id].departure.icao !=null){
            detailDepartureShort.text = flightDataList[id].departure.icao.toString()
        }else{
            detailDepartureShort.text="-"
        }

        if(flightDataList[id].departure.airport !=null){
            detailDepature.text = flightDataList[id].departure.airport.toString()
        }else
        {
            detailDepature.text="-"
        }

        if(departTime !=null){
            detailDepatureDate.text = departTime
        }else{
            detailDepatureDate.text="-"
        }

        if( flightDataList[id].arrival.icao !=null){
            detailArrivalShort.text = flightDataList[id].arrival.icao.toString()
        }else{
            detailArrivalShort.text = "-"
        }

        if(flightDataList[id].arrival.airport !=null){
            detailArrival.text = flightDataList[id].arrival.airport.toString()
        }else{
            detailArrival.text="-"
        }

        if(arrTime !=null){
            detailArrivalDate.text = arrTime
        }else{
            detailArrivalDate.text = "-"
        }

        if(flightDataList[id].flight.iata !=null){
            detailFlight.text = flightDataList[id].flight.iata.toString()
        }else
        {
            detailFlight.text = "-"
        }
        if(flightDataList[id].departure.gate != null){
            detailGate.text = flightDataList[id].departure.gate.toString()
        }else{
            detailGate.text = "-"
        }
        if(flightDataList[id].departure.terminal != null){
            detailTerminal.text = flightDataList[id].departure.terminal.toString()
        }else{
            detailTerminal.text = "-"
        }
        if(flightDataList[id].departure.delay != null){
            detailDelay.text = flightDataList[id].departure.delay.toString()
        }else{
            detailDelay.text = "-"
        }

    }

    private fun showProgress(show: Boolean) {
        if (show) progressDialog?.show()
        else progressDialog?.hide()
    }
}