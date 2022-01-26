package com.layanan.jurusan.data.model

import com.google.gson.annotations.SerializedName

data class HasilKerjaDosen(
    @SerializedName("id")
    val id: Int,

    @SerializedName("jenis_kegiatan")
    val jenisKegiatan: String?,

    @SerializedName("judul")
    val judul: String?,

    @SerializedName("luaran_hasil")
    val luaranHasil: String?,

    @SerializedName("link")
    val link: String?,

    @SerializedName("jumlah_kutipan")
    val jumlahKutipan: String?,

    @SerializedName("sitasi_i10_index")
    val sitasiI10Index: String?
)