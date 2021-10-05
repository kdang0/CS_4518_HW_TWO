package com.example.basketbol1

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherMapApi {
    @GET("data/2.5/weather?" +
            "&appID=475f6e184f638ba53d8ef78248c620e1" +
            "&format=json" +
            "&nojsoncallback=1" +
            "&extras=url_s"
    )
    fun fetchWeatherName(@Query("lat")lat: String, @Query("lon") long: String): Call<WeatherNameItem>
    @GET("data/2.5/weather?" +
            "&appID=475f6e184f638ba53d8ef78248c620e1" +
            "&format=json" +
            "&nojsoncallback=1" +
            "&extras=url_s"
    )
    fun fetchWeatherTemp(@Query("lat")lat: String, @Query("lon") long: String): Call<OpenWeatherMapResponse>
}