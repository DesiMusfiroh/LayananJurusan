package com.layanan.jurusan.data.remote.response.civitas

import com.google.gson.annotations.SerializedName
import com.layanan.jurusan.data.model.Mahasiswa

class DetailMahasiswaResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: Mahasiswa
)