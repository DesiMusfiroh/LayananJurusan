package com.layanan.jurusan.data.model

import com.google.gson.annotations.SerializedName

class ProdiModel(
    @SerializedName("id")
    val id: Int,

    @SerializedName("kode")
    val kode: String?,

    @SerializedName("nama")
    val nama: String?,

    @SerializedName("jenjang")
    val jenjang: String?,

    @SerializedName("deskripsi")
    val deskripsi: String?,

    @SerializedName("logo")
    val logo: String?,

    @SerializedName("image_struktur_organisasi")
    val imageStrukturOrganisasi: String?,

    @SerializedName("visi")
    val visi: String?,

    @SerializedName("misi")
    val misi: String?,

    @SerializedName("tujuan")
    val tujuan: String?,

    @SerializedName("alamat")
    val alamat: String?,

    @SerializedName("email")
    val email: String?,

    @SerializedName("telepon")
    val telepon: String?,

    @SerializedName("link")
    val link: String?,
)