package com.layanan.jurusan.data.remote.response.surat

import com.google.gson.annotations.SerializedName
import com.layanan.jurusan.data.model.MetaModel
import com.layanan.jurusan.data.model.RiwayatSuratModel
import com.layanan.jurusan.data.model.TujuanSuratModel

class ListRiwayatSuratDosenResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: List<TujuanSuratModel>?,
    @SerializedName("meta")
    val meta: MetaModel,
)