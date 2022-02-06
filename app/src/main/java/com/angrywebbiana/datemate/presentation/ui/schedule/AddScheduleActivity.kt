package com.angrywebbiana.datemate.presentation.ui.schedule

import android.os.Bundle
import android.widget.DatePicker
import android.widget.DatePicker.OnDateChangedListener
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.adapters.CalendarViewBindingAdapter
import com.angrywebbiana.datemate.databinding.ActivityAddScheduleBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class AddScheduleActivity: AppCompatActivity() {

    private lateinit var binding: ActivityAddScheduleBinding

    val calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddScheduleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*datePicker.init(
            calendar[Calendar.YEAR], calendar[Calendar.MONTH],
            calendar[Calendar.DAY_OF_MONTH],
            OnDateChangedListener { view: DatePicker?, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                setDate(
                    year,
                    monthOfYear,
                    dayOfMonth
                )
            })*/


        binding.datePickerAddList.init(
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH),
            OnDateChangedListener { view: DatePicker?, year: Int, monthOfYear: Int, dayOfMonth: Int ->

            }
        )
    }
}