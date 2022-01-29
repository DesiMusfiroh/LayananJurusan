package com.layanan.jurusan.data.model

import com.google.gson.annotations.SerializedName

data class KeywordSuratModel(
    @SerializedName("id")
    val id: Int,

    @SerializedName("jenis_surat_id")
    val jenisSuratId: Int,

    @SerializedName("nama")
    val nama: String?,

    @SerializedName("keyword")
    val keyword: String?,

    @SerializedName("tipe")
    val tipe: String?,
)