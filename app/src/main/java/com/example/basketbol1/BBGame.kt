package com.example.basketbol1

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.basketbol1.BBGameRepository.Companion.get
import java.util.*

@Entity(tableName = "table_game")
data class BBGame(
    @PrimaryKey val id:UUID = UUID.randomUUID(),
    var teamAName : String = "A Team",
    var teamBName : String = "B team",
    var teamAScore: Int = 0,
    var teamBScore: Int = 0,
    @ColumnInfo(name= "date") var date: Date = Date()
) {
    val photoAFileName get() = "IMG_$id.jpg"
    val photoBFileName get() = "IMG_$id.jpg"

}



