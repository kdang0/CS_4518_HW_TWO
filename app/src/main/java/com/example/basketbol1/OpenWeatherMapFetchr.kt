package com.example.basketbol1

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

private const val TAG = "OpenWeatherMapFetchr"

class OpenWeatherMapFetchr {
    private val openWeatherMapApi: OpenWeatherMapApi
    init {
        val retrofit: Retrofit = Retrofit.Builder().baseUrl("https://api.openweathermap.org/").addConverterFactory(
            GsonConverterFactory.create()).build()
        openWeatherMapApi = retrofit.create(OpenWeatherMapApi::class.java)
    }

    fun fetchWeatherTemp(lat: Double, long: Double): LiveData<WeatherTempItem> {
//        val responseLiveData: MutableLiveData<String> = MutableLiveData()
        val responseLiveData : MutableLiveData<WeatherTempItem> = MutableLiveData()
        val openWeatherMapRequest: Call<OpenWeatherMapResponse> = openWeatherMapApi.fetchWeatherTemp(lat.toString(), long.toString())
        openWeatherMapRequest.enqueue(object : Callback<OpenWeatherMapResponse> {
            override fun onFailure(call: Call<OpenWeatherMapResponse>, t: Throwable) {
                Log.e(TAG, "Failed to fetch weather", t)
            }

            override fun onResponse(call: Call<OpenWeatherMapResponse>, response: Response<OpenWeatherMapResponse>) {
                Log.d(TAG, "Response received")
//                val weatherMapResponse: WeatherResponse? = response.body()
//                responseLiveData.value = response.body()
//                val //weatherResponse: WeatherResponse? = weatherResponse?.weatherItems
                //var weatherItems: List<WeatherItem>? = weatherResponse?.weatherItems
                //responseLiveData.value = weatherItems
                val openWeatherResponse : OpenWeatherMapResponse? = response.body()
                var weatherTempItem : WeatherTempItem ?= openWeatherResponse?.main

                responseLiveData.value = weatherTempItem
            }
        })
        return responseLiveData
    }

    fun fetchWeatherLocal(lat: Double, long: Double): LiveData<WeatherNameItem> {
        val responseLiveData : MutableLiveData<WeatherNameItem> = MutableLiveData()
        val openWeatheMapRequest : Call<WeatherNameItem> = openWeatherMapApi.fetchWeatherName(lat.toString(), long.toString())
        openWeatheMapRequest.enqueue(object : Callback<WeatherNameItem> {
            override fun onFailure(call: Call<WeatherNameItem>, t:Throwable){
                Log.e(TAG, "Failed to fetch weather", t)
            }

            override fun onResponse(call : Call<WeatherNameItem>, response: Response<WeatherNameItem>){
                Log.d(TAG, "Response received")
                var weatherNameItem : WeatherNameItem ?= response.body()
                responseLiveData.value = weatherNameItem
            }
        })
        return responseLiveData
    }
}