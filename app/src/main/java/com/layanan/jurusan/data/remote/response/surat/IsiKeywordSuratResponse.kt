package com.layanan.jurusan.data.remote.response.surat

import com.google.gson.annotations.SerializedName
import com.layanan.jurusan.data.model.IsiKeywordSuratModel

class IsiKeywordSuratResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: List<IsiKeywordSuratModel>,
)