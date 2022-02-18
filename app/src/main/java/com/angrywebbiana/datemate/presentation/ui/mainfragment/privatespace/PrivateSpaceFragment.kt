package com.angrywebbiana.datemate.presentation.ui.mainfragment.privatespace

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.GridLayoutManager
import com.angrywebbiana.datemate.MainActivity
import com.angrywebbiana.datemate.R
import com.angrywebbiana.datemate.data.repository.SharedPrefRepository
import com.angrywebbiana.datemate.databinding.CalendarDayLayoutBinding
import com.angrywebbiana.datemate.databinding.FragmentPrivateSpaceBinding
import com.angrywebbiana.datemate.presentation.ui.mainfragment.followers.add.AddFollowerActivity
import com.angrywebbiana.datemate.presentation.ui.mainfragment.privatespace.adpater.PrivateScheduleAdapter
import com.angrywebbiana.datemate.presentation.ui.schedule.AddScheduleActivity
import com.angrywebbiana.datemate.presentation.util.daysOfWeekFromLocale
import com.kizitonwose.calendarview.model.CalendarDay
import com.kizitonwose.calendarview.model.DayOwner
import com.kizitonwose.calendarview.ui.DayBinder
import com.kizitonwose.calendarview.ui.ViewContainer
import com.kizitonwose.calendarview.utils.yearMonth
import dagger.hilt.android.AndroidEntryPoint
import java.sql.Date
import java.sql.Timestamp
import java.time.LocalDate
import java.time.YearMonth
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@AndroidEntryPoint
class PrivateSpaceFragment : Fragment() {

    private val privateSpaceViewModel: PrivateSpaceViewModel by viewModels()

    private var _binding: FragmentPrivateSpaceBinding? = null
    private val binding get() = _binding!!

    private val selectedDates = mutableSetOf<LocalDate>()
    private val today = LocalDate.now()
    private val monthTitleFormatter = DateTimeFormatter.ofPattern("MMMM")

    private val scheduleDates = mutableSetOf<LocalDate>()

    private val adapter by lazy {
        PrivateScheduleAdapter()
    }

    @Inject
    lateinit var sharedPrefRepository: SharedPrefRepository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPrivateSpaceBinding.inflate(inflater, container, false)

        val currentMonth = YearMonth.now()
        val startMonth = currentMonth.minusMonths(360)
        val endMonth = currentMonth.plusMonths(360)
        val daysOfWeek = daysOfWeekFromLocale()

        binding.cvPrivateCalendar.setup(startMonth, endMonth, daysOfWeek.first())
        binding.cvPrivateCalendar.scrollToMonth(currentMonth)

        binding.cvPrivateCalendar.dayBinder = object : DayBinder<DayViewContainer> {
            override fun create(view: View) = DayViewContainer(view)
            override fun bind(container: DayViewContainer, day: CalendarDay) {
                container.day = day
                val textView = container.bindingCalendarDay.tvDay
                textView.text = day.date.dayOfMonth.toString()
                val selectedView = container.bindingCalendarDay.viewSelectedBg
                val scheduleView = container.bindingCalendarDay.viewSchedule
                if (day.owner == DayOwner.THIS_MONTH) {
                    when {
                        selectedDates.contains(day.date) -> {
                            selectedView.visibility = View.VISIBLE
                        }
                        scheduleDates.contains(day.date) -> {
                            scheduleView.visibility = View.VISIBLE
                        }
                        today == day.date -> {
                            textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.purple_200))
                            if (selectedDates.contains(day.date)) {
                                selectedView.visibility = View.VISIBLE
                            } else {
                                selectedView.visibility = View.GONE
                            }
                            //selectedView.visibility = View.VISIBLE
                        }
                        else -> {
                            textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.default_text_color))
                            selectedView.visibility = View.GONE
                        }
                    }
                } else {
                    textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.teal_700))
                    //textView.background = null
                }
            }
        }

        binding.cvPrivateCalendar.monthScrollListener = {
            if (binding.cvPrivateCalendar.maxRowCount == 6) {
                binding.tvYear.text = it.yearMonth.year.toString()
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

        binding.rvPrivateSpaceList.also {
            it.layoutManager = GridLayoutManager(requireContext(), 2);
            it.adapter = adapter
        }

        privateSpaceViewModel.localScheduleListLiveData.observe(viewLifecycleOwner, {
            if (it.isNotEmpty()) {
                adapter.submitList(it.toMutableList())

                for (schedule in it) {
                    scheduleDates.add(
                        schedule.startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
                    )
                }
                binding.cvPrivateCalendar.notifyCalendarChanged()
                binding.tvNotExistSchedule.visibility = View.GONE
            } else {
                binding.tvNotExistSchedule.visibility = View.VISIBLE
            }
        })

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        val userName = sharedPrefRepository.getUserName()
        (activity as MainActivity).supportActionBar?.title = "$userName's space"

        privateSpaceViewModel.getScheduleListLocal()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    inner class DayViewContainer(view: View) : ViewContainer(view) {
        // Will be set when this container is bound. See the dayBinder.
        lateinit var day: CalendarDay
        val bindingCalendarDay = CalendarDayLayoutBinding.bind(view)

        init {
            view.setOnClickListener {
                if (day.owner == DayOwner.THIS_MONTH) {
                    if (selectedDates.contains(day.date)) {
                        selectedDates.remove(day.date)
                    } else {
                        selectedDates.add(day.date)
                    }
                    binding.cvPrivateCalendar.notifyDayChanged(day)
                }
            }
            view.setOnLongClickListener {
                val intent = Intent(requireContext(), AddScheduleActivity::class.java)
                val timestamp = Date.from(day.date.atStartOfDay(ZoneId.systemDefault()).toInstant())
                intent.putExtra("spaceType", "P")
                intent.putExtra("dateTimestamp", timestamp.time)
                Log.d("TESTLOG", "private space timestamp: ${timestamp.time}")
                startActivity(intent)
                return@setOnLongClickListener true
            }
        }
    }
}