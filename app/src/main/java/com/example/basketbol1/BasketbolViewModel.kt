package com.example.basketbol1

import android.util.Log
import androidx.lifecycle.ViewModel

private const val TAG = "BasketbolViewModel"


class BasketbolViewModel : ViewModel() {
    private val BBGameRepository = com.example.basketbol1.BBGameRepository.get()

    var teams = listOf(
        Team(R.string.team_a, 0),
        Team(R.string.team_b, 0)
    )

    var teamAPoints: Int = teams[0].score

    var teamBPoints: Int = teams[1].score

    var butIsClicked: Boolean = false

    fun updatePts(Team: String, Points: Int) {
        if (Team == "A") {
            teamAPoints += Points
        } else {
            teamBPoints += Points
        }
        Log.d(TAG, "updated points")
    }

    fun reset() {
        teamAPoints = 0
        teamBPoints = 0
        Log.d(TAG, "reset points")
    }

    fun saveBBGame(bbGame: BBGame) {
        BBGameRepository.updateBBGame(bbGame)
    }
}