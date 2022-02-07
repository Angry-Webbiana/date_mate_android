package com.angrywebbiana.datemate.domain.model.response

import com.google.gson.annotations.SerializedName

data class GroupListResponse(
    @SerializedName("responseCode")
    var responseCode: String,

    @SerializedName("message")
    var message: GroupListMessage,

    @SerializedName("errMsg")
    var errMsg: String?
)

data class GroupListMessage(
    @SerializedName("groupList")
    var groupList: List<GroupList>
)

data class GroupList(
    @SerializedName("id")
    var id: Int,

    @SerializedName("userSeq")
    var userSeq: Int,

    @SerializedName("groupId")
    var groupId: Int,

    @SerializedName("status")
    var status: Int,

    @SerializedName("group")
    var group: GroupListGroup
)

data class GroupListGroup(
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
    var userGroupList: List<Int>
)