package com.layanan.jurusan.data.model

import com.google.gson.annotations.SerializedName

data class Iku1Model(
    @SerializedName("id")
    val id: Int,

    @SerializedName("nim")
    val nim: String?,

    @SerializedName("nama")
    val nama: String?,

    @SerializedName("jenjang_prodi")
    val jenjangProdi: String?,

    @SerializedName("nama_prodi")
    val namaProdi: String?,

    @SerializedName("tahun")
    val tahun: String?,

    @SerializedName("no_ijazah")
    val noIjazah: String?,

    @SerializedName("tanggal_ijazah")
    val tanggalIjazah: String?,

    @SerializedName("bukti")
    val bukti: String?,

    @SerializedName("mendapat_pekerjaan")
    val mendapatPekerjaan: MendapatPekerjaanModel?,

    @SerializedName("wiraswasta")
    val wiraswasta: WiraswastaModel?,

    @SerializedName("melanjutkan_studi")
    val melanjutkanStudi: MelanjutkanStudiModel?,
)