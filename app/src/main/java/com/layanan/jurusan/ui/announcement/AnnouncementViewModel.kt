package com.layanan.jurusan.ui.announcement

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.layanan.jurusan.data.DataRepository
import com.layanan.jurusan.data.model.AnnouncementModel
import com.layanan.jurusan.data.model.NewsModel

class AnnouncementViewModel(private val repository: DataRepository) : ViewModel() {
    private var announcementId: Int = 0

    fun setSelectedAnnouncement(announcementId: Int) {
        this.announcementId = announcementId
    }

    fun getAnnouncement(): LiveData<AnnouncementModel> = repository.getAnnouncement(announcementId)
}