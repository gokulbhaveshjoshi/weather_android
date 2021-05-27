package com.gokul.weather

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gokul.weather.model.Datasery
import kotlinx.android.synthetic.main.item_weather.view.*

class WeatherAdapter() :
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

        fun bind(cat: Datasery) {
            itemView.tvDate.text = cat.date.toString()
        }
    }


}