package com.layanan.jurusan.data.remote.response.announcement

import com.google.gson.annotations.SerializedName
import com.layanan.jurusan.data.model.AnnouncementModel
import com.layanan.jurusan.data.model.MetaModel

data class ListAnnouncementResponse (
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val announcement: List<AnnouncementModel>,
    @SerializedName("meta")
    val meta: MetaModel,
)