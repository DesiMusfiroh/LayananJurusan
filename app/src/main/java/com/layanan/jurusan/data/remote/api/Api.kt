package com.layanan.jurusan.data.remote.api

import com.layanan.jurusan.data.remote.response.ResponseApi
import com.layanan.jurusan.data.remote.response.news.LatestNewsResponse
import com.layanan.jurusan.data.remote.response.login.LoginResponse
import com.layanan.jurusan.data.remote.response.news.DetailNewsResponse
import com.layanan.jurusan.data.remote.response.news.ListNewsResponse
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
}