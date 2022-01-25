package com.layanan.jurusan.data.remote.api

import com.layanan.jurusan.data.remote.response.ProfileJurusan.ProfileJurusanResponse
import com.layanan.jurusan.data.remote.response.ProfileJurusan.ProfileProdiResponse
import com.layanan.jurusan.data.remote.response.ResponseApi
import com.layanan.jurusan.data.remote.response.SaveFcmTokenResponse
import com.layanan.jurusan.data.remote.response.UserProfileResponse
import com.layanan.jurusan.data.remote.response.announcement.DetailAnnouncementResponse
import com.layanan.jurusan.data.remote.response.announcement.LatestAnnouncementResponse
import com.layanan.jurusan.data.remote.response.announcement.ListAnnouncementResponse
import com.layanan.jurusan.data.remote.response.iku.Iku1Response
import com.layanan.jurusan.data.remote.response.iku.Iku2Response
import com.layanan.jurusan.data.remote.response.iku.Iku3Response
import com.layanan.jurusan.data.remote.response.iku.Iku4Response
import com.layanan.jurusan.data.remote.response.news.LatestNewsResponse
import com.layanan.jurusan.data.remote.response.login.LoginResponse
import com.layanan.jurusan.data.remote.response.news.DetailNewsResponse
import com.layanan.jurusan.data.remote.response.news.ListNewsResponse
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

    @GET("news")
    suspend fun getTestNews(
        @Query("page") page: Int
    ): Response<ListNewsResponse>

    @GET("news/{id}")
    fun getDetailNews(@Path("id") id: Int): Call<DetailNewsResponse>


    @GET("character")
    suspend fun getAllCharacters(
        // @Query("count") size:Int,
        @Query("page") page: Int

    ): Response<ResponseApi>

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

//    @GET("iku/iku5/{year}")
//    fun getIku5(@Path("year") year: String): Call<Iku5Response>
//
//    @GET("iku/iku6/{year}")
//    fun getIku6(@Path("year") year: String): Call<Iku6Response>
//
//    @GET("iku/iku7/{year}")
//    fun getIku7(@Path("year") year: String): Call<Iku7Response>
//
//    @GET("iku/iku8/{year}")
//    fun getIku8(@Path("year") year: String): Call<Iku8Response>

}