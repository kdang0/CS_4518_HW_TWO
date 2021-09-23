package com.example.basketbol1

import android.app.Application

class BBGameIntentApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        BBGameRepository.initialize(this)
    }
}