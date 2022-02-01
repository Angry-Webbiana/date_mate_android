package com.angrywebbiana.datemate.domain.usecase

import com.angrywebbiana.datemate.data.repository.RemoteRepository
import com.angrywebbiana.datemate.domain.model.request.Login
import com.angrywebbiana.datemate.domain.model.response.LoginResponse
import com.angrywebbiana.datemate.domain.usecase.common.SingleUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class PostLoginUseCase @Inject constructor(
    private val remoteRepository: RemoteRepository
): SingleUseCase<Login, LoginResponse>() {
    override fun implement(param: Login): Single<LoginResponse> {
        return remoteRepository.postLogin(param)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}