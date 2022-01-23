package com.layanan.jurusan.data.model

import com.google.gson.annotations.SerializedName

class PrestasiModel(
    @SerializedName("id")
    val id: Int,

    @SerializedName("juara")
    val juara: String?,

    @SerializedName("tipe_lomba")
    val tipeLomba: String?,

    @SerializedName("nama_lomba")
    val namaLomba: String?,

    @SerializedName("penyelenggara")
    val penyelenggara: String?,

    @SerializedName("dosen_pembimbing")
    val dosenPembimbing: String?,
)