package com.layanan.jurusan.data.model

import com.google.gson.annotations.SerializedName

data class KaryaTerapan(
    @SerializedName("id")
    val id: Int,

    @SerializedName("tipe_penghargaan")
    val tipePenghargaan: String?,

    @SerializedName("pemberi_penghargaan")
    val pemberiPenghargaan: String?
)