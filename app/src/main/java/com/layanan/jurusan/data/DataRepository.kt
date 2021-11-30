package com.layanan.jurusan.data

import androidx.lifecycle.LiveData
import com.layanan.jurusan.data.model.NewsModel
import com.layanan.jurusan.data.remote.RemoteDataSource
import com.layanan.jurusan.data.remote.response.login.LoginDataResponse

class DataRepository private constructor(
        private val remoteDataSource: RemoteDataSource,
) {
    companion object {
        @Volatile
        private var instance: DataRepository? = null

        fun getInstance(remoteData: RemoteDataSource): DataRepository =
                instance ?: synchronized(this) {
                    instance ?: DataRepository(remoteData).apply {
                        instance = this
                    }
                }
    }
    fun getLatestNews(): LiveData<List<NewsModel>> = remoteDataSource.getLatestNews()

    fun login(username: String,password: String): LiveData<LoginDataResponse> = remoteDataSource.getUserLogin(username,password)
}