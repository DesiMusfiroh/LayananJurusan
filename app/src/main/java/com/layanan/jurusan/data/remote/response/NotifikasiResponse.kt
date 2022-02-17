package com.layanan.jurusan.data.remote.response

import com.google.gson.annotations.SerializedName
import com.layanan.jurusan.data.model.JenisSuratModel
import com.layanan.jurusan.data.model.NotifikasiModel

data class NotifikasiResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: List<NotifikasiModel>,
)