package com.layanan.jurusan.data.model

import com.google.gson.annotations.SerializedName

data class KaryaSeni(
    @SerializedName("id")
    val id: Int,

    @SerializedName("tipe_pendanaan")
    val tipePendanaan: String?,

    @SerializedName("nama_lembaga")
    val namaLembaga: String?,

    @SerializedName("kriteria_luaran")
    val kriteriaLuaran: String?,

    @SerializedName("metode_berkarya")
    val metodeBerkarya: String?,

    @SerializedName("studi_kasus")
    val studiKasus: String?,

    @SerializedName("substantial_review")
    val substantialReview: String?,

    @SerializedName("link_review")
    val linkReview: String?,
)