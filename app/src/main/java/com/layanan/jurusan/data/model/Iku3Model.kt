package com.layanan.jurusan.data.model

import com.google.gson.annotations.SerializedName

class Iku3Model(
    @SerializedName("id")
    val id: Int,

    @SerializedName("no_induk")
    val noInduk: String,

    @SerializedName("nama")
    val nama: String,

    @SerializedName("nama_prodi")
    val namaProdi: String,

    val fakultas: String = "Sains dan Teknologi",

    @SerializedName("praktisi")
    val praktisi: PraktisiModel?,

    @SerializedName("kegiatan_tri_dharma")
    val kegiatanTriDharma: KegiatanTriDharmaModel?,

    @SerializedName("bukti")
    val bukti: String?,

    @SerializedName("tahun")
    val tahun: String,
)