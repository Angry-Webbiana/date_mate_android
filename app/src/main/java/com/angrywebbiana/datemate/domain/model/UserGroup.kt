package com.angrywebbiana.datemate.domain.model

import com.angrywebbiana.datemate.domain.model.response.GroupListGroup
import com.google.gson.annotations.SerializedName

data class UserGroup(
    @SerializedName("id")
    var id: Int,

    @SerializedName("userSeq")
    var userSeq: Int,

    @SerializedName("groupId")
    var groupId: Int,

    @SerializedName("status")
    var status: Int,

    @SerializedName("group")
    var group: Int,

    @SerializedName("user")
    var user: User
)