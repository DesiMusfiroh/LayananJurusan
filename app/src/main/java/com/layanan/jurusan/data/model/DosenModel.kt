package com.layanan.jurusan.data.model

import com.google.gson.annotations.SerializedName

class DosenModel(
    @SerializedName("id")
    val id: Int,

    @SerializedName("nama")
    val nama: String?,

    @SerializedName("no_induk")
    val noInduk: String?,

    @SerializedName("nama_gelar")
    val namaGelar: String?,

    @SerializedName("status")
    val status: String?,

    @SerializedName("ttd_digital")
    val ttdDigital: String?,

    @SerializedName("prodi")
    val prodi: ProdiModel?,
)