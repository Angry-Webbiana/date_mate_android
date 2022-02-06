package com.angrywebbiana.datemate.domain.usecase

import com.angrywebbiana.datemate.data.repository.RemoteRepository
import com.angrywebbiana.datemate.domain.model.request.AddFollower
import com.angrywebbiana.datemate.domain.model.response.CommonResponse
import com.angrywebbiana.datemate.domain.usecase.common.SingleUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class PostAddFollowerUseCase @Inject constructor(
    private val remoteRepository: RemoteRepository
): SingleUseCase<AddFollower, CommonResponse>() {
    override fun implement(param: AddFollower): Single<CommonResponse> {
        return remoteRepository.postAddFollower(param)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}