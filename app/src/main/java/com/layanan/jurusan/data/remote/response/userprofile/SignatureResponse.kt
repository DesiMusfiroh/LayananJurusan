package com.layanan.jurusan.data.remote.response.userprofile

import com.google.gson.annotations.SerializedName

data class SignatureResponse(
    @SerializedName("message")
    val message: String,

    @SerializedName("data")
    val signature: String
)