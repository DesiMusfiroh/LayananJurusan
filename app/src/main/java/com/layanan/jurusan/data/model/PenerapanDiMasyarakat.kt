package com.layanan.jurusan.data.model

import com.google.gson.annotations.SerializedName

class PenerapanDiMasyarakat(
    @SerializedName("id")
    val id: Int,

    @SerializedName("jenis_lembaga")
    val jenisLembaga: String?,

    @SerializedName("nama_lembaga")
    val namaLembaga: String?,

    @SerializedName("no_pks")
    val noPks: String?,


)