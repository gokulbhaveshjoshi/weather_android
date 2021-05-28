package com.gokul.weather.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gokul.weather.R
import com.gokul.weather.activity.WeatherDetailsActivity
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



            //Set image
            when (item.weather) {
                "clear" -> {
                    itemView.ivWeatherImage.setImageResource(R.drawable.ic_clear)
                    itemView.nsvHead.setBackgroundResource(R.drawable.clear)
                    itemView.tvWeatherName.text = "CLEAR"
                }
                "cloudy" -> {
                    itemView.ivWeatherImage.setImageResource(R.drawable.ic_cloud)
                    itemView.nsvHead.setBackgroundResource(R.drawable.cloudy)
                    itemView.tvWeatherName.text = "Cloudy"
                }
                "mcloudy" -> {
                    itemView.ivWeatherImage.setImageResource(R.drawable.ic_cloudly)
                    itemView.nsvHead.setBackgroundResource(R.drawable.cloudyy)
                    itemView.tvWeatherName.text = "Heavy Cloud"
                }
                "lightrain" -> {
                    itemView.ivWeatherImage.setImageResource(R.drawable.ic_rain)
                    itemView.nsvHead.setBackgroundResource(R.drawable.light_rain)
                    itemView.tvWeatherName.text = "Rain"
                }
                "pcloudy" -> {
                    itemView.ivWeatherImage.setImageResource(R.drawable.ic_cloudly)
                    itemView.nsvHead.setBackgroundResource(R.drawable.cloudyy)
                    itemView.tvWeatherName.text = "Clould"
                }

                "rain" -> {
                    itemView.ivWeatherImage.setImageResource(R.drawable.ic_rain)
                    itemView.nsvHead.setBackgroundResource(R.drawable.light_rain)
                    itemView.tvWeatherName.text = "Rain"
                }
                "ishower" -> {
                    itemView.ivWeatherImage.setImageResource(R.drawable.ic_rain)
                    itemView.nsvHead.setBackgroundResource(R.drawable.heavy_rain)
                    itemView.tvWeatherName.text = "Shower"
                }
                "humid" -> {
                    itemView.ivWeatherImage.setImageResource(R.drawable.cloudyy)
                    itemView.nsvHead.setBackgroundResource(R.drawable.clear)
                    itemView.tvWeatherName.text = "Humid"
                }
                "oshower" -> {
                    itemView.ivWeatherImage.setImageResource(R.drawable.ic_rain)
                    itemView.nsvHead.setBackgroundResource(R.drawable.heavy_rain)
                    itemView.tvWeatherName.text = "shower"
                }
                "lightsnowday" -> {
                    itemView.ivWeatherImage.setImageResource(R.drawable.ic_snow)
                    itemView.nsvHead.setBackgroundResource(R.drawable.snow_fall)
                    itemView.tvWeatherName.text = "Snow"
                }
                "snow" -> {
                    itemView.ivWeatherImage.setImageResource(R.drawable.ic_snow)
                    itemView.nsvHead.setBackgroundResource(R.drawable.snow_fall)
                    itemView.tvWeatherName.text = "snow"
                }
                "rainsnow" -> {
                    itemView.ivWeatherImage.setImageResource(R.drawable.ic_snow)
                    itemView.nsvHead.setBackgroundResource(R.drawable.snow_fall)
                    itemView.tvWeatherName.text = "Rain Snow"
                }
                "ts" -> {
                    itemView.ivWeatherImage.setImageResource(R.drawable.ic_thunder)
                    itemView.nsvHead.setBackgroundResource(R.drawable.thunder)
                    itemView.tvWeatherName.text = "Thunder"
                }
                "tsrain" -> {
                    itemView.ivWeatherImage.setImageResource(R.drawable.ic_thunder)
                    itemView.nsvHead.setBackgroundResource(R.drawable.thunderrain)
                    itemView.tvWeatherName.text = "Thunder Rain"

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