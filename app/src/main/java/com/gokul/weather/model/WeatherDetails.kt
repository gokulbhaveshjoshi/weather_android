package com.gokul.weather.model

data class WeatherDetails(
    val dataseries: List<DataseryX>,
    val `init`: String,
    val product: String
)