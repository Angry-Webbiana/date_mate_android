package com.angrywebbiana.datemate.presentation.ui.schedule

import android.graphics.Paint
import android.os.Bundle
import android.widget.EditText
import android.widget.TimePicker
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.angrywebbiana.datemate.R
import com.angrywebbiana.datemate.databinding.ActivityAddScheduleBinding
import dagger.hilt.android.AndroidEntryPoint
import java.lang.reflect.Field


@AndroidEntryPoint
class AddScheduleActivity: AppCompatActivity() {

    private lateinit var binding: ActivityAddScheduleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddScheduleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        //setNumberPickerTextColor(binding.timePickerAddSchedule)
    }

    fun setNumberPickerTextColor(numberPicker: TimePicker): Boolean {
        val count = numberPicker.childCount
        val color = ContextCompat.getColor(this@AddScheduleActivity, R.color.default_text_color)
        for (i in 0 until count) {
            val child = numberPicker.getChildAt(i)
            if (child is EditText) {
                try {
                    val selectorWheelPaintField: Field =
                        numberPicker.javaClass.getDeclaredField("mSelectorWheelPaint")
                    selectorWheelPaintField.isAccessible = true
                    (selectorWheelPaintField.get(numberPicker) as Paint).color = color
                    child.setTextColor(color)
                    numberPicker.invalidate()
                    return true
                } catch (e: NoSuchFieldException) {
                    //Log.w("setNumberPickerTextColor", e);
                } catch (e: IllegalAccessException) {
                    //Log.w("setNumberPickerTextColor", e);
                } catch (e: IllegalArgumentException) {
                    //Log.w("setNumberPickerTextColor", e);
                }
            }
        }
        return false
    }
}