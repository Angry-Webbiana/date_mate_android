package com.angrywebbiana.datemate.domain.usecase

import com.angrywebbiana.datemate.data.repository.LocalRepository
import com.angrywebbiana.datemate.domain.model.Schedule
import com.angrywebbiana.datemate.domain.usecase.common.SingleUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class SelectScheduleListUseCase @Inject constructor(
    private val localRepository: LocalRepository
): SingleUseCase<Any, List<Schedule>>() {
    override fun implement(param: Any): Single<List<Schedule>> {
        return localRepository.getAllSchedulesList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}