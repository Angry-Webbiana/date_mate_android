package com.angrywebbiana.datemate.domain.model.response

import com.angrywebbiana.datemate.domain.model.User
import com.google.gson.annotations.SerializedName

data class FollowersResponse(
    @SerializedName("responseCode")
    var responseCode: String,

    @SerializedName("message")
    var message: FollowersMessage,

    @SerializedName("errMsg")
    var errMsg: String?
)

data class FollowersMessage(
    @SerializedName("userList")
    var userList: List<FollowersList>
)

data class FollowersList(
    @SerializedName("userSeq")
    var userSeq: Int,

    @SerializedName("relationUserSeq")
    var relationUserSeq: Int,

    @SerializedName("status")
    var status: String,

    @SerializedName("user")
    var user: User
)