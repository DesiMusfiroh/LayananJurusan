package com.layanan.jurusan.data.remote.api

import com.layanan.jurusan.data.remote.response.CountNotifikasiResponse
import com.layanan.jurusan.data.remote.response.NotifikasiResponse
import com.layanan.jurusan.data.remote.response.ProfileJurusan.ProfilHimpunanResponse
import com.layanan.jurusan.data.remote.response.ProfileJurusan.ProfileJurusanResponse
import com.layanan.jurusan.data.remote.response.ProfileJurusan.ProfileProdiResponse
import com.layanan.jurusan.data.remote.response.SaveFcmTokenResponse
import com.layanan.jurusan.data.remote.response.UserProfileResponse
import com.layanan.jurusan.data.remote.response.announcement.DetailAnnouncementResponse
import com.layanan.jurusan.data.remote.response.announcement.LatestAnnouncementResponse
import com.layanan.jurusan.data.remote.response.announcement.ListAnnouncementResponse
import com.layanan.jurusan.data.remote.response.civitas.*
import com.layanan.jurusan.data.remote.response.document.ListDocumentResponse
import com.layanan.jurusan.data.remote.response.iku.*
import com.layanan.jurusan.data.remote.response.news.LatestNewsResponse
import com.layanan.jurusan.data.remote.response.login.LoginResponse
import com.layanan.jurusan.data.remote.response.login.LogoutResponse
import com.layanan.jurusan.data.remote.response.news.DetailNewsResponse
import com.layanan.jurusan.data.remote.response.news.ListNewsResponse
import com.layanan.jurusan.data.remote.response.surat.*
import com.layanan.jurusan.data.remote.response.userprofile.SignatureResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface Api {
    @FormUrlEncoded
    @POST("login")
    fun login(
            @Field("username") username: String,
            @Field("password") password: String
    ): Call<LoginResponse>

    @GET("latest_news")
    fun getLatestNews(): Call<LatestNewsResponse>

    @GET("news")
    suspend fun getListNews(
            @Query("page") page: Int
    ): Response<ListNewsResponse>

    @GET("news")
    suspend fun getSearchNews(
        @Query("search") search: String,
        @Query("page") page: Int
    ): Response<ListNewsResponse>

    @GET("dokumen")
    fun getListDocument(): Call<ListDocumentResponse>

    @GET("news/{id}")
    fun getDetailNews(@Path("id") id: Int): Call<DetailNewsResponse>

    @GET("latest_announcement")
    fun getLatestAnnouncement(): Call<LatestAnnouncementResponse>

    @GET("announcements")
    suspend fun getListAnnouncement(
            @Query("page") page: Int
    ): Response<ListAnnouncementResponse>

    @GET("announcements/{id}")
    fun getAnnouncement(@Path("id") id: Int): Call<DetailAnnouncementResponse>

    @FormUrlEncoded
    @POST("save_token")
    fun saveToken(
        @Field("fcm_token") fcmToken: String,
        @Header("Authorization") token: String
    ): Call<SaveFcmTokenResponse>

    @GET("profil/jurusan")
    fun getProfileJurusan(): Call<ProfileJurusanResponse>

    @GET("get_user")
    fun getUserProfile(@Header("Authorization") token: String): Call<UserProfileResponse>

    @GET("profil/{name}")
    fun getProfileProdi(@Path("name") name: String): Call<ProfileProdiResponse>

    @GET("profil_himpunan/{name}")
    fun getProfileHimpunan(@Path("name") name: String): Call<ProfilHimpunanResponse>

    @Multipart
    @POST("profile/signature")
    fun uploadSignature(
        @Part signature: MultipartBody.Part,
        @Header("Authorization") token: String
    ): Call<SignatureResponse>

    @GET("iku/iku1/{year}")
    fun getIku1(@Path("year") year: String): Call<Iku1Response>

    @GET("iku/iku2/{year}")
    fun getIku2(@Path("year") year: String): Call<Iku2Response>

    @GET("iku/iku3/{year}")
    fun getIku3(@Path("year") year: String): Call<Iku3Response>

    @GET("iku/iku4/{year}")
    fun getIku4(@Path("year") year: String): Call<Iku4Response>

    @GET("iku/iku5/{year}")
    fun getIku5(@Path("year") year: String): Call<Iku5Response>

    @GET("iku/iku6/{year}")
    fun getIku6(@Path("year") year: String): Call<Iku6Response>

    @GET("iku/iku7/{year}")
    fun getIku7(@Path("year") year: String): Call<Iku7Response>

    @GET("iku/iku8/{year}")
    fun getIku8(@Path("year") year: String): Call<Iku8Response>

    @GET("surat/jenis-surat/{tipe}")
    fun getJenisSurat(@Path("tipe") tipe: String): Call<JenisSuratResponse>

    @GET("surat/keyword/{jenisSuratId}")
    fun getKeywordSurat(@Path("jenisSuratId") jenisSuratId: Int): Call<KeywordSuratResponse>

    @GET("surat/riwayat-surat/mahasiswa")
    fun getRiwayatSurat(@Header("Authorization") token: String): Call<ListRiwayatSuratResponse>

    @GET("surat/riwayat-surat/dosen")
    suspend fun getRiwayatSuratDosen(@Header("Authorization") token: String, @Query("page") page: Int): Response<ListRiwayatSuratDosenResponse>

    @GET("surat/riwayat-surat/mahasiswa/{id}")
    fun showRiwayatSurat(@Header("Authorization") token: String,@Path("id") id: Int): Call<RiwayatSuratResponse>

    @GET("surat/isi-keyword/{riwayatSuratId}")
    fun getIsiKeywordSurat(@Path("riwayatSuratId") riwayatSuratId: Int): Call<IsiKeywordSuratResponse>

    @GET("logout")
    fun logout(@Header("Authorization") token: String): Call<LogoutResponse>


    @GET("angkatan")
    fun getAngkatan(): Call<AngkatanResponse>

    @GET("civitas-akademik/mahasiswa")
    fun getMahasiswa(
            @Query("prodi") prodi: String,
            @Query("angkatan") angkatan: String,
            @Query("status_mahasiswa") status_mahasiswa: String,
    ): Call<MahasiswaResponse>

    @GET("civitas-akademik/mahasiswa")
    fun getSearchMahasiswa(
        @Query("prodi") prodi: String,
        @Query("angkatan") angkatan: String,
        @Query("status_mahasiswa") status_mahasiswa: String,
        @Query("search") search: String
    ): Call<MahasiswaResponse>

    @GET("civitas-akademik/mahasiswa/{id}")
    fun getDetailMahasiswa(
        @Path("id") id: Int
    ): Call<DetailMahasiswaResponse>

    @GET("status-dosen")
    fun getStatusDosen(): Call<StatusDosenResponse>

    @GET("civitas-akademik/dosen")
    fun getDosen(
        @Query("prodi") prodi: String,
        @Query("status") status: String,
    ) : Call<DosenResponse>

    @GET("civitas-akademik/dosen/{id}")
    fun getDetailDosen(
        @Path("id") id: Int
    ) : Call<DetailDosenResponse>

    @GET("civitas-akademik/dosen")
    fun getSearchDosen(
        @Query("prodi") prodi: String,
        @Query("status") status: String,
        @Query("search") search: String
    ): Call<DosenResponse>

    @GET("civitas-akademik/alumni")
    suspend fun getAlumni(
        @Query("prodi") prodi: String,
        @Query("angkatan") angkatan: String,
        @Query("page") page: Int
    ): Response<MahasiswaResponse>

    @GET("civitas-akademik/alumni/{id}")
    fun getDetailAlumni(
        @Path("id") id: Int
    ): Call<DetailMahasiswaResponse>

    @GET("civitas-akademik/alumni")
    fun getSearchAlumni(
        @Query("prodi") prodi: String,
        @Query("angkatan") angkatan: String,
        @Query("search") search: String
    ): Call<MahasiswaResponse>

    @GET("notifikasi")
    fun getNotifikasi(
        @Header("Authorization") token: String
    ): Call<NotifikasiResponse>

    @GET("count-notifikasi")
    fun getCountNotifikasi(
        @Header("Authorization") token: String
    ): Call<CountNotifikasiResponse>

}