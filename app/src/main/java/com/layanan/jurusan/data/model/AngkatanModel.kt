package com.layanan.jurusan.data.model

import com.google.gson.annotations.SerializedName

data class AngkatanModel(
        @SerializedName("angkatan")
        val angkatan: String? = null,
)