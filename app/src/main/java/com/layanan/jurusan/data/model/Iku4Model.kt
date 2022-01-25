package com.layanan.jurusan.data.model

import com.google.gson.annotations.SerializedName

data class Iku4Model(
    @SerializedName("id")
    val id: Int,

    @SerializedName("no_induk")
    val noInduk: String,

    @SerializedName("nama")
    val nama: String,

    @SerializedName("nama_prodi")
    val namaProdi: String,

    val fakultas: String = "Sains dan Teknologi",

    @SerializedName("berkualifikasi_s3")
    val berkualifikasiS3: BerkualifikasiS3?,

    @SerializedName("sertifikat_kompetensi")
    val sertifikatKompetensi: SertifikatKompetensi?,

    @SerializedName("praktisi_profesional")
    val praktisiProfesional: PraktisiProfesional?,

    @SerializedName("tahun")
    val tahun: String,

    @SerializedName("bukti")
    val bukti: String?,

)