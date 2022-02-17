package com.layanan.jurusan.data.model

import com.google.gson.annotations.SerializedName

data class NotifikasiModel(
    @SerializedName("id")
    val id: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("message")
    val message: String,

    @SerializedName("created_at")
    val createdAd: String,
)