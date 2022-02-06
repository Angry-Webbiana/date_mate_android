package com.angrywebbiana.datemate.data.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.angrywebbiana.datemate.domain.model.Schedule
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface ScheduleDao {
    @Insert
    fun insert(vararg schedule: Schedule): Completable

    @Insert
    fun insert(schedules: List<Schedule>): Completable

    @Query("DELETE FROM schedule WHERE seq = :id")
    fun delete(id: Int): Completable

    @Query("SELECT * FROM schedule")
    fun getAll(): Single<List<Schedule>>
}