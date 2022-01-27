package com.layanan.jurusan.data.model

import com.google.gson.annotations.SerializedName

data class Kolaborator(
    @SerializedName("id")
    val id: Int,

    @SerializedName("tipe_kolaborator")
    val tipeKolaborator: String?,

    @SerializedName("lembaga_kolaborator")
    val lembagaKolaborator: String?,
)