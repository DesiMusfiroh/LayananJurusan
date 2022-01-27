package com.layanan.jurusan.data.remote.response.iku

import com.google.gson.annotations.SerializedName
import com.layanan.jurusan.data.model.Iku7Model
import com.layanan.jurusan.data.model.Iku8Model

data class Iku8Response(
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: List<Iku8Model>,
)