package com.example.basketbol1.localdatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.basketbol1.BBGame
import java.util.*

@Dao
interface BBGameDao {

    @Query("SELECT * FROM table_game")
    fun getBBGames(): LiveData<List<BBGame>>

    @Query("SELECT * FROM table_game WHERE id=(:id)")
    fun getBBGame(id: UUID): LiveData<BBGame?>

    @Query("SELECT * FROM table_game WHERE teamAScore > teamBScore")
    fun getBBGameAWin(): LiveData<List<BBGame>>

    @Query("SELECT * FROM table_game WHERE teamBScore > teamAScore")
    fun getBBGameBWin(): LiveData<List<BBGame>>

    @Update
    fun updateGame(bbGame: BBGame)

    @Insert
    fun addGame(bbGame: BBGame)
}