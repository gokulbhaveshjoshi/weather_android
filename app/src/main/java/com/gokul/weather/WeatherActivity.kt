package com.gokul.weather

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gokul.weather.viewModel.WeatherViewModel
import kotlinx.android.synthetic.main.activity_weather.*

class WeatherActivity : AppCompatActivity() {
    private  var viewAdapter = WeatherAdapter()


    var weatherViewModel: WeatherViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)


        val factory = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        weatherViewModel = ViewModelProvider(this, factory).get(WeatherViewModel::class.java)

        setData()

        weatherViewModel!!.observeWeatherInfo().observe(this, Observer {

            if (it != null) {
                vpWeather.adapter = viewAdapter
                viewAdapter.setItem(it.dataseries)
//                try {
////                    locationRecycler.visibility = View.GONE
////                    weatherRecycler.visibility = View.VISIBLE
//                } catch (e: Exception) {
//                    e.printStackTrace()
//                }
            }
            else{
                Toast.makeText(this,"No Data is Set",Toast.LENGTH_LONG).show()
                clError.visibility = View.VISIBLE
                vpWeather.visibility = View.GONE
            }
        })



    }

    private fun setData() {
        weatherViewModel!!.requestWeatherInfo("24.5859","74.8566")
    }


}