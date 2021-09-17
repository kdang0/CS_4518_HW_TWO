package com.example.basketbol1

import androidx.annotation.StringRes

data class Team (@StringRes val textResId : Int, var score : Int)
data class Team2 (val teamName : String, var score: Int)