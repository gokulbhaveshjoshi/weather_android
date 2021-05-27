package com.gokul.weather.api

import com.gokul.weather.model.WeatherInfo
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
//    @GET("location/search/")
//    fun getLocation(@Query("query") query: String): Call<List<WeatherLocation>>
//
//    @GET("location/{woeid}/")
//    fun getWeather(@Path("woeid") woeid: Int): Call<WeatherModel>

    @GET("api.pl")
    fun getWeather(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("product") product: String,
        @Query("output") output: String

    ): retrofit2.Call<WeatherInfo>
}