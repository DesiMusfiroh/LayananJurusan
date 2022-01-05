package com.layanan.jurusan.data.remote.response.ProfileJurusan

import com.google.gson.annotations.SerializedName
import com.layanan.jurusan.data.model.ProfileJurusanModel
import com.layanan.jurusan.data.model.ProfileProdiModel

class ProfileProdiResponse(
    @SerializedName("message")
    val message: String,

    @SerializedName("data")
    val data: ProfileProdiModel
)