package com.angrywebbiana.datemate.domain.model.response

import com.angrywebbiana.datemate.domain.model.User
import com.google.gson.annotations.SerializedName

data class UserProfileResponse(
    @SerializedName("responseCode")
    var responseCode: String,

    @SerializedName("message")
    var message: UserProfileMessage,

    @SerializedName("errMsg")
    var errMessage: String?
)

data class UserProfileMessage(
    @SerializedName("profile")
    var user: User,
)