package com.layanan.jurusan.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

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

    @SerializedName("tanggal_verifikasi")
    val tanggalVerifikasi: String?,

    @SerializedName("pesan")
    val pesan: String?,

    @SerializedName("user")
    val user: UserModel?,

    @SerializedName("file")
    val file: String?
)