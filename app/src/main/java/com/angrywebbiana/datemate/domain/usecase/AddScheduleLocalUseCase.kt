package com.angrywebbiana.datemate.domain.usecase

import com.angrywebbiana.datemate.data.repository.LocalRepository
import com.angrywebbiana.datemate.domain.model.Schedule
import com.angrywebbiana.datemate.domain.usecase.common.CompletableUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class AddScheduleLocalUseCase @Inject constructor(
    private val localRepository: LocalRepository
): CompletableUseCase<Schedule>() {
    override fun implement(param: Schedule): Completable {
        return localRepository.insertSchedule(param)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}