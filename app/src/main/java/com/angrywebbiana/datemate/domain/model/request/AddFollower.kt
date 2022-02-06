package com.angrywebbiana.datemate.domain.model.request

import com.google.gson.annotations.SerializedName

data class AddFollower(
    var auth: String,
    var targetUserSeq: TargetUserSeq
)

data class TargetUserSeq(
    @SerializedName("targetUserSeq")
    var targetUserSeq: Int
)