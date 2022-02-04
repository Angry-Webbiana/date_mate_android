package com.angrywebbiana.datemate.presentation.ui.mainfragment.privatespace

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.angrywebbiana.datemate.R
import com.angrywebbiana.datemate.databinding.CalendarDayLayoutBinding
import com.angrywebbiana.datemate.databinding.FragmentPrivateSpaceBinding
import com.angrywebbiana.datemate.presentation.util.daysOfWeekFromLocale
import com.kizitonwose.calendarview.model.CalendarDay
import com.kizitonwose.calendarview.model.DayOwner
import com.kizitonwose.calendarview.model.InDateStyle
import com.kizitonwose.calendarview.ui.DayBinder
import com.kizitonwose.calendarview.ui.ViewContainer
import com.kizitonwose.calendarview.utils.yearMonth
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class PrivateSpaceFragment : Fragment() {

    private val privateSpaceViewModel: PrivateSpaceViewModel by viewModels()

    private var _binding: FragmentPrivateSpaceBinding? = null
    private val binding get() = _binding!!

    private val selectedDates = mutableSetOf<LocalDate>()
    private val today = LocalDate.now()
    private val monthTitleFormatter = DateTimeFormatter.ofPattern("MMMM")

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
        /*binding.cvPrivateCalendar.updateMonthConfiguration(
            inDateStyle = InDateStyle.ALL_MONTHS,
            maxRowCount = 6,
            hasBoundaries = true
        )*/
        binding.cvPrivateCalendar.scrollToMonth(currentMonth)

        binding.cvPrivateCalendar.dayBinder = object : DayBinder<DayViewContainer> {
            override fun create(view: View) = DayViewContainer(view)
            override fun bind(container: DayViewContainer, day: CalendarDay) {
                container.day = day
                val textView = container.bindingCalendarDay.tvDay
                textView.text = day.date.dayOfMonth.toString()
                val selectedView = container.bindingCalendarDay.viewSelectedBg
                if (day.owner == DayOwner.THIS_MONTH) {
                    when {
                        selectedDates.contains(day.date) -> {
                            selectedView.visibility = View.VISIBLE
                        }
                        today == day.date -> {
                            textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.purple_200))
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

        return binding.root
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
                return@setOnLongClickListener true
            }
        }
    }
}