package com.layanan.jurusan.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Document(
    val id: String? = null,
    val user_id: String? = null,
    val category: String? = null,
    val title: String? = null,
    val desc: String? = null,
    val file: String? = null,
    val status: String? = null
) : Parcelable
