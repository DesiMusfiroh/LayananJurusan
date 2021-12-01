package com.layanan.jurusan.data.remote.response.news

import com.google.gson.annotations.SerializedName
import com.layanan.jurusan.data.model.NewsModel

data class ListNewsResponse(
    @SerializedName("data")
    val myData: List<NewsModel>,
    @SerializedName("page")
    val page: Int,
    @SerializedName("per_page")
    val perPage: Int,
    @SerializedName("total")
    val total: Int,
    @SerializedName("total_pages")
    val totalPages: Int
)