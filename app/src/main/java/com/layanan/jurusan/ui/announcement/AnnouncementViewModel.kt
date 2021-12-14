package com.layanan.jurusan.ui.announcement

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.layanan.jurusan.data.DataRepository

class AnnouncementViewModel(private val repository: DataRepository): ViewModel() {
    val listAnnouncement = repository.getListAnnouncement().cachedIn(viewModelScope)
}