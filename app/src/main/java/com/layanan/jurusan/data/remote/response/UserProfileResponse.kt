package com.layanan.jurusan.data.remote.response

import com.google.gson.annotations.SerializedName
import com.layanan.jurusan.data.model.UserModel

data class UserProfileResponse(
    @SerializedName("message")
    val message: String,

    @SerializedName("data")
    val data: UserModel
)