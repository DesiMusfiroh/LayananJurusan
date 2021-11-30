package com.layanan.jurusan.data.model

import com.google.gson.annotations.SerializedName

data class UserModel (
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("username")
    val username: String,

    @field:SerializedName("nomor_induk")
    val nomor_induk: String,

    @field:SerializedName("role")
    val role: Int,
)