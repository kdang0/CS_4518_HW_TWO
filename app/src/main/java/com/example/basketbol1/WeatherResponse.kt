package com.example.basketbol1

import com.google.gson.annotations.SerializedName

class WeatherResponse {
//    lateinit var weatherNameItems: List<WeatherNameItem>
    @SerializedName("main")
     lateinit var weatherTempItems : List<WeatherTempItem>
}