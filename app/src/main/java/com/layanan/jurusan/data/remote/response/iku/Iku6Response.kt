package com.layanan.jurusan.data.remote.response.iku

import com.google.gson.annotations.SerializedName
import com.layanan.jurusan.data.model.Iku5Model
import com.layanan.jurusan.data.model.Iku6Model

data class Iku6Response(
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: List<Iku6Model>,
)