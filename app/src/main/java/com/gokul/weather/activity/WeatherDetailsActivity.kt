package com.gokul.weather.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.gokul.weather.GPS.GpsTracker
import com.gokul.weather.R
import com.gokul.weather.adapter.WeatherDetailsAdapter
import com.gokul.weather.viewModel.WeatherDetailsViewModel
import kotlinx.android.synthetic.main.activity_weather_details.*

class WeatherDetailsActivity : AppCompatActivity() {
    private var viewDetailsAdapter = WeatherDetailsAdapter()
    var weatherViewModel: WeatherDetailsViewModel? = null
    private lateinit var gpsTracker: GpsTracker
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_details)
        setVisible(1,0)
        val factory = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        weatherViewModel = ViewModelProvider(this, factory).get(WeatherDetailsViewModel::class.java)

        setData()
        weatherViewModel!!.observeWeatherDetailsInfo().observe(this,{
            if(it != null ){
                setVisible(0,1)
                rvWeatherDetails.adapter = viewDetailsAdapter
                viewDetailsAdapter.setItem(it.dataseries)
            }
        })
    }


    private fun setData() {

        gpsTracker = GpsTracker(this)

        if(gpsTracker.canGetLocation()){
            val latitude = gpsTracker.getLatitude()
            val longittude = gpsTracker.getLatitude()
            weatherViewModel!!.requestWeatherDetailsInfo(longittude.toString(),latitude.toString())
            Log.d("Location info ", "$latitude & $longittude")
        }
        else{
            with(gpsTracker) { showSettingsAlert() }
            setVisible(0,0)
        }
    }

    private fun setVisible(pb: Int, rv: Int){
        pbLoad.visibility = if(pb==0) View.GONE else View.VISIBLE
        rvWeatherDetails.visibility = if(rv==0) View.GONE else View.VISIBLE

    }
}