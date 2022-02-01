package com.angrybab.datemate.domain.model.response

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
    @SerializedName("token")
    var token: String
)