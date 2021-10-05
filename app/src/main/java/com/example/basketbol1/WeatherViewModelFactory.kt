package com.example.basketbol1

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class WeatherViewModelFactory(private val latitude: Double, private val longitude: Double) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeatherViewModel::class.java)) {
            return WeatherViewModel(latitude, longitude) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}