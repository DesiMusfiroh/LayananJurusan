package com.layanan.jurusan.data.remote.response.civitas

import com.google.gson.annotations.SerializedName
import com.layanan.jurusan.data.model.Mahasiswa
import com.layanan.jurusan.data.model.MetaModel

class MahasiswaResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: List<Mahasiswa>,
    @SerializedName("meta")
    val meta: MetaModel,
)