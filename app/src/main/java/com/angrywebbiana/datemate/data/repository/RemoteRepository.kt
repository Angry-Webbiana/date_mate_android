package com.angrywebbiana.datemate.data.repository

import com.angrywebbiana.datemate.data.network.dmapi.DMApi
import com.angrywebbiana.datemate.domain.model.request.*
import com.angrywebbiana.datemate.domain.model.response.*
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class RemoteRepository @Inject constructor(
    private val DMApiClient: DMApi,
) {
    fun postSignUp(param: SignUp): Single<Any> =
        DMApiClient.postSignUp(param)

    fun postLogin(param: Login): Single<LoginResponse> =
        DMApiClient.postLogin(param)

    fun getFollowers(param: String): Single<FollowersResponse> =
        DMApiClient.getFollowers(param)

    fun getUserProfile(param: UserProfile): Single<UserProfileResponse> =
        DMApiClient.getUserProfile(param.auth, param.email)

    fun postAddFollower(param: AddFollower): Single<CommonResponse> =
        DMApiClient.postAddFriend(param.auth, param.targetUserSeq)

    fun getGroupInfo(param: GroupInfo): Single<GroupInfoResponse> =
        DMApiClient.getGroupInfo(param.auth, param.groupId)

    fun getGroupList(param: String): Single<GroupListResponse> =
        DMApiClient.getGroupList(param)

    fun postGroupCreate(param: CreateGroup): Single<CommonResponse> =
        DMApiClient.postGroupCreate(param.auth, param.createGroupRequest)

    fun postGroupInvite(param: InviteOrLeaveGroup): Single<CommonResponse> =
        DMApiClient.postGroupInvite(param.auth, param.inviteOrLeaveGroupRequest)

    fun postGroupLeave(param: InviteOrLeaveGroup): Single<CommonResponse> =
        DMApiClient.postGroupLeave(param.auth, param.inviteOrLeaveGroupRequest)
}