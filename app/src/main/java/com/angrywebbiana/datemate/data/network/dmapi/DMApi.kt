package com.angrywebbiana.datemate.data.network.dmapi

import com.angrywebbiana.datemate.domain.model.request.Login
import com.angrywebbiana.datemate.domain.model.request.SignUp
import com.angrywebbiana.datemate.domain.model.response.LoginResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface DMApi {
    @Headers("Accept: application/json", "Content-Type: application/json")
    @POST(BaseUrl.DM_API_POST_SIGN_UP)
    fun postSignUp(
        @Body signUp: SignUp
    ): Single<Any>

    @Headers("Accept: application/json", "Content-Type: application/json")
    @POST(BaseUrl.DM_API_POST_LOGIN)
    fun postLogin(
        @Body login: Login
    ): Single<LoginResponse>
}