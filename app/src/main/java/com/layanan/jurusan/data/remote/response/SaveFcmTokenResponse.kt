package com.layanan.jurusan.data.remote.response

import com.google.gson.annotations.SerializedName

data class SaveFcmTokenResponse(
    @SerializedName("message")
    val message: String,

    @SerializedName("data")
    val token: String
)