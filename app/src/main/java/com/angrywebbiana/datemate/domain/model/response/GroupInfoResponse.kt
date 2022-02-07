package com.angrywebbiana.datemate.domain.model.response

import com.angrywebbiana.datemate.domain.model.Group
import com.google.gson.annotations.SerializedName

data class GroupInfoResponse(
    @SerializedName("responseCode")
    var responseCode: String,

    @SerializedName("message")
    var message: GroupInfoMessage,

    @SerializedName("errMsg")
    var errMsg: String?
)

data class GroupInfoMessage(
    @SerializedName("group")
    var group: Group
)
