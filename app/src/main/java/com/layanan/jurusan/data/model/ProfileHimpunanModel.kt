package com.layanan.jurusan.data.model

import com.google.gson.annotations.SerializedName

class ProfileHimpunanModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("nama")
    val name: String,
    @SerializedName("deskripsi")
    val desc: String,
    @SerializedName("logo")
    val logo: String,
    @SerializedName("visi")
    val vision: String,
    @SerializedName("misi")
    val mission: String,
)