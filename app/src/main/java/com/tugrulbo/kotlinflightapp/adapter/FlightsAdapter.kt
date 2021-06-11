package com.tugrulbo.kotlinflightapp.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.tugrulbo.kotlinflightapp.R
import com.tugrulbo.kotlinflightapp.model.Data
import com.tugrulbo.kotlinflightapp.model.FlightData
import com.tugrulbo.kotlinflightapp.ui.HomeActivity
import kotlinx.android.synthetic.main.item_home_recyclerview.view.*
import java.text.DateFormat
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class FlightsAdapter(private val flightsList: ArrayList<Data>, var onListClickListener: OnListClickListener):RecyclerView.Adapter<FlightsAdapter.RowHolder>() {
    private lateinit var context : Context

    inner class RowHolder(itemView:View) :RecyclerView.ViewHolder(itemView) {
        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(){

            val item = flightsList[adapterPosition]
            var f = DateTimeFormatter.ISO_DATE_TIME
            var departureDate = item.departure.estimated.toString()
            var arrivalDate = item.arrival.estimated.toString()
            var departureTime = ZonedDateTime.parse(departureDate,f)
            var arrivalTime = ZonedDateTime.parse(arrivalDate,f)
            var departTime = "${departureTime.hour}:${departureTime.minute}"
            var arrTime = "${arrivalTime.hour}:${arrivalTime.minute}"
            itemView.itemTextAirport.text = item.airline.name.toString()
            itemView.itemTextIcao.text = item.departure.icao.toString()+  " - " +departTime
            itemView.itemTextEstimated.text = item.arrival.icao.toString()+  " - " + arrTime
            if(item.departure.delay == null){
                itemView.itemDelay.text = "Bilinmiyor"
            }else
            {
                itemView.itemDelay.text = item.departure.delay.toString() + "min"
            }


            if(item.flightStatus == "scheduled"){
                itemView.itemStatus.setImageResource(R.drawable.status_yellow)
            }else if (item.flightStatus == "cancelled"){
                itemView.itemStatus.setImageResource(R.drawable.status_red)
            }else
            {
                itemView.itemStatus.setImageResource(R.drawable.status_green)
            }
            //Alt kısımda kurduğum yapıda bir hata var ama anlamadım.
            /*when (item.flightStatus) {
                "scheduled" -> itemView.itemStatus.setImageResource(R.drawable.status_yellow)
                "cancelled" -> itemView.itemStatus.setImageResource(R.drawable.status_red)
                "active" ->itemView.itemStatus.setImageResource(R.drawable.status_green)
                else -> {
                    itemView.itemStatus.setImageResource(R.drawable.status_green)
                }
            }*/

            itemView.setOnClickListener {
                onListClickListener.onClick(flightsList,adapterPosition)
            }
        }

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        context = parent.context
        val inflatedView = LayoutInflater.from(context).inflate(R.layout.item_home_recyclerview,parent,false)
        return RowHolder(inflatedView)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
        return flightsList.size
    }

    interface OnListClickListener {
        fun onClick(flightsList: ArrayList<Data>,position: Int)
    }
}