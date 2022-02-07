package com.layanan.jurusan.data.model

import com.google.gson.annotations.SerializedName

data class Mahasiswa(
    @SerializedName("id")
    val id: Int,

    @SerializedName("nama")
    val nama: String,

    @SerializedName("nim")
    val nim: String,

    @SerializedName("angkatan")
    val angkatan: String,

    @SerializedName("prodi")
    val prodi: ProdiModel?,
)