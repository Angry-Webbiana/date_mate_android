package com.angrywebbiana.datemate.presentation.ui.schedule

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.angrywebbiana.datemate.domain.model.Schedule
import com.angrywebbiana.datemate.domain.usecase.AddScheduleLocalUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddScheduleViewModel @Inject constructor(
    private val addScheduleLocalUseCase: AddScheduleLocalUseCase
): ViewModel() {
    private val _isSuccessAddSchedule = MutableLiveData<Boolean>()
    val isSuccessAddSchedule: LiveData<Boolean> get() = _isSuccessAddSchedule

    fun addScheduleLocal(schedule: Schedule) {
        addScheduleLocalUseCase.execute(schedule)
            .subscribe({
                _isSuccessAddSchedule.value = true
            }, {
                _isSuccessAddSchedule.value = false
                Log.e("TESTLOG", "[addScheduleLocal] onError: $it")
            })
    }
}