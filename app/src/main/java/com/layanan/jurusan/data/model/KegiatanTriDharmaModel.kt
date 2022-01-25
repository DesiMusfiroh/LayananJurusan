package com.layanan.jurusan.data.model

import com.google.gson.annotations.SerializedName

class KegiatanTriDharmaModel(
    @SerializedName("id")
    val id: Int,

    @SerializedName("jenis_kegiatan")
    val jenisKegiatan: String?,

    @SerializedName("tahun")
    val tahun: String,

    @SerializedName("nama_kegiatan")
    val namaKegiatan: String,

    @SerializedName("tipe_kampus")
    val tipeKampus: String,

    @SerializedName("nama_kampus")
    val namaKampus: String?,

    @SerializedName("no_sk")
    val noSk: String?,

    )