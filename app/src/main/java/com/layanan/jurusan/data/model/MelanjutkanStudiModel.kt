package com.layanan.jurusan.data.model

import com.google.gson.annotations.SerializedName

data class MelanjutkanStudiModel(
    @SerializedName("id")
    val id: Int,

    @SerializedName("masa_tunggu")
    val masaTunggu: String?,

    @SerializedName("jenjang_prodi_lanjutan")
    val jenjangProdiLanjutan: String?,

    @SerializedName("nama_prodi")
    val namaProdi: String?,

    @SerializedName("nama_perguruan_tinggi")
    val namaPerguruanTinggi: String?,
)