package com.angrywebbiana.datemate.domain.usecase

import com.angrywebbiana.datemate.data.repository.RemoteRepository
import com.angrywebbiana.datemate.domain.model.response.FollowersResponse
import com.angrywebbiana.datemate.domain.usecase.common.SingleUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class GetFollowersUseCase @Inject constructor(
    private val remoteRepository: RemoteRepository
): SingleUseCase<String, FollowersResponse>() {
    override fun implement(param: String): Single<FollowersResponse> {
        return remoteRepository.getFollowers(param)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}