package com.example.basketbol1

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "table_game")
data class BBGame(
    @PrimaryKey val id:UUID = UUID.randomUUID(),
    @ColumnInfo(name= "col_game") var date: Date = Date(),
    var team1: Team2 = Team2("Team A", 0),
    var team2: Team2 = Team2("Team B", 1)
)
