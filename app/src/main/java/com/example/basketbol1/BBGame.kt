package com.example.basketbol1

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "table_game")
data class BBGame(
    @PrimaryKey val id:UUID = UUID.randomUUID(),
    var teamAName : String = "A Team",
    var teamBName : String = "B team",
    var teamAScore: Int = 0,
    var teamBScore: Int = 0,
    @ColumnInfo(name= "date") var date: Date = Date()
)


