package com.angrywebbiana.datemate.domain.model.request

import com.google.gson.annotations.SerializedName

data class CreateGroup(
    var auth: String,
    var createGroupRequest: CreateGroupRequest
)

data class CreateGroupRequest(
    @SerializedName("groupName")
    var groupName: String,

    @SerializedName("groupDesc")
    var groupDesc: String,
)