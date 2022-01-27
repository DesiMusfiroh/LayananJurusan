package com.layanan.jurusan.data.model

import com.google.gson.annotations.SerializedName

data class JenisSuratModel(
        @SerializedName("id")
        val id: Int,

        @SerializedName("judul")
        val judul: String?,

        @SerializedName("tipe")
        val tipe: String?
)