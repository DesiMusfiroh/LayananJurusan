package com.layanan.jurusan.data.model

import com.google.gson.annotations.SerializedName

class PrestasiMembimbingMahasiswa(
    @SerializedName("id")
    val id: Int,

    @SerializedName("juara")
    val juara: String?,

    @SerializedName("tipe_lomba")
    val tipeLomba: String?,

    @SerializedName("nama_lomba")
    val namaLomba: String?,

    @SerializedName("nama_mahasiswa")
    val namaMahasiswa: String?,

    @SerializedName("nim_mahasiswa")
    val nimMahasiswa: String?
)