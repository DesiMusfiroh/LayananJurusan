package com.layanan.jurusan.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class RiwayatSuratModel(
    @SerializedName("id")
    val id: Int,

    @SerializedName("jenis_surat")
    val jenisSurat: JenisSuratModel?,

    @SerializedName("jenis_ttd")
    val jenisTtd: String?,

    @SerializedName("status_pengajuan")
    val status: String?,

    @SerializedName("tanggal_pengajuan")
    val tanggalPengajuan: String?,

    @SerializedName("file")
    val file: String?
) : Parcelable