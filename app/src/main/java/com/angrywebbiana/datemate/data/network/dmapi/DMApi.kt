package com.angrywebbiana.datemate.data.network.dmapi

import com.angrywebbiana.datemate.domain.model.request.AddFollower
import com.angrywebbiana.datemate.domain.model.request.Login
import com.angrywebbiana.datemate.domain.model.request.SignUp
import com.angrywebbiana.datemate.domain.model.request.TargetUserSeq
import com.angrywebbiana.datemate.domain.model.response.CommonResponse
import com.angrywebbiana.datemate.domain.model.response.FollowersResponse
import com.angrywebbiana.datemate.domain.model.response.LoginResponse
import com.angrywebbiana.datemate.domain.model.response.UserProfileResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*

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

    @Headers("Accept: application/json", "Content-Type: application/json")
    @GET(BaseUrl.DM_API_GET_FOLLOWERS_LIST)
    fun getFollowers(
        @Header("Authorization") auth: String
    ): Single<FollowersResponse>

    @Headers("Accept: application/json", "Content-Type: application/json")
    @GET(BaseUrl.DM_API_GET_USER_PROFILE)
    fun getUserProfile(
        @Header("Authorization") auth: String,
        @Query("email") email: String
    ): Single<UserProfileResponse>

    @Headers("Accept: application/json", "Content-Type: application/json")
    @POST(BaseUrl.DM_API_POST_ADD_FRIEND)
    fun postAddFriend(
        @Header("Authorization") auth: String,
        @Body seq: TargetUserSeq
    ): Single<CommonResponse>
}