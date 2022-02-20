package com.layanan.jurusan.data.model

import com.google.gson.annotations.SerializedName

data class Mahasiswa(
    @SerializedName("id")
    val id: Int,

    @SerializedName("nama")
    val nama: String,

    @SerializedName("nim")
    val nim: String,

    @SerializedName("angkatan")
    val angkatan: String,

    @SerializedName("status_kuliah")
    val statusKuliah: String,

    @SerializedName("ttd_digital")
    val ttdDigital: String,

    @SerializedName("dosen")
    val dosen: DosenModel,

    @SerializedName("prodi")
    val prodi: ProdiModel?,

    @SerializedName("prestasi")
    val prestasi: List<PrestasiModel>?,

    @SerializedName("pengalaman_luar_kampus")
    val pengalamanLuarKampus: List<PengalamanLuarKampusModel>?
)