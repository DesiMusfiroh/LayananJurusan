package com.layanan.jurusan.data.model

import com.google.gson.annotations.SerializedName

data class SertifikatKompetensi(
    @SerializedName("id")
    val id: Int,

    @SerializedName("jenis_sertifikat")
    val jenisSertifikat: String?,

    @SerializedName("nomor_sertifikat")
    val nomorSertifikat: String?,

    @SerializedName("nama_lembaga")
    val namaLembaga: String?,

    @SerializedName("tahun")
    val tahun: String?,
)