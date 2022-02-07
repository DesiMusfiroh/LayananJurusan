package com.layanan.jurusan.data.remote.response.civitas

import com.google.gson.annotations.SerializedName

class StatusDosenResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: List<String>,
)