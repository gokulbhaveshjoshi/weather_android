package com.gokul.weather

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gokul.weather.model.Datasery
import kotlinx.android.synthetic.main.item_weather.view.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class WeatherAdapter :
    RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    var list: List<Datasery> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        return WeatherViewHolder(parent)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    fun setItem(list: List<Datasery>) {
        this.list = list
        notifyDataSetChanged()
    }

    class WeatherViewHolder constructor(view: View) : RecyclerView.ViewHolder(view) {
        constructor(parent: ViewGroup) :
                this(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_weather, parent, false)
                )

        fun bind(item: Datasery) {
            //Date
            val string = item.date.toString()
            val formatter = DateTimeFormatter.ofPattern("yyyyMMdd", Locale.ENGLISH)
            val date = LocalDate.parse(string, formatter)
            itemView.tvDate.text = date.toString()
            //Tempertaure
            itemView.tvMaxTemperature.text = item.temp2m.max.toString() + "°C"
            itemView.tvMinTemperature.text = item.temp2m.min.toString() + "°C"
            //Wind Speed
            itemView.tvWindSpeed.text = item.wind10m_max.toString()

            itemView.tvWeatherName.text = item.weather.toString()

            //Set image
            when (item.weather) {
                "clear" -> {
                    itemView.ivWeatherImage.setImageResource(R.drawable.ic_clear)
                }
                "cloudy" -> {
                    itemView.ivWeatherImage.setImageResource(R.drawable.ic_cloud)
                }
                "mcloudy" -> {
                    itemView.ivWeatherImage.setImageResource(R.drawable.ic_cloudly)
                }
                "lightrain" -> {
                    itemView.ivWeatherImage.setImageResource(R.drawable.ic_rain)
                }
                "pcloudy" -> {
                    itemView.ivWeatherImage.setImageResource(R.drawable.ic_cloudly)
                }

                "rain" -> {
                    itemView.ivWeatherImage.setImageResource(R.drawable.ic_rain)
                }
                "ishower" -> {
                    itemView.ivWeatherImage.setImageResource(R.drawable.ic_rain)
                }
                "humid" -> {
                    itemView.ivWeatherImage.setImageResource(R.drawable.ic_cloud)
                }
                "oshower" -> {
                    itemView.ivWeatherImage.setImageResource(R.drawable.ic_rain)
                }
                "lightsnowday" -> {
                    itemView.ivWeatherImage.setImageResource(R.drawable.ic_snow)
                }
                "snow" -> {
                    itemView.ivWeatherImage.setImageResource(R.drawable.ic_snow)
                }
                "rainsnow" -> {
                    itemView.ivWeatherImage.setImageResource(R.drawable.ic_snow)
                }
                "ts" -> {
                    itemView.ivWeatherImage.setImageResource(R.drawable.ic_thunder)
                }
                "tsrain" -> {
                    itemView.ivWeatherImage.setImageResource(R.drawable.ic_thunder)

                }
            }

            //Click
            itemView.cvRoot.setOnClickListener {
                val intent = Intent(it.context, WeatherDetailsActivity::class.java)
                it.context.startActivity(intent)
            }
        }
    }


}