package com.example.basketbol1

import androidx.lifecycle.ViewModel

class BBGameViewModel : ViewModel() {
    val bbgames = mutableListOf<BBGame>()

    init {
        for (i in 0 until 100) {
            val bbgame = BBGame()
            bbgames += bbgame
        }
    }
}