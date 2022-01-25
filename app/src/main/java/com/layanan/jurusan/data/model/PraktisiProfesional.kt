package com.layanan.jurusan.data.model

import com.google.gson.annotations.SerializedName

data class PraktisiProfesional(
    @SerializedName("id")
    val id: Int,

    @SerializedName("jenis_pekerjaan")
    val jenisPekerjaan: String?,

    @SerializedName("jenis_perusahaan")
    val jenisPerusahaan: String?,

    @SerializedName("nama_perusahaan")
    val namaPerusahaan: String?,

    @SerializedName("tahun")
    val tahun: String?,

)