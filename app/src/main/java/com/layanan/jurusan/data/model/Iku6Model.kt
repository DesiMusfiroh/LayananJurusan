package com.layanan.jurusan.data.model

import com.google.gson.annotations.SerializedName

class Iku6Model(
    @SerializedName("id")
    val id: Int,

    @SerializedName("nama_prodi")
    val namaProdi: String,

    @SerializedName("mitra")
    val namaLembaga: Mitra?,

    val fakultas: String = "Sains dan Teknologi",

    @SerializedName("jenis_kegiatan")
    val jenisKegiatan: String?,

    @SerializedName("nama_kegiatan")
    val namaKegiatan: String?,

    @SerializedName("no_pks")
    val noPks: String?,

    @SerializedName("tahun")
    val tahun: String?,

    @SerializedName("bukti")
    val bukti: String?,
)