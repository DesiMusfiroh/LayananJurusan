package com.layanan.jurusan.data.remote.response

import com.google.gson.annotations.SerializedName
import com.layanan.jurusan.data.model.NotifikasiModel

class CountNotifikasiResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: Int,
)