package com.layanan.jurusan.data.remote.response.surat

import com.google.gson.annotations.SerializedName
import com.layanan.jurusan.data.model.Iku2Model
import com.layanan.jurusan.data.model.JenisSuratModel

data class JenisSuratResponse(
        @SerializedName("message")
        val message: String,
        @SerializedName("data")
        val data: List<JenisSuratModel>,
)