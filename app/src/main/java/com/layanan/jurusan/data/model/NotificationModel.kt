package com.layanan.jurusan.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class NotificationModel(
    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("kategori")
    val category: String? = null,

    @SerializedName("title")
    val title: String? = null,

    @SerializedName("desc")
    val desc: String? = null,

    @SerializedName("datetime")
    val datetime: String? = null,

    @SerializedName("link")
    val link: String? = null,
) : Parcelable