package com.layanan.jurusan.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.layanan.jurusan.data.DataRepository
import com.layanan.jurusan.data.model.NewsModel

class HomeViewModel(private val repository: DataRepository) : ViewModel() {
    fun getLatestNews() : LiveData<List<NewsModel>> = repository.getLatestNews()
}