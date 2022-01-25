package com.layanan.jurusan.data.remote.response.iku

import com.google.gson.annotations.SerializedName
import com.layanan.jurusan.data.model.Iku4Model

data class Iku4Response(
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: List<Iku4Model>,
)