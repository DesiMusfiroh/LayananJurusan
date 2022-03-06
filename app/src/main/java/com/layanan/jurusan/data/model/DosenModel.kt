package com.layanan.jurusan.data.model

import com.google.gson.annotations.SerializedName

class DosenModel(
    @SerializedName("id")
    val id: Int,

    @SerializedName("nama")
    val nama: String?,

    @SerializedName("no_induk")
    val noInduk: String?,

    @SerializedName("nama_gelar")
    val namaGelar: String?,

    @SerializedName("status")
    val status: String?,

    @SerializedName("ttd_digital")
    val ttdDigital: String?,

    @SerializedName("prodi")
    val prodi: ProdiModel?,

    @SerializedName("kegiatan_tri_dharma")
    val kegiatanTriDharma: List<KegiatanTriDharmaModel>?,

    @SerializedName("praktisi")
    val praktisi: List<PraktisiModel>?,

    @SerializedName("prestasi_membimbing_mahasiswa")
    val prestasiMembimbingMahasiswa: List<PrestasiMembimbingMahasiswa>?,

    @SerializedName("sertifikat_kompetensi")
    val sertifikatKompetensi: List<SertifikatKompetensi>?,

    @SerializedName("hasil_kerja_dosen")
    val hasilKerjaDosen: List<HasilKerjaDosen>?,

    @SerializedName("praktisi_profesional")
    val praktisiProfesional: List<PraktisiProfesional>?,
)