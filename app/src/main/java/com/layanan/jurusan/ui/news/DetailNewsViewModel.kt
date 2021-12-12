package com.layanan.jurusan.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.layanan.jurusan.data.DataRepository
import com.layanan.jurusan.data.model.NewsModel

class DetailNewsViewModel(private val repository: DataRepository): ViewModel() {
    private var newsId: Int = 0

    fun setSelectedNews(newsId: Int) {
        this.newsId = newsId
    }

    fun getDetailNews(): LiveData<NewsModel> = repository.getDetailNews(newsId)
}