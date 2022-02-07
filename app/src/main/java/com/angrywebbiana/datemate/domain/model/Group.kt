package com.angrywebbiana.datemate.domain.model

import com.google.gson.annotations.SerializedName

data class Group(
    @SerializedName("id")
    var id: Int,

    @SerializedName("groupId")
    var groupId: Int,

    @SerializedName("groupName")
    var groupName: String,

    @SerializedName("groupOwner")
    var groupOwner: Int,

    @SerializedName("groupDesc")
    var groupDesc: String,

    @SerializedName("userGroupList")
    var userGroupList: List<UserGroup>
)