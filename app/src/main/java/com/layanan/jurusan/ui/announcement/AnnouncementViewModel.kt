package com.layanan.jurusan.ui.announcement

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.layanan.jurusan.data.DataRepository
import com.layanan.jurusan.data.model.AnnouncementModel

class AnnouncementViewModel(private val repository: DataRepository) : ViewModel() {
    private var announcementId: Int = 0

    fun setSelectedAnnouncement(announcementId: Int) {
        this.announcementId = announcementId
    }

    fun getAnnouncement(): LiveData<AnnouncementModel> = repository.getAnnouncement(announcementId)
    val listAnnouncement = repository.getListAnnouncement().cachedIn(viewModelScope)

}