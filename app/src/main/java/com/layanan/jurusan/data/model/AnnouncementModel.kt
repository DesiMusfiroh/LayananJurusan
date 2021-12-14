package com.layanan.jurusan.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class AnnouncementModel(
    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("kategori")
    val category: String? = null,

    @SerializedName("judul")
    val title: String? = null,

    @SerializedName("cuplikan")
    val snippet: String? = null,

    @SerializedName("deskripsi")
    val desc: String? = null,

    @SerializedName("image")
    val image: String? = null,

    @SerializedName("file")
    val file: String? = null,

    @SerializedName("expires")
    val expires: String? = null,

    @SerializedName("status")
    val status: String? = null
) : Parcelable
