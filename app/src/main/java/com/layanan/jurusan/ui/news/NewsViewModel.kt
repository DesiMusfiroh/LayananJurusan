package com.layanan.jurusan.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.layanan.jurusan.data.DataRepository
import com.layanan.jurusan.data.model.NewsModel

class NewsViewModel(private val repository: DataRepository): ViewModel() {
    fun getListNews() = repository.getListNews().cachedIn(viewModelScope)

}