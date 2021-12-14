package com.layanan.jurusan.data.remote.response.announcement

import com.google.gson.annotations.SerializedName
import com.layanan.jurusan.data.model.AnnouncementModel

class DetailAnnouncementResponse(
    @SerializedName("message")
    val message: String,

    @SerializedName("data")
    val data: AnnouncementModel
)