package com.example.basketbol1.localdatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.basketbol1.BBGame

@Database(entities = [BBGame::class], version = 1)
@TypeConverters(BBGameTypeConverters::class)
abstract class BBGameDatabase: RoomDatabase() {
    abstract fun BBGameDao(): BBGameDao
}