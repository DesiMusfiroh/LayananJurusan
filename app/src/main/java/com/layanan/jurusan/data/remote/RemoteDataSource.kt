package com.layanan.jurusan.data.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.layanan.jurusan.data.datasource.ListNewsDataSource
import com.layanan.jurusan.data.datasource.TestNewsDataSource
import com.layanan.jurusan.data.model.AnnouncementModel
import com.layanan.jurusan.data.model.NewsModel
import com.layanan.jurusan.data.remote.api.ApiConfig
import com.layanan.jurusan.data.remote.response.SaveFcmTokenResponse
import com.layanan.jurusan.data.remote.response.announcement.DetailAnnouncementResponse
import com.layanan.jurusan.data.remote.response.announcement.LatestAnnouncementResponse
import com.layanan.jurusan.data.remote.response.news.LatestNewsResponse
import com.layanan.jurusan.data.remote.response.login.LoginDataResponse
import com.layanan.jurusan.data.remote.response.login.LoginResponse
import com.layanan.jurusan.data.remote.response.news.DetailNewsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {
    companion object {
        fun getInstance(): RemoteDataSource {
            return RemoteDataSource()
        }
        private const val TAG = "Remote Data Source"
    }

    fun getUserLogin(username: String, password: String): LiveData<LoginDataResponse> {
        val user: MutableLiveData<LoginDataResponse> = MutableLiveData()
        val client = ApiConfig.getApiService().login(username, password)
        client.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    user.postValue(response.body()?.data)
                    Log.d("HasilResponse",response.body().toString())
                } else {

                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
        return user
    }

    fun getLatestNews(): LiveData<List<NewsModel>> {
        val results = MutableLiveData<List<NewsModel>>()
        ApiConfig.getApiService().getLatestNews().enqueue(object: Callback<LatestNewsResponse> {
            override fun onResponse(call: Call<LatestNewsResponse>, response: Response<LatestNewsResponse>) {
                if (response.isSuccessful) {
                    results.postValue(response.body()?.news)
                } else {
                    Log.e(TAG, "onFailure Response: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<LatestNewsResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
        return results
    }

    fun getDetailNews(id: Int): LiveData<NewsModel>{
        val result = MutableLiveData<NewsModel>()
        ApiConfig.getApiService().getDetailNews(id).enqueue(object : Callback<DetailNewsResponse>{
            override fun onResponse(
                call: Call<DetailNewsResponse>,
                response: Response<DetailNewsResponse>
            ) {
                if (response.isSuccessful){
                    Log.d("HasilResponse",response.body().toString())
                    result.postValue(response.body()!!.data)
                }else{
                    Log.d("Failed","Tidak mendapat data")
                }
            }

            override fun onFailure(call: Call<DetailNewsResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }

        })

        return result
    }

    fun getLatestAnnouncement(): LiveData<List<AnnouncementModel>> {
        val results = MutableLiveData<List<AnnouncementModel>>()
        ApiConfig.getApiService().getLatestAnnouncement().enqueue(object: Callback<LatestAnnouncementResponse> {
            override fun onResponse(call: Call<LatestAnnouncementResponse>, response: Response<LatestAnnouncementResponse>) {
                if (response.isSuccessful) {
                    results.postValue(response.body()?.announcements)
                } else {
                    Log.e(TAG, "onFailure Response: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<LatestAnnouncementResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
        return results
    }

    fun listCharacter() = Pager(PagingConfig(pageSize = 5)) {
        TestNewsDataSource(ApiConfig.getApiService())
    }.flow

    fun getListNews() = Pager(PagingConfig(pageSize = 1)) {
        ListNewsDataSource(ApiConfig.getApiService())
    }.flow

    fun getAnnouncement(id: Int): LiveData<AnnouncementModel>{
        val result = MutableLiveData<AnnouncementModel>()
        ApiConfig.getApiService().getAnnouncement(id).enqueue(object : Callback<DetailAnnouncementResponse>{
            override fun onResponse(
                call: Call<DetailAnnouncementResponse>,
                response: Response<DetailAnnouncementResponse>
            ) {
                if (response.isSuccessful){
                    Log.d("HasilResponse",response.body().toString())
                    result.postValue(response.body()!!.data)
                }else{
                    Log.d("Failed","Tidak mendapat data")
                }
            }

            override fun onFailure(call: Call<DetailAnnouncementResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }

        })

        return result
    }

    fun saveFcmToken(fcmToken: String, jwtToken: String): LiveData<SaveFcmTokenResponse>{
        val result = MutableLiveData<SaveFcmTokenResponse>()
        ApiConfig.getApiService().saveToken(fcmToken,"Bearer ${jwtToken}").enqueue(object : Callback<SaveFcmTokenResponse>{
            override fun onResponse(
                call: Call<SaveFcmTokenResponse>,
                response: Response<SaveFcmTokenResponse>
            ) {
                result.postValue(response.body())
                Log.d("HasilResponse",response.body().toString())
            }

            override fun onFailure(call: Call<SaveFcmTokenResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }

        })
        return result
    }

}