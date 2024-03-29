package com.angrywebbiana.datemate.presentation.ui.mainfragment.groupspace

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.angrywebbiana.datemate.R
import com.angrywebbiana.datemate.databinding.ActivityGroupSpaceBinding
import com.angrywebbiana.datemate.databinding.CalendarDayLayoutBinding
import com.angrywebbiana.datemate.presentation.ui.mainfragment.privatespace.PrivateSpaceFragment
import com.angrywebbiana.datemate.presentation.util.daysOfWeekFromLocale
import com.kizitonwose.calendarview.model.CalendarDay
import com.kizitonwose.calendarview.model.DayOwner
import com.kizitonwose.calendarview.ui.DayBinder
import com.kizitonwose.calendarview.ui.ViewContainer
import com.kizitonwose.calendarview.utils.yearMonth
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class GroupSpaceActivity: AppCompatActivity() {

    private lateinit var binding: ActivityGroupSpaceBinding

    private val selectedDates = mutableSetOf<LocalDate>()
    private val today = LocalDate.now()
    private val monthTitleFormatter = DateTimeFormatter.ofPattern("MMMM")

    private val groupName by lazy {
        intent.getStringExtra("groupName")
    }

    private val groupId by lazy {
        intent.getIntExtra("groupId", 0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGroupSpaceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.elevation = 0F

        val currentMonth = YearMonth.now()
        val startMonth = currentMonth.minusMonths(360)
        val endMonth = currentMonth.plusMonths(360)
        val daysOfWeek = daysOfWeekFromLocale()

        binding.cvGroupCalendar.setup(startMonth, endMonth, daysOfWeek.first())
        binding.cvGroupCalendar.scrollToMonth(currentMonth)

        binding.cvGroupCalendar.dayBinder = object :
            DayBinder<GroupSpaceActivity.DayViewContainer> {
            override fun create(view: View) = DayViewContainer(view)
            override fun bind(container: GroupSpaceActivity.DayViewContainer, day: CalendarDay) {
                container.day = day
                val textView = container.bindingCalendarDay.tvDay
                textView.text = day.date.dayOfMonth.toString()
                val selectedView = container.bindingCalendarDay.viewSelectedBg
                if (day.owner == DayOwner.THIS_MONTH) {
                    when {
                        today == day.date -> {
                            textView.setTextColor(ContextCompat.getColor(this@GroupSpaceActivity, R.color.purple_200))
                            //selectedView.visibility = View.VISIBLE
                        }
                        // TODO TESTCODE
                        day.date == LocalDate.parse("2022-02-10") -> {
                            selectedView.background = ContextCompat.getDrawable(this@GroupSpaceActivity, R.drawable.calendar_selected_1_bg)
                            selectedView.visibility = View.VISIBLE
                        }
                        day.date == LocalDate.parse("2022-02-11") -> {
                            selectedView.background = ContextCompat.getDrawable(this@GroupSpaceActivity, R.drawable.calendar_selected_3_bg)
                            selectedView.visibility = View.VISIBLE
                        }
                        day.date == LocalDate.parse("2022-02-12") -> {
                            selectedView.background = ContextCompat.getDrawable(this@GroupSpaceActivity, R.drawable.calendar_selected_5_bg)
                            selectedView.visibility = View.VISIBLE
                        }
                        day.date == LocalDate.parse("2022-02-13") -> {
                            selectedView.background = ContextCompat.getDrawable(this@GroupSpaceActivity, R.drawable.calendar_selected_4_bg)
                            selectedView.visibility = View.VISIBLE
                        }
                        day.date == LocalDate.parse("2022-02-19") -> {
                            selectedView.background = ContextCompat.getDrawable(this@GroupSpaceActivity, R.drawable.calendar_selected_2_bg)
                            selectedView.visibility = View.VISIBLE
                        }
                        day.date == LocalDate.parse("2022-02-20") -> {
                            if (selectedDates.contains(day.date)) {
                                selectedView.background = ContextCompat.getDrawable(this@GroupSpaceActivity, R.drawable.calendar_selected_2_bg)
                            } else {
                                selectedView.background = ContextCompat.getDrawable(this@GroupSpaceActivity, R.drawable.calendar_selected_1_bg)
                            }
                            selectedView.visibility = View.VISIBLE

                        }
                        selectedDates.contains(day.date) -> {
                            selectedView.visibility = View.VISIBLE
                        }
                        else -> {
                            textView.setTextColor(ContextCompat.getColor(this@GroupSpaceActivity, R.color.default_text_color))
                            selectedView.visibility = View.GONE
                        }
                    }
                } else {
                    textView.setTextColor(ContextCompat.getColor(this@GroupSpaceActivity, R.color.teal_700))
                    //textView.background = null
                }
            }
        }

        binding.cvGroupCalendar.monthScrollListener = {
            if (binding.cvGroupCalendar.maxRowCount == 6) {
                binding.tvYear.text = it.yearMonth.year.toString() + "년"
                binding.tvMonth.text = monthTitleFormatter.format(it.yearMonth)
            } else {
                // In week mode, we show the header a bit differently.
                // We show indices with dates from different months since
                // dates overflow and cells in one index can belong to different
                // months/years.
                val firstDate = it.weekDays.first().first().date
                val lastDate = it.weekDays.last().last().date
                if (firstDate.yearMonth == lastDate.yearMonth) {
                    binding.tvYear.text = firstDate.yearMonth.year.toString()
                    binding.tvMonth.text = monthTitleFormatter.format(firstDate)
                } else {
                    binding.tvMonth.text =
                        "${monthTitleFormatter.format(firstDate)} - ${monthTitleFormatter.format(lastDate)}"
                    if (firstDate.year == lastDate.year) {
                        binding.tvYear.text = firstDate.yearMonth.year.toString()
                    } else {
                        binding.tvYear.text = "${firstDate.yearMonth.year} - ${lastDate.yearMonth.year}"
                    }
                }
            }
        }
    }

    inner class DayViewContainer(view: View) : ViewContainer(view) {
        // Will be set when this container is bound. See the dayBinder.
        lateinit var day: CalendarDay
        val bindingCalendarDay = CalendarDayLayoutBinding.bind(view)

        init {
            view.setOnClickListener {
                if (day.owner == DayOwner.THIS_MONTH) {
                    Log.d("TESTLOG", "day.date: ${day.date}")
                    if (selectedDates.contains(day.date)) {
                        selectedDates.remove(day.date)
                    } else {
                        selectedDates.add(day.date)
                    }
                    binding.cvGroupCalendar.notifyDayChanged(day)
                }
            }
            view.setOnLongClickListener {
                return@setOnLongClickListener true
            }
        }
    }

    override fun onResume() {
        super.onResume()
        this.supportActionBar?.title = groupName
    }
}