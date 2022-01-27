package com.layanan.jurusan.data.model

import com.google.gson.annotations.SerializedName

data class Iku5Model(
    @SerializedName("id")
    val id: Int,

    @SerializedName("nama")
    val nama: String,

    @SerializedName("no_induk")
    val noInduk: String,

    @SerializedName("hasil_kerja_dosen")
    val hasilKerjaDosen: HasilKerjaDosen?,

    @SerializedName("penerapan_di_masyarakat")
    val penerapanDiMasyarakat: PenerapanDiMasyarakat?,

    @SerializedName("kolaborator")
    val kolaborator: Kolaborator?,

    @SerializedName("karya_terapan")
    val karyaTerapan: KaryaTerapan?,

    @SerializedName("karya_seni")
    val karyaSeni: KaryaSeni?,

    @SerializedName("tahun")
    val tahun: String,

    @SerializedName("bukti")
    val bukti: String?,
)