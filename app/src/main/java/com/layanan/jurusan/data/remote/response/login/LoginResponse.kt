package com.layanan.jurusan.data.remote.response.login

import com.google.gson.annotations.SerializedName
import com.layanan.jurusan.data.model.UserModel

data class LoginResponse(
    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("data")
    val data: LoginDataResponse
)