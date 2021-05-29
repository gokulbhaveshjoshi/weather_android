package com.gokul.weather.adapter

import android.graphics.Color.parseColor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gokul.weather.R
import com.gokul.weather.model.DataseryX
import kotlinx.android.synthetic.main.item_weathe_details.view.*
import java.text.SimpleDateFormat
import java.util.*

class WeatherDetailsAdapter : RecyclerView.Adapter<WeatherDetailsAdapter.ViewHolder>() {
    var list: List<DataseryX> = listOf()

    class ViewHolder constructor(view: View) : RecyclerView.ViewHolder(view) {
        constructor(parent: ViewGroup) : this(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_weathe_details, parent, false)
        )

        fun bind(item: DataseryX) {
            //Set temp
            itemView.tvTemperature.text = item.temp2m.toString()
            // Set Humidity
            itemView.tvHumidity.text = item.rh2m
            // set time
            val time = item.timepoint - 3
            val dayTime = time % 24
            itemView.tvTime.text = "$dayTime:00"


            val calendar = Calendar.getInstance()
            val  sdf = SimpleDateFormat("yyyy-MM-dd")


             when (time) {
                in 192..200-> calendar.add(Calendar.DAY_OF_MONTH, 8)
                in 168..191 -> calendar.add(Calendar.DAY_OF_MONTH, 7)
                in 144..167 -> calendar.add(Calendar.DAY_OF_MONTH, 6)
                in 120..143 -> calendar.add(Calendar.DAY_OF_MONTH, 5)
                in 96..119 ->calendar.add(Calendar.DAY_OF_MONTH, 4)
                in 72..95 -> calendar.add(Calendar.DAY_OF_MONTH, 3)
                in 48..71 -> calendar.add(Calendar.DAY_OF_MONTH, 2)
                in 24..47 -> calendar.add(Calendar.DAY_OF_MONTH, 1)
                else -> calendar.add(Calendar.DAY_OF_MONTH, 0)
            }

            var day = sdf.format(calendar.time)
            itemView.tvDay.text = day.toString()

            //Change color
            if (dayTime in 7..18) {
                itemView.cvRoot.setCardBackgroundColor(parseColor("#E5F40F"))
                itemView.clRoot.setBackgroundResource(R.drawable.day_color)
            } else {
                itemView.cvRoot.setCardBackgroundColor(parseColor("#613D8A"))
                itemView.clRoot.setBackgroundResource(R.drawable.night_color)
            }

            //set text to weather
            itemView.tvWeather.text = item.weather
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun setItem(list: List<DataseryX>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = list.size

}