package com.layanan.jurusan.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.layanan.jurusan.data.datasource.TestNewsDataSource
import com.layanan.jurusan.data.model.AnnouncementModel
import com.layanan.jurusan.data.model.NewsModel
import com.layanan.jurusan.data.remote.RemoteDataSource
import com.layanan.jurusan.data.remote.api.ApiConfig
import com.layanan.jurusan.data.remote.response.login.LoginDataResponse
import kotlinx.coroutines.flow.Flow

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

    fun getLatestAnnouncement(): LiveData<List<AnnouncementModel>> = remoteDataSource.getLatestAnnouncement()

    fun getTestNews() = remoteDataSource.getListNews()

    fun getListNews() = remoteDataSource.getListNews()

    fun getListAnnouncement() = remoteDataSource.getListAnnouncement()

    fun login(username: String,password: String): LiveData<LoginDataResponse> = remoteDataSource.getUserLogin(username,password)

    fun getDetailNews(id: Int): LiveData<NewsModel> = remoteDataSource.getDetailNews(id)

    fun listCharacter() = remoteDataSource.listCharacter()
}