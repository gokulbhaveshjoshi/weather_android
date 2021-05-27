package com.gokul.weather.model

data class Datasery(
    val date: Int,
    val temp2m: Temp2m,
    val weather: String,
    val wind10m_max: Int
)