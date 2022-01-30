package com.layanan.jurusan.data.model

import com.google.gson.annotations.SerializedName

data class IsiKeywordSuratModel(
    @SerializedName("id")
    val id: Int,

    @SerializedName("riwayat_surat_id")
    val riwayatSuratId: Int,

    @SerializedName("keyword_surat")
    val keywordSurat: KeywordSuratModel,

    @SerializedName("value")
    val keyword: String?,

)