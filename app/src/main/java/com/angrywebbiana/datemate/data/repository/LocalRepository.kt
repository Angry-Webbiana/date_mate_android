package com.angrywebbiana.datemate.data.repository

import com.angrywebbiana.datemate.data.local.room.dao.ScheduleDao
import com.angrywebbiana.datemate.domain.model.Schedule
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class LocalRepository @Inject constructor(
    private val scheduleDao: ScheduleDao
) {
    fun getAllSchedulesList(): Single<List<Schedule>> =
        scheduleDao.getAll()

    fun insertSchedule(item: Schedule): Completable =
        scheduleDao.insert(item)

    fun deleteSchedule(id: Int): Completable =
        scheduleDao.delete(id)
}