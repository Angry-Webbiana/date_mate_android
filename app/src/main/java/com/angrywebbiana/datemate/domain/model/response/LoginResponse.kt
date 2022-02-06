package com.angrywebbiana.datemate.domain.model.response

import com.angrywebbiana.datemate.domain.model.User
import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("responseCode")
    var responseCode: String,

    @SerializedName("message")
    var message: Message,

    @SerializedName("errMsg")
    var errMessage: String?
)

data class Message(
    @SerializedName("user")
    var user: User,

    @SerializedName("token")
    var token: String
)