package com.layanan.jurusan.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class JenisSuratModel(
        @SerializedName("id")
        val id: Int,

        @SerializedName("judul")
        val judul: String?,

        @SerializedName("tipe")
        val tipe: String?,

        @SerializedName("file")
        val file: String?
) : Parcelable