package com.gokul.weather.model

data class WeatherInfo(
    val `init`: String,
    val product: String,
    val dataseries: List<Datasery>
)