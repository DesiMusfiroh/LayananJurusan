package com.layanan.jurusan.data.model

import com.google.gson.annotations.SerializedName

data class Iku2Model(
    @SerializedName("id")
    val id: Int,

    @SerializedName("nim")
    val nim: String?,

    @SerializedName("nama")
    val nama: String?,

    @SerializedName("jenjang_prodi")
    val jenjangProdi: String?,

    @SerializedName("nama_prodi")
    val namaProdi: String?,

    @SerializedName("bukti")
    val bukti: String?,

    @SerializedName("pengalaman_luar_kampus")
    val pengalamanLuarKampus: PengalamanLuarKampusModel?,

    @SerializedName("prestasi")
    val prestasi: PrestasiModel?,
)