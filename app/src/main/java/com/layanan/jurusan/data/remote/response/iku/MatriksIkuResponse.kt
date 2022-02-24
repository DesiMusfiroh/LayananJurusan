package com.layanan.jurusan.data.remote.response.iku

import com.google.gson.annotations.SerializedName
import com.layanan.jurusan.data.model.Iku8Model
import com.layanan.jurusan.data.model.MatriksIkuModel

data class MatriksIkuResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: List<MatriksIkuModel>,
)