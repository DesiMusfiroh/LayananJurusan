package com.layanan.jurusan.data.remote.response.iku

import com.google.gson.annotations.SerializedName
import com.layanan.jurusan.data.model.Iku1Model
import com.layanan.jurusan.data.model.Iku2Model

data class Iku2Response(
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: List<Iku2Model>,
)