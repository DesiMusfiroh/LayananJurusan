package com.layanan.jurusan.data.remote.response.ProfileJurusan

import com.google.gson.annotations.SerializedName
import com.layanan.jurusan.data.model.ProfileHimpunanModel

class ProfilHimpunanResponse(
    @SerializedName("message")
    val message: String,

    @SerializedName("data")
    val data: ProfileHimpunanModel
)