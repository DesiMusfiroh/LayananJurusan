package com.layanan.jurusan.data.remote.response.document

import com.google.gson.annotations.SerializedName
import com.layanan.jurusan.data.model.DocumentModel
import com.layanan.jurusan.data.model.MetaModel

data class ListDocumentResponse(
        @SerializedName("message")
        val message: String,
        @SerializedName("data")
        val data: List<DocumentModel>
)