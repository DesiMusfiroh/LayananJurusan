package com.layanan.jurusan.data.remote.response.iku

import com.google.gson.annotations.SerializedName
import com.layanan.jurusan.data.model.Iku6Model
import com.layanan.jurusan.data.model.Iku7Model

data class Iku7Response(
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: List<Iku7Model>,
)