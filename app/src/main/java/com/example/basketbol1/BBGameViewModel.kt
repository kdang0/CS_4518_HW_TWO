package com.example.basketbol1

import androidx.lifecycle.ViewModel

class BBGameViewModel : ViewModel() {
    val whoWon: Boolean = false
    val bbgames = mutableListOf<BBGame>()
/*
    init {

        for (i in 0 until 100) {
            val bbgame = BBGame()
            bbgames += bbgame
        }


    }
 */
    fun addBBGame(bbgame : BBGame){
        BBGameRepository.addBBGame(bbgame)
    }

    private val BBGameRepository = com.example.basketbol1.BBGameRepository.get()
    val BBGamesLiveData = BBGameRepository.getBBGames()
    val BBGamesALiveData = BBGameRepository.getBBGameAWin()
    val BBGamesBLiveData = BBGameRepository.getBBGameBWin()
}