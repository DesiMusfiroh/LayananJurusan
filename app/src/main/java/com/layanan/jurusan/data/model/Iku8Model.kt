package com.layanan.jurusan.data.model

import com.google.gson.annotations.SerializedName

class Iku8Model(
    @SerializedName("id")
    val id: Int,

    @SerializedName("jenjang_prodi")
    val jenjangProdi: String?,

    @SerializedName("nama_prodi")
    val namaProdi: String?,

    val fakultas: String = "Sains dan Teknologi",

    @SerializedName("tipe_lembaga_akreditasi")
    val tipeLembagaAkreditasi: String?,

    @SerializedName("nama_lembaga_akreditasi")
    val namaLembagaAkreditasi: String?,

    @SerializedName("no_sk")
    val noSk: String?,

    @SerializedName("tahun")
    val tahun: String?,

    @SerializedName("bukti")
    val bukti: String?,
)