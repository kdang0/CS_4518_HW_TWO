package com.example.basketbol1

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.io.File
import java.util.*

private const val TAG = "BasketbolViewModel"


class BasketbolViewModel : ViewModel() {
    private val BBGameRepository = com.example.basketbol1.BBGameRepository.get()
    private val bbgameIDLiveData = MutableLiveData<UUID>()

    var bbGameLiveData: LiveData<BBGame?> = Transformations.switchMap(bbgameIDLiveData) {
        bbGameID -> BBGameRepository.getBBGame(bbGameID)
    }

    var teams = listOf(
        Team2("Team A", 0),
        Team2("Team B", 0)
    )

    var teamAPoints: Int = teams[0].score

    var teamAName : String = teams[0].teamName

    var teamBPoints: Int = teams[1].score

    var teamBName : String = teams[1].teamName

    var butIsClicked: Boolean = false

    fun updatePts(Team: String, Points: Int) {
        if (Team == "A") {
            teamAPoints += Points
        } else {
            teamBPoints += Points
        }
        Log.d(TAG, "updated points")
    }

    fun addBBGame(bbgame : BBGame){
        BBGameRepository.addBBGame(bbgame)
    }

    fun reset() {
        teamAPoints = 0
        teamBPoints = 0
        Log.d(TAG, "reset points")
    }

    fun loadBBGame(bbGameID: UUID) {
        bbgameIDLiveData.value = bbGameID
    }

    fun saveBBGame(bbGame: BBGame) {
        BBGameRepository.updateBBGame(bbGame)
    }

    fun getPhotoAFile(game : BBGame) : File {
        return BBGameRepository.getPhotoAFile(game)
    }

    fun getPhotoBFile(game : BBGame) : File {
        return BBGameRepository.getPhotoBFile(game)
    }
}