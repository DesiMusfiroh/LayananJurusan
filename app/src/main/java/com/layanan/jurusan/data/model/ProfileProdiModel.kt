package com.layanan.jurusan.data.model

import com.google.gson.annotations.SerializedName

class ProfileProdiModel(
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
    @SerializedName("alamat")
    val address: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("telepon")
    val phone: String
)