package com.example.basketbol1

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.example.basketbol1.localdatabase.BBGameDatabase
import java.util.*

private const val DATABASE_NAME = "game-database"
class BBGameRepository private constructor(context : Context) {

    private val database : BBGameDatabase = Room.databaseBuilder(
        context.applicationContext,
        BBGameDatabase::class.java,
        DATABASE_NAME
    ).build()
    
    fun getBBGames() : LiveData<List<BBGame>> = database.BBGameDao().getBBGames()
    fun getBBGame(id : UUID) : LiveData<BBGame?> = database.BBGameDao().getBBGame(id)
    
    companion object{
        private var INSTANCE: BBGameRepository? = null
        fun initialize(context: Context){
            if (INSTANCE == null) {
                INSTANCE = BBGameRepository(context)
            }
        }

        fun get() : BBGameRepository {
            return INSTANCE ?:
            throw IllegalStateException("BBGameRepository must be initialized")
        }
    }
}