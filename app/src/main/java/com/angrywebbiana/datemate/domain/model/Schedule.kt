package com.angrywebbiana.datemate.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "schedule")
data class Schedule(
    @PrimaryKey
    var seq: Int,

    @ColumnInfo(name = "schedule_name")
    var scheduleName: String,

    @ColumnInfo(name = "schedule_desc")
    var scheduleDesc: String,

    @ColumnInfo(name = "start_date")
    var startDate: Date,

    @ColumnInfo(name = "end_date")
    var endDate: Date,

    @ColumnInfo(name = "type")
    var type: String,

    @ColumnInfo(name = "status")
    var status: Int,

    @ColumnInfo(name = "create_user_seq")
    var createUserSeq: Int
)