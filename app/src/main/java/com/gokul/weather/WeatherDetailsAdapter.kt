package com.gokul.weather

import android.graphics.Color.parseColor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gokul.weather.model.DataseryX
import kotlinx.android.synthetic.main.item_weathe_details.view.*

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
            val time = item.timepoint
            val dayTime = if (time < 24) time else time % 24
            itemView.tvTime.text = "$dayTime:00"

            //set day
            var day = when (time) {
                in 169..192 -> "Day 8"
                in 145..168 -> "Day 7"
                in 121..144 -> "Day 6"
                in 97..120 -> "Day 5"
                in 73..96 -> "Day 4"
                in 49..72 -> "Day 3"
                in 25..48 -> "Day 2"
                else -> "Day 1"
            }
            itemView.tvDay.text = day.toString()

            //Change color
            if (dayTime in 5..18) {
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