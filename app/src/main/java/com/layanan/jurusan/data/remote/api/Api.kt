package com.layanan.jurusan.data.remote.api

import com.layanan.jurusan.data.remote.response.ListNewsResponse
import com.layanan.jurusan.data.remote.response.login.LoginResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface Api {
    @FormUrlEncoded
    @POST("login")
    fun login(
            @Field("username") username: String,
            @Field("password") password: String
    ) : Call<LoginResponse>

    @GET("")
    fun getLatestNews(): Call<ListNewsResponse>
}