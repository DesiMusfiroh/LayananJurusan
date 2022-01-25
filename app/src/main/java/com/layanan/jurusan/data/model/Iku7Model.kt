package com.layanan.jurusan.data.model

import com.google.gson.annotations.SerializedName

class Iku7Model(
    @SerializedName("id")
    val id: Int,

    @SerializedName("jenjang_prodi")
    val jenjangProdi: String?,

    @SerializedName("nama_prodi")
    val namaProdi: String?,

    @SerializedName("fakultas")
    val fakultas: String = "Sains dan Teknologi",

    @SerializedName("mata_kuliah")
    val mataKuliah: String?,

    @SerializedName("sks")
    val sks: String?,

    @SerializedName("metode_pembelajaran")
    val metodePembelajaran: String?,

    @SerializedName("no_sk")
    val noSk: String?,

    @SerializedName("tahun")
    val tahun: String?,

    @SerializedName("bukti")
    val bukti: String?
)