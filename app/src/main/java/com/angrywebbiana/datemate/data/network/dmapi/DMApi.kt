package com.angrywebbiana.datemate.data.network.dmapi

import com.angrywebbiana.datemate.domain.model.request.*
import com.angrywebbiana.datemate.domain.model.response.*
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

    @Headers("Accept: application/json", "Content-Type: application/json")
    @GET(BaseUrl.DM_API_GET_GROUP)
    fun getGroupInfo(
        @Header("Authorization") auth: String,
        @Query("groupId") id: Int
    ): Single<GroupInfoResponse>

    @Headers("Accept: application/json", "Content-Type: application/json")
    @GET(BaseUrl.DM_API_GET_GROUP_LIST)
    fun getGroupList(
        @Header("Authorization") auth: String,
    ): Single<GroupListResponse>

    @Headers("Accept: application/json", "Content-Type: application/json")
    @POST(BaseUrl.DM_API_POST_GROUP_CREATE)
    fun postGroupCreate(
        @Header("Authorization") auth: String,
        @Body createGroupRequest: CreateGroupRequest
    ): Single<CommonResponse>

    @Headers("Accept: application/json", "Content-Type: application/json")
    @POST(BaseUrl.DM_API_POST_GROUP_INVITE)
    fun postGroupInvite(
        @Header("Authorization") auth: String,
        @Body inviteGroupRequest: InviteOrLeaveGroupRequest
    ): Single<CommonResponse>

    @Headers("Accept: application/json", "Content-Type: application/json")
    @POST(BaseUrl.DM_API_POST_GROUP_LEAVE)
    fun postGroupLeave(
        @Header("Authorization") auth: String,
        @Body leaveGroupRequest: InviteOrLeaveGroupRequest
    ): Single<CommonResponse>
}