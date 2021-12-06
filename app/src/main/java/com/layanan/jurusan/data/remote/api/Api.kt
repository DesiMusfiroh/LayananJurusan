package com.layanan.jurusan.data.remote.api

import com.layanan.jurusan.data.remote.response.news.LatestNewsResponse
import com.layanan.jurusan.data.remote.response.login.LoginResponse
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

    @GET("news")
    fun getLatestNews(): Call<LatestNewsResponse>

    @GET("news")
    suspend fun getListNews(
            @Field("page") page: Int,
            @Field("limit") limit: Int
    ): Response<ListNewsResponse>
}