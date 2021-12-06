package com.layanan.jurusan.ui.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.layanan.jurusan.data.DataRepository

class NewsViewModel(private val repository: DataRepository): ViewModel() {
    fun getListNews() = repository.getListNews().cachedIn(viewModelScope)

}