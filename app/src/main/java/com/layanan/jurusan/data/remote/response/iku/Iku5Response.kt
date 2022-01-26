package com.layanan.jurusan.data.remote.response.iku

import com.google.gson.annotations.SerializedName
import com.layanan.jurusan.data.model.Iku5Model

data class Iku5Response(
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: List<Iku5Model>,
)