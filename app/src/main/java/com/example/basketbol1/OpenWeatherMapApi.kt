package com.example.basketbol1

import retrofit2.Call
import retrofit2.http.GET

interface OpenWeatherMapApi {
    @GET("data/2.5/weather?id=4956184" +
            "&appID=475f6e184f638ba53d8ef78248c620e1" +
            "&format=json" +
            "&nojsoncallback=1" +
            "&extras=url_s"
    )
    fun fetchWeather(): Call<OpenWeatherMapResponse>
}