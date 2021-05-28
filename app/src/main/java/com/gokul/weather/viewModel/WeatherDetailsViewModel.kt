package com.gokul.weather.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.gokul.weather.WeatherInfoVM
import com.gokul.weather.model.WeatherDetails
import com.gokul.weather.model.WeatherInfo

class WeatherDetailsViewModel (application: Application) : AndroidViewModel(application){
    private val weatherInfo: WeatherInfoVM = WeatherInfoVM.instance!!

    fun requestWeatherDetailsInfo(log: String, lat: String) {
        weatherInfo.requestWeatherDetailsData(log, lat)
    }

    fun observeWeatherDetailsInfo(): MutableLiveData<WeatherDetails?> {
        return weatherInfo.observeWeatherDetailsData()
    }
}
