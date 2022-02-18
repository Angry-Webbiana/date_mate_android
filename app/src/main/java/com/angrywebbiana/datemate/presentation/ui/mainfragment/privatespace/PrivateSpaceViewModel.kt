package com.angrywebbiana.datemate.presentation.ui.mainfragment.privatespace

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.angrywebbiana.datemate.domain.model.Schedule
import com.angrywebbiana.datemate.domain.usecase.AddScheduleLocalUseCase
import com.angrywebbiana.datemate.domain.usecase.SelectScheduleListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import javax.inject.Inject

@HiltViewModel
class PrivateSpaceViewModel @Inject constructor(
    private val selectScheduleListUseCase: SelectScheduleListUseCase,
    private val addScheduleLocalUseCase: AddScheduleLocalUseCase
): ViewModel() {
    private val _localScheduleListLiveData = MutableLiveData<List<Schedule>>()
    val localScheduleListLiveData: LiveData<List<Schedule>> get() = _localScheduleListLiveData

    fun getScheduleListLocal() {
        selectScheduleListUseCase.execute(Any())
            .subscribe(object: SingleObserver<List<Schedule>> {
                override fun onSubscribe(d: Disposable) {
                }

                override fun onSuccess(t: List<Schedule>) {
                    _localScheduleListLiveData.value = t
                }

                override fun onError(e: Throwable) {
                    Log.e("TESTLOG", "[getScheduleListLocal] onError: $e")
                }
            })
    }

    fun saveHolidayScheduleLocal(schedule: Schedule) {
        addScheduleLocalUseCase.execute(schedule)
            .subscribe({
                Log.d("TESTLOG", "[saveHolidayScheduleLocal] onSuccess")
            }, {
                Log.e("TESTLOG", "[saveHolidayScheduleLocal] onError: $it")
            })
    }
}