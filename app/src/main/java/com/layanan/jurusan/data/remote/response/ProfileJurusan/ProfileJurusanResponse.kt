package com.layanan.jurusan.data.remote.response.ProfileJurusan

import com.google.gson.annotations.SerializedName
import com.layanan.jurusan.data.model.ProfileJurusanModel

data class ProfileJurusanResponse(
    @SerializedName("message")
    val message: String,

    @SerializedName("data")
    val data: ProfileJurusanModel

    )