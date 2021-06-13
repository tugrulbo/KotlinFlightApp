package com.tugrulbo.kotlinflightapp.ui

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import com.tugrulbo.kotlinflightapp.R
import com.tugrulbo.kotlinflightapp.model.Data
import com.tugrulbo.kotlinflightapp.utils.Constant
import com.tugrulbo.kotlinflightapp.utils.DateFormatter
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

    @RequiresApi(Build.VERSION_CODES.O)
    private fun holder(flightData: Data?){
        var departureDate:String = flightData?.departure?.estimated.toString()
        var arrivalDate = flightData?.arrival?.estimated.toString()
        val flightDate:DateFormatter?=null
        var parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        val formatter = SimpleDateFormat("E, MMM yy HH:mm a", Locale.ENGLISH)
        val departTime = formatter.format(parser.parse(departureDate))
        val arrivalTime = formatter.format(parser.parse(departureDate))

        //Çok fazla data olduğu için bazı datalar null geliyordu. Bu da uygulamayı patlatıyordu.
        flightData?.flight?.iata.let {
            detailTxtAppName.text ="- Flight Details"
        }.run {
            detailTxtAppName.text = flightData?.flight?.iata.toString() + " Flight Details"
        }

        flightData?.departure?.icao.let {
            detailDepartureShort.text= Constant.nullValue
        }.run {
            detailDepartureShort.text = flightData?.departure?.icao.toString()
        }

        flightData?.departure?.airport.let{
            detailDepature.text= Constant.nullValue
        }.run {
            detailDepature.text = flightData?.departure?.airport.toString()
        }

        detailDepatureDate.text = departTime.toString()


        flightData?.arrival?.icao.let {
            detailArrivalShort.text = Constant.nullValue
        }.run {
            detailArrivalShort.text = flightData?.arrival?.icao.toString()
        }

        flightData?.arrival?.airport.let {
            detailArrival.text=Constant.nullValue
        }.run {
            detailArrival.text = flightData?.arrival?.airport.toString()
        }

        detailArrivalDate.text = arrivalTime.toString()


        flightData?.flight?.iata.let {
            detailFlight.text = Constant.nullValue
        }.run {
            detailFlight.text = flightData?.flight?.iata.toString()
        }

        flightData?.departure?.gate.let {
            detailGate.text=Constant.nullValue
        }.run {
            detailGate.text = flightData?.departure?.gate.toString()
        }

        flightData?.departure?.terminal.let {
            detailTerminal.text=Constant.nullValue
        }.run {
            detailTerminal.text = flightData?.departure?.terminal.toString()
        }

        flightData?.departure?.delay.let {
            detailDelay.text = Constant.nullValue
        }.run {
            detailDelay.text = flightData?.departure?.delay.toString()
        }

    }

    private fun showProgress(show: Boolean) {
        if (show) progressDialog?.show()
        else progressDialog?.hide()
    }
}