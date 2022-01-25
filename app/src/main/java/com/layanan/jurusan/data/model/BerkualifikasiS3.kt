package com.layanan.jurusan.data.model

import com.google.gson.annotations.SerializedName

data class BerkualifikasiS3(
    @SerializedName("id")
    val id: Int,

    @SerializedName("prodi_lulus")
    val prodiLulus: String?,

    @SerializedName("pt_lulus")
    val ptLulus: String?,

    @SerializedName("nama_pt")
    val namaPt: String?,

    @SerializedName("no_ijazah")
    val noIjazah: String?,

    @SerializedName("tahun")
    val tahun: String?,


)