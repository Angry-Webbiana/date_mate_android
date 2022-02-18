package com.angrywebbiana.datemate.presentation.ui.schedule

import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TimePicker
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.angrywebbiana.datemate.R
import com.angrywebbiana.datemate.data.repository.SharedPrefRepository
import com.angrywebbiana.datemate.databinding.ActivityAddScheduleBinding
import com.angrywebbiana.datemate.domain.model.Schedule
import dagger.hilt.android.AndroidEntryPoint
import java.lang.reflect.Field
import java.util.*
import javax.inject.Inject


@AndroidEntryPoint
class AddScheduleActivity: AppCompatActivity() {

    @Inject
    lateinit var sharedPrefRepository: SharedPrefRepository

    private lateinit var binding: ActivityAddScheduleBinding

    private val addScheduleViewModel: AddScheduleViewModel by viewModels()

    private var spaceType = ""
    private var dateTimeStamp: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddScheduleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        spaceType = intent.getStringExtra("spaceType").toString()
        dateTimeStamp = intent.getLongExtra("dateTimestamp", 0)

        Log.d("TESTLOG", "spaceType: $spaceType")
        Log.d("TESTLOG", "dateTimeStamp: $dateTimeStamp")

        binding.btnAddSchedule.setOnClickListener {
            val scheduleName = binding.etAddScheduleTitle.text.toString()
            val scheduleDesc = binding.etAddScheduleContents.text.toString()

            val hour = binding.timePickerAddSchedule.hour
            val minute = binding.timePickerAddSchedule.minute
            val scheduleStartDateTime = GregorianCalendar(Locale.getDefault())
            scheduleStartDateTime.time = Date(dateTimeStamp)
            scheduleStartDateTime.set(Calendar.HOUR_OF_DAY, hour)
            scheduleStartDateTime.set(Calendar.MINUTE, minute)

            val schedule = Schedule(
                scheduleName = scheduleName,
                scheduleDesc = scheduleDesc,
                startDate = scheduleStartDateTime.time,
                endDate = scheduleStartDateTime.time,
                type = "P",
                status = 0,
                createUserSeq = sharedPrefRepository.getUserSeq()
            )
            if (spaceType == "P")
                addScheduleViewModel.addScheduleLocal(schedule)
        }

        binding.btnAddScheduleCancel.setOnClickListener {
            finish()
        }

        addScheduleViewModel.isSuccessAddSchedule.observe(this, {
            if (it) {
                Toast.makeText(this, "일정 추가 완료!", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "일정에 실패하였습니다. 다시 시도해주세요.", Toast.LENGTH_SHORT).show()
            }
        })

        //setNumberPickerTextColor(binding.timePickerAddSchedule)
    }
}