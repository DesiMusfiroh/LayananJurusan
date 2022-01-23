package com.layanan.jurusan.data.model

import com.google.gson.annotations.SerializedName

data class WiraswastaModel(
    @SerializedName("id")
    val id: Int,

    @SerializedName("masa_tunggu")
    val masaTunggu: String,

    @SerializedName("kab_kota_tempat_kerja")
    val kabKotaTempatKerja: String?,

    @SerializedName("umk")
    val umk: Integer?,

    @SerializedName("penghasilan")
    val penghasilan: Int?,

    @SerializedName("presentase_dari_umk")
    val presentaseDariUmk: Int?,

    @SerializedName("kriteria_kewiraswastaan")
    val kriteriaKewiraswastaan: String?,

    @SerializedName("kriteria_usaha")
    val kriteriaUsaha: String?,

    @SerializedName("nama_usaha")
    val namaUsaha: String?,

    @SerializedName("izin_usaha")
    val izinUsaha: String?,
)