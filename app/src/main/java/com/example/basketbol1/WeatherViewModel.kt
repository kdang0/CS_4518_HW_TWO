package com.example.basketbol1

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class WeatherViewModel : ViewModel() {
    val weatherTempItemLiveData: LiveData<WeatherTempItem>
    val weatherNameItemLiveData: LiveData<WeatherNameItem>

    init {
        weatherNameItemLiveData = OpenWeatherMapFetchr().fetchWeatherLocal()
        weatherTempItemLiveData = OpenWeatherMapFetchr().fetchWeatherTemp()
    }
}