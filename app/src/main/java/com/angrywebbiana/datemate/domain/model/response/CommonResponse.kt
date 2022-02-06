package com.angrywebbiana.datemate.domain.model.response

import com.google.gson.annotations.SerializedName

data class CommonResponse(
    @SerializedName("responseCode")
    var responseCode: String,

    @SerializedName("message")
    var message: Any,

    @SerializedName("errMsg")
    var errMsg: String?
)