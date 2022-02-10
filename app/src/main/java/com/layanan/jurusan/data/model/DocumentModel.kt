package com.layanan.jurusan.data.model

import com.google.gson.annotations.SerializedName

data class DocumentModel(
        @SerializedName("id")
        val id: Int? = null,
        @SerializedName("user_id")
        val user_id: String? = null,
        @SerializedName("kategori")
        val category: String? = null,
        @SerializedName("judul")
        val title: String? = null,
        @SerializedName("deskripsi")
        val desc: String? = null,
        @SerializedName("file")
        val file: String? = null,
        @SerializedName("status")
        val status: String? = null,
        @SerializedName("created_at")
        val published_at: String? = null,
)