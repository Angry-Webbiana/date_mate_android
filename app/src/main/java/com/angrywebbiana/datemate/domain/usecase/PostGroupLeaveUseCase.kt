package com.angrywebbiana.datemate.domain.usecase

import com.angrywebbiana.datemate.data.repository.RemoteRepository
import com.angrywebbiana.datemate.domain.model.request.InviteOrLeaveGroup
import com.angrywebbiana.datemate.domain.model.response.CommonResponse
import com.angrywebbiana.datemate.domain.usecase.common.SingleUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class PostGroupLeaveUseCase @Inject constructor(
    private val remoteRepository: RemoteRepository
): SingleUseCase<InviteOrLeaveGroup, CommonResponse>() {
    override fun implement(param: InviteOrLeaveGroup): Single<CommonResponse> {
        return remoteRepository.postGroupLeave(param)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}