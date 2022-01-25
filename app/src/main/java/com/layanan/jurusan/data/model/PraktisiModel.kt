package com.layanan.jurusan.data.model

import com.google.gson.annotations.SerializedName

class PraktisiModel(
    @SerializedName("id")
    val id: Int,

    @SerializedName("jenis_pekerjaan")
    val jenisPekerjaan: String?,

    @SerializedName("jenis_perusahaan")
    val jenisPerusahaan: String?,

    @SerializedName("tahun")
    val tahun: String?,

    @SerializedName("persetujuan")
    val persetujuan: String?,
)