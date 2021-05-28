package com.gokul.weather.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.gokul.weather.model.WeatherInfo

class WeatherViewModel(application: Application) : AndroidViewModel(application) {

    private val weatherInfo: WeatherInfoVM = WeatherInfoVM.instance!!

    fun requestWeatherInfo(log: String, lat: String) {
        weatherInfo.requestWeatherData(log, lat)
    }

    fun observeWeatherInfo(): MutableLiveData<WeatherInfo?> {
        return weatherInfo.observeWeatherData()
    }
}