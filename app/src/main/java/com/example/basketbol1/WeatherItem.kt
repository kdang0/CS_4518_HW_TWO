package com.example.basketbol1

import com.google.gson.annotations.SerializedName

data class WeatherItem(
    var name: String = "",
    var id: String = "",
    @SerializedName("main.temp") var mainTemp: String = ""
)
