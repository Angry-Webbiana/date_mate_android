package com.angrywebbiana.datemate.domain.usecase

import com.angrywebbiana.datemate.data.repository.RemoteRepository
import com.angrywebbiana.datemate.domain.model.request.UserProfile
import com.angrywebbiana.datemate.domain.model.response.UserProfileResponse
import com.angrywebbiana.datemate.domain.usecase.common.SingleUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class GetUserProfileUseCase @Inject constructor(
    private val remoteRepository: RemoteRepository
): SingleUseCase<UserProfile, UserProfileResponse>() {
    override fun implement(param: UserProfile): Single<UserProfileResponse> {
        return remoteRepository.getUserProfile(param)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}