package com.layanan.jurusan.data.model

import com.google.gson.annotations.SerializedName

data class ProfileJurusanModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("nama")
    val name: String,
    @SerializedName("deskripsi")
    val desc: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("visi")
    val vision: String,
    @SerializedName("misi")
    val mission: String,
    @SerializedName("alamat")
    val address: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("telepon")
    val phone: String
    )