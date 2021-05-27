package com.gokul.weather

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gokul.weather.api.ApiClient
import com.gokul.weather.api.ApiInterface
import com.gokul.weather.model.WeatherInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherInfoVM: ViewModel() {
    private val weatherModel: MutableLiveData<WeatherInfo?> = MutableLiveData()

    fun observeWeatherData(): MutableLiveData<WeatherInfo?> {
        return weatherModel
    }

    fun requestWeatherData(log:String, lat:String) {

        val weatherDataCall: Call<WeatherInfo> = myInterface.getWeather(log, lat, "civillight","json")
        weatherDataCall.enqueue(object : Callback<WeatherInfo?> {
            override fun onResponse(call: Call<WeatherInfo?>, response: Response<WeatherInfo?>) {
                weatherModel.value = response.body()
                Log.d("API RESPONSE", "${response.isSuccessful}")
                Log.d("API RESPONSE", "$response")
                Log.d("API response ","${response.body()}")
            }

            override fun onFailure(call: Call<WeatherInfo?>, t: Throwable) {
                weatherModel.postValue(null)
            }
        })

    }

    companion object {
        private lateinit var myInterface: ApiInterface
        private var weatherDataRepository: WeatherInfoVM? = null
        val instance: WeatherInfoVM?
            get() {
                if (weatherDataRepository == null) {
                    weatherDataRepository = WeatherInfoVM()
                }
                return weatherDataRepository
            }
    }

    init {
        myInterface = ApiClient.buildService(ApiInterface::class.java)
    }
}