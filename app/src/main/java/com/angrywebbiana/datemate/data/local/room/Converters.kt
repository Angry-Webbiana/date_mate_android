package com.angrywebbiana.datemate.data.local.room

import androidx.room.TypeConverter
import java.util.*

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long) = Date(value)

    @TypeConverter
    fun dateToTimestamp(date: Date) = date.time
}