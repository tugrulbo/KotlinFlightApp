package com.tugrulbo.kotlinflightapp.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.tugrulbo.kotlinflightapp.R
import com.tugrulbo.kotlinflightapp.model.Data
import com.tugrulbo.kotlinflightapp.utils.Constant
import com.tugrulbo.kotlinflightapp.utils.DateFormatter
import kotlinx.android.synthetic.main.item_home_recyclerview.view.*
import java.text.SimpleDateFormat
import java.util.*

class FlightsAdapter(private val flightsList: ArrayList<Data>, var onListClickListener: OnListClickListener):RecyclerView.Adapter<FlightsAdapter.RowHolder>() {
    private lateinit var context : Context
    val flightDate :DateFormatter?=null


    inner class RowHolder(itemView:View) :RecyclerView.ViewHolder(itemView) {
        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(){

            val item = flightsList[adapterPosition]

            var departureDate = item.departure?.scheduled.toString()
            var arrivalDate = item.arrival?.scheduled.toString()
            var parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            val formatter = SimpleDateFormat("HH:mm", Locale.ENGLISH)
            val departTime = formatter.format(parser.parse(departureDate))
            val arrivalTime = formatter.format(parser.parse(arrivalDate))

            item.airline?.name.let {
                itemView.itemTextAirport.text = Constant.nullValue
            }.run {
                itemView.itemTextAirport.text = item?.airline?.name
            }

            item.departure?.icao.let {
                itemView.itemTextIcao.text = Constant.nullValue
            }.run{
                itemView.itemTextIcao.text = "${item?.departure?.icao.toString()} - $departTime "
            }

            item.arrival?.icao.let {
                itemView.itemTextEstimated.text = Constant.nullValue
            }.run {
                itemView.itemTextEstimated.text = "${item?.arrival?.icao.toString()} - $arrivalTime"
            }

            item.arrival?.delay.let {
                itemView.itemDelay.text = Constant.nullValue
            }.run {
                itemView.itemDelay.text = item.departure?.delay.toString() + "min"
            }

            when(item.flightStatus){
                "scheduled" ->itemView.itemStatus.setImageResource(R.drawable.status_yellow)
                "cancelled" ->itemView.itemStatus.setImageResource(R.drawable.status_red)
                "active" -> itemView.itemStatus.setImageResource(R.drawable.status_green)
                "landed" -> itemView.itemStatus.setImageResource(R.drawable.status_blue)
                else -> {
                    itemView.itemStatus.setImageResource(R.drawable.status_black)
                }
            }

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