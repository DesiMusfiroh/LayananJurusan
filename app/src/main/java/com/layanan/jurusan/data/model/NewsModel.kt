package com.layanan.jurusan.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsModel(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("user_id")
    val user_id: String? = null,
    @SerializedName("kategori")
    val category: String? = null,
    @SerializedName("judul")
    val title: String? = null,
    @SerializedName("deskripsi")
    val desc: String? = null,
    @SerializedName("image")
    val image: String? = null,
    @SerializedName("file")
    val file: String? = null,
    @SerializedName("status")
    val status: String? = null
) : Parcelable