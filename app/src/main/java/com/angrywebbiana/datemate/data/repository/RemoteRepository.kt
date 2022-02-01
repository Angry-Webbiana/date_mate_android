package com.angrywebbiana.datemate.data.repository

import com.angrywebbiana.datemate.data.network.dmapi.DMApi
import com.angrywebbiana.datemate.domain.model.request.Login
import com.angrywebbiana.datemate.domain.model.request.SignUp
import com.angrywebbiana.datemate.domain.model.response.LoginResponse
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class RemoteRepository @Inject constructor(
    private val DMApiClient: DMApi,
) {
    fun postSignUp(param: SignUp): Single<Any> =
        DMApiClient.postSignUp(param)

    fun postLogin(param: Login): Single<LoginResponse> =
        DMApiClient.postLogin(param)
}