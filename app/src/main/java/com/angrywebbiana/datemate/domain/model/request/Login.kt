package com.angrywebbiana.datemate.domain.model.request

import com.google.gson.annotations.SerializedName

data class Login(
    @SerializedName("email")
    var email: String,

    @SerializedName("password")
    var password: String
)