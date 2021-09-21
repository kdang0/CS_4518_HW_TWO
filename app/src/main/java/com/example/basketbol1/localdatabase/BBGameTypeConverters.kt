package com.example.basketbol1.localdatabase

import androidx.room.TypeConverter
import java.util.*

class BBGameTypeConverters {

    @TypeConverter
    fun fromUUID(uuid: UUID?): String?{
        return uuid?.toString()
    }

    @TypeConverter
    fun toUUID(uuid: String?): UUID?{
        return UUID.fromString(uuid)
    }

    @TypeConverter
    fun fromDate(date:Date?):Long?{
        return date?.time
    }

    @TypeConverter
    fun toDate(millisSinceEpoch : Long?) : Date?{
        return millisSinceEpoch?.let{
            Date(it)
        }
    }
}