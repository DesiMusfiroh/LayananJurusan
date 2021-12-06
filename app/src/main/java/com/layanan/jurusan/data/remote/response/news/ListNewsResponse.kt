package com.layanan.jurusan.data.remote.response.news

import com.google.gson.annotations.SerializedName
import com.layanan.jurusan.data.model.MetaModel
import com.layanan.jurusan.data.model.NewsModel

data class ListNewsResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val news: MutableList<NewsModel>,
    @SerializedName("meta")
    val meta: MetaModel,
)