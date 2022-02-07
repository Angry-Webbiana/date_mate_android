package com.angrywebbiana.datemate.domain.model.request

import com.google.gson.annotations.SerializedName

data class InviteOrLeaveGroup(
    var auth: String,
    var inviteOrLeaveGroupRequest: InviteOrLeaveGroupRequest
)

data class InviteOrLeaveGroupRequest(
    @SerializedName("userSeq")
    var userSeq: Int,

    @SerializedName("groupId")
    var groupId: Int
)