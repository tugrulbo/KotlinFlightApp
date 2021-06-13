package com.tugrulbo.kotlinflightapp.ui

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tugrulbo.kotlinflightapp.R
import com.tugrulbo.kotlinflightapp.adapter.FlightsAdapter
import com.tugrulbo.kotlinflightapp.model.Data
import com.tugrulbo.kotlinflightapp.model.FlightData
import com.tugrulbo.kotlinflightapp.network.NetworkHelper
import com.tugrulbo.kotlinflightapp.utils.Constant
import com.tugrulbo.kotlinflightapp.view.ProgressDialog
import kotlinx.android.synthetic.main.activity_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class HomeActivity : AppCompatActivity(), FlightsAdapter.OnListClickListener {

    //değişkenler
    val flightDataList = ArrayList<Data>()
    private var progressDialog: ProgressDialog? = null
    private var networkHelper = NetworkHelper()
    private var flightDataAdapter:FlightsAdapter? = null
    val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this@HomeActivity)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        progressDialog = ProgressDialog(this)
        getAllFlightData()
        onClickEvents()

    }

    private fun onClickEvents(){
        homeBtnLogout.setOnClickListener {
            clearEmailAndPass()
            val intent = Intent(this@HomeActivity,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun getAllFlightData(){
        showProgress(true)
        networkHelper.flightsData?.getAllFlights(Constant.apiKey)?.enqueue(object :Callback<FlightData>{
            override fun onResponse(call: Call<FlightData>, response: Response<FlightData>) {
                showProgress(false)

                response.body()?.data.let {
                       if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                           it?.forEach{
                               flightDataList.add(it)
                           }
                       } else {
                           TODO("VERSION.SDK_INT < N")
                       }
                }

                //sorulacak kısım
                flightDataAdapter = FlightsAdapter(flightDataList,this@HomeActivity)
                homeRecyclerView?.layoutManager=layoutManager
                homeRecyclerView.adapter=flightDataAdapter
                flightDataAdapter!!.notifyDataSetChanged()

            }
            override fun onFailure(call: Call<FlightData>, t: Throwable) {
                showProgress(false)

                //Error mesajı artık "Constant" dosyası altından çekiliyor
                Toast.makeText(
                    this@HomeActivity, Constant.homeNotLoading,
                    Toast.LENGTH_LONG
                ).show()
            }
        })
    }

    private fun showProgress(show: Boolean) {
        if (show) progressDialog?.show()
        else progressDialog?.hide()
    }


    override fun onClick(flightsList: ArrayList<Data>, position: Int) {
       var intent = Intent(this,DetailActivity::class.java)
        intent.putExtra("flightData",flightDataList[position])
        Log.e(TAG, "onClick: ${flightDataList[position]}", )
        startActivity(intent)
    }

    private fun clearEmailAndPass(){
        var sharedPref = getSharedPreferences(Constant.sharedPrefName, Context.MODE_PRIVATE)
        var editor = sharedPref.edit()
        editor.remove(Constant.sharedPrefEmail).commit()
        editor.remove(Constant.sharedPrefPass).commit()
        editor.remove(Constant.sharedPrefBoolean).commit()
    }


}