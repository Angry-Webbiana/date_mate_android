package com.angrywebbiana.datemate.domain.usecase

import com.angrywebbiana.datemate.data.repository.RemoteRepository
import com.angrywebbiana.datemate.domain.model.request.SignUp
import com.angrywebbiana.datemate.domain.usecase.common.SingleUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class PostSignUpUseCase @Inject constructor(
    private val remoteRepository: RemoteRepository
): SingleUseCase<SignUp, Any>() {
    override fun implement(param: SignUp): Single<Any> {
        return remoteRepository.postSignUp(param)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}