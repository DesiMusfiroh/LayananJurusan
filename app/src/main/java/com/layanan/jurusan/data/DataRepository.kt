package com.layanan.jurusan.data

import androidx.lifecycle.LiveData
import com.layanan.jurusan.data.model.AnnouncementModel
import com.layanan.jurusan.data.model.NewsModel
import com.layanan.jurusan.data.remote.RemoteDataSource
import com.layanan.jurusan.data.remote.response.SaveFcmTokenResponse
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

    fun getLatestAnnouncement(): LiveData<List<AnnouncementModel>> = remoteDataSource.getLatestAnnouncement()

    fun getTestNews() = remoteDataSource.getListNews()

    fun getListNews() = remoteDataSource.getListNews()

    fun getListAnnouncement() = remoteDataSource.getListAnnouncement()

    fun login(username: String,password: String): LiveData<LoginDataResponse> = remoteDataSource.getUserLogin(username,password)

    fun getDetailNews(id: Int): LiveData<NewsModel> = remoteDataSource.getDetailNews(id)

    fun listCharacter() = remoteDataSource.listCharacter()

    fun getAnnouncement(id: Int): LiveData<AnnouncementModel> = remoteDataSource.getAnnouncement(id)

    fun saveFcmToken(fcmToken: String, jwtToken: String): LiveData<SaveFcmTokenResponse> = remoteDataSource.saveFcmToken(fcmToken,jwtToken)

    fun getSearchNews(search: String) = remoteDataSource.getSearchNews(search)

    fun getProfileJurusan() = remoteDataSource.getProfileJurusan()

    fun getUserProfile(jwtToken: String) = remoteDataSource.getUserProfile(jwtToken)
}