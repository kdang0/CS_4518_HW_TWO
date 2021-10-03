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

    fun fetchWeather(): LiveData<List<WeatherItem>> {
        val responseLiveData: MutableLiveData<List<WeatherItem>> = MutableLiveData()
        val openWeatherMapRequest: Call<OpenWeatherMapResponse> = openWeatherMapApi.fetchWeather()
        openWeatherMapRequest.enqueue(object : Callback<OpenWeatherMapResponse> {
            override fun onFailure(call: Call<OpenWeatherMapResponse>, t: Throwable) {
                Log.e(TAG, "Failed to fetch weather", t)
            }

            override fun onResponse(call: Call<OpenWeatherMapResponse>, response: Response<OpenWeatherMapResponse>) {
                Log.d(TAG, "Response received")
                val openWeatherMapResponse: OpenWeatherMapResponse? = response.body()
                val weatherResponse: WeatherResponse? = openWeatherMapResponse?.weatherResponse
                var weatherItems: List<WeatherItem>? = weatherResponse?.weatherItems
                responseLiveData.value = weatherItems
            }
        })
        return responseLiveData
    }
}