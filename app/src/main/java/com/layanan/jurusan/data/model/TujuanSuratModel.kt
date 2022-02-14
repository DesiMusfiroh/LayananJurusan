package com.layanan.jurusan.data.model

import com.google.gson.annotations.SerializedName

data class TujuanSuratModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("riwayat_surat_id")
    val riwayatSuratId: Int?,
    @SerializedName("dosen_id")
    val dosenId: Int?,
    @SerializedName("riwayat_surat")
    val riwayatSurat: RiwayatSuratModel?,
)