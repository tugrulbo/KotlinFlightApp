package com.tugrulbo.kotlinflightapp.ui

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.tugrulbo.kotlinflightapp.R
import com.tugrulbo.kotlinflightapp.model.Data
import com.tugrulbo.kotlinflightapp.utils.Constant
import com.tugrulbo.kotlinflightapp.view.ProgressDialog
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_home.*
import java.text.SimpleDateFormat
import java.util.*

class DetailActivity : AppCompatActivity() {

    private var progressDialog: ProgressDialog? = null

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
        val flightData:Data? = intent.getParcelableExtra("flightData")
        Log.e(TAG, "loadData: ${flightData}", )
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            holder(flightData)
            showProgress(false)
        }
    }

    private fun holder(flightData: Data?){
        var departureDate = flightData?.departure?.estimated.toString()
        var arrivalDate = flightData?.arrival?.estimated.toString()
        var parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        val formatter = SimpleDateFormat("E, MMM yy HH:mm a", Locale.getDefault())
        val departTime = formatter.format(parser.parse(departureDate))
        val arrivalTime = formatter.format(parser.parse(arrivalDate))


        //Çok fazla data olduğu için bazı datalar null geliyordu. Bu da uygulamayı patlatıyordu.
        flightData?.flight?.iata.let {
            detailTxtAppName.text = flightData?.flight?.iata.toString() + " Flight Details"
        }?:run {
            detailTxtAppName.text ="- Flight Details"
        }

        flightData?.departure?.icao?.let {
            detailDepartureShort.text = flightData?.departure?.icao.toString()
        }?:run {
            detailDepartureShort.text= Constant.nullValue
        }

        flightData?.departure?.airport?.let{
            detailDepature.text = flightData?.departure?.airport.toString()
        }?:run {
            detailDepature.text= Constant.nullValue
        }

        detailDepatureDate.text = departTime

        flightData?.arrival?.icao?.let {
            detailArrivalShort.text = flightData?.arrival?.icao.toString()
        }?:run {
            detailArrivalShort.text = Constant.nullValue
        }

        flightData?.arrival?.airport?.let {
            detailArrival.text = flightData?.arrival?.airport.toString()
        }?:run {
            detailArrival.text=Constant.nullValue
        }

        detailArrivalDate.text = arrivalTime


        flightData?.flight?.iata?.let {
            detailFlight.text = flightData?.flight?.iata.toString()
        }?:run {
            detailFlight.text = Constant.nullValue
        }

        flightData?.departure?.gate?.let {
            detailGate.text = flightData?.departure?.gate.toString()
        }?:run {
            detailGate.text=Constant.nullValue
        }

        flightData?.departure?.terminal?.let {
            detailTerminal.text = flightData?.departure?.terminal.toString()
        }?:run {
            detailTerminal.text=Constant.nullValue
        }

        flightData?.departure?.delay?.let {
            detailDelay.text = flightData?.departure?.delay.toString()
        }?:run {
            detailDelay.text = Constant.nullValue
        }

    }

    private fun showProgress(show: Boolean) {
        if (show) progressDialog?.show()
        else progressDialog?.hide()
    }
}