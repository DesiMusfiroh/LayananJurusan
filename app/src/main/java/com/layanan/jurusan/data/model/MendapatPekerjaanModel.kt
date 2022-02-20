package com.layanan.jurusan.data.model

import com.google.gson.annotations.SerializedName

data class MendapatPekerjaanModel(
    @SerializedName("id")
    val id: Int,

    @SerializedName("masa_tunggu")
    val masaTunggu: String?,

    @SerializedName("kab_kota_tempat_kerja")
    val kabKotaTempatKerja: String?,

    @SerializedName("umk")
    val umk: Integer?,

    @SerializedName("penghasilan")
    val penghasilan: Int?,

    @SerializedName("presentase_dari_umk")
    val presentaseDariUmk: Double?,

    @SerializedName("kriteria_perusahaan")
    val kriteriaPerusahaan: String?,

    @SerializedName("nama_perusahaan")
    val namaPerusahaan: String  ?,

    @SerializedName("perjanjian_kerja")
    val perjanjianKerja: String?,

    @SerializedName("izin_usaha")
    val izinUsaha: String?,
)