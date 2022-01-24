package com.layanan.jurusan.data.model

import com.google.gson.annotations.SerializedName

data class PengalamanLuarKampusModel(
    @SerializedName("id")
    val id: Int,

    @SerializedName("jenis_kegiatan")
    val jenisKegiatan: String?,

    @SerializedName("nama_tempat_kegiatan")
    val namaTempat: String?,

    @SerializedName("dosen_pembimbing")
    val dosenPembimbing: String?,

    @SerializedName("no_sk")
    val noSk: String?,

    @SerializedName("sks")
    val sks: String?
)