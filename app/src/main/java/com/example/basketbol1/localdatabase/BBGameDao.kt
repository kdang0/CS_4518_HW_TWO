package com.example.basketbol1.localdatabase

import androidx.room.Dao
import androidx.room.Query
import com.example.basketbol1.BBGame
import java.util.*

@Dao
interface BBGameDao {

    @Query("SELECT * FROM table_game")
    fun getBBGames(): List<BBGame>

    @Query("SELECT * FROM table_game WHERE id=(:id)")
    fun getBBGame(id: UUID): BBGame?
}