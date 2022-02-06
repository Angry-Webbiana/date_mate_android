package com.angrywebbiana.datemate.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.angrywebbiana.datemate.data.local.room.dao.ScheduleDao
import com.angrywebbiana.datemate.domain.model.Schedule

@Database(entities = [Schedule::class], version = 1)
@TypeConverters(Converters::class)
abstract class Database: RoomDatabase() {
    abstract fun scheduleDao(): ScheduleDao
}