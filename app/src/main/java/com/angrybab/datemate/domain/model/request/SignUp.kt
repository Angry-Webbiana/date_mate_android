package com.angrybab.datemate.domain.model.request

import com.google.gson.annotations.SerializedName

data class SignUp(
    @SerializedName("email")
    var email: String,

    @SerializedName("userName")
    var username: String,

    @SerializedName("password")
    var password: String
)