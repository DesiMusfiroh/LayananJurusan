package com.layanan.jurusan.data.remote.response.login

import com.google.gson.annotations.SerializedName
import com.layanan.jurusan.data.model.UserModel

data class LoginDataResponse(
    @SerializedName("token")
    val token: String,

    @SerializedName("user")
    val user: UserModel
)