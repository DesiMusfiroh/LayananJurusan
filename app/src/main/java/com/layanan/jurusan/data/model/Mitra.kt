package com.layanan.jurusan.data.model

import com.google.gson.annotations.SerializedName

data class Mitra(
    @SerializedName("id")
    val id: Int,

    @SerializedName("nama")
    val nama: String?,

    @SerializedName("tipe_mitra")
    val tipeMitra: String?,

    @SerializedName("logo")
    val logo: String?,

    @SerializedName("link")
    val link: String?,
)