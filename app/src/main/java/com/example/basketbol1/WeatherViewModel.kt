package com.example.basketbol1

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class WeatherViewModel(latitude: Double, longitude: Double) : ViewModel() {
    var lat = latitude
    var long = longitude
    val weatherTempItemLiveData: LiveData<WeatherTempItem>
    val weatherNameItemLiveData: LiveData<WeatherNameItem>

    init {
        weatherNameItemLiveData = OpenWeatherMapFetchr().fetchWeatherLocal(lat, long)
        weatherTempItemLiveData = OpenWeatherMapFetchr().fetchWeatherTemp(lat, long)
    }
}