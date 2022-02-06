package com.angrywebbiana.datemate.domain.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("userSeq")
    var userSeq: Int,

    @SerializedName("email")
    var email: String,

    @SerializedName("userId")
    var userId: String,

    @SerializedName("userName")
    var userName: String,

    @SerializedName("status")
    var status: String
)