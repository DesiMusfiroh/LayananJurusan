package com.layanan.jurusan.data.model

import com.google.gson.annotations.SerializedName

data class MatriksIkuModel(
    @SerializedName("id")
    val id: Int,

    @SerializedName("judul")
    val judul: String?,

    @SerializedName("satuan")
    val satuan: String?,

    @SerializedName("iku")
    val iku: String?,

    @SerializedName("target")
    val target: Double?,

    @SerializedName("capaian")
    val capaian: Double?,

    @SerializedName("kendala")
    val kendala: String?,

    @SerializedName("tindak_lanjut")
    val tindakLanjut: String?,

    @SerializedName("tahun")
    val tahun: Int?,
)