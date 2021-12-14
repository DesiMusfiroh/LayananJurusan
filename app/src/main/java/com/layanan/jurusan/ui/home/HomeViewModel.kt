package com.layanan.jurusan.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.layanan.jurusan.data.DataRepository
import com.layanan.jurusan.data.model.AnnouncementModel
import com.layanan.jurusan.data.model.NewsModel
import com.layanan.jurusan.data.remote.response.SaveFcmTokenResponse

class HomeViewModel(private val repository: DataRepository) : ViewModel() {
    fun getLatestNews() : LiveData<List<NewsModel>> = repository.getLatestNews()

    fun getLatestAnnouncement() : LiveData<List<AnnouncementModel>> = repository.getLatestAnnouncement()

    fun saveFcmToken(fcmToken: String, jwtToken: String): LiveData<SaveFcmTokenResponse> = repository.saveFcmToken(fcmToken, jwtToken)
}