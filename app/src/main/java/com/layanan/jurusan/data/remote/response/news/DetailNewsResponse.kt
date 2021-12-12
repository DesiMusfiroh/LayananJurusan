package com.layanan.jurusan.data.remote.response.news

import com.google.gson.annotations.SerializedName
import com.layanan.jurusan.data.model.NewsModel

data class DetailNewsResponse(
    @SerializedName("message")
    val message: String,

    @SerializedName("data")
    val data: NewsModel
)