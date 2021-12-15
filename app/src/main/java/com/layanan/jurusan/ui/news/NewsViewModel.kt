package com.layanan.jurusan.ui.news

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.layanan.jurusan.data.DataRepository
import com.layanan.jurusan.data.datasource.ListNewsDataSource
import com.layanan.jurusan.data.model.NewsModel
import com.layanan.jurusan.data.remote.api.ApiConfig
import kotlinx.coroutines.flow.Flow

class NewsViewModel(private val repository: DataRepository): ViewModel() {
//    fun getListNews(): Flow<PagingData<NewsModel>> {
//        val data = repository.getListNews().cachedIn(viewModelScope)
//        return data
//    }

//    val listNews = Pager(PagingConfig(pageSize = 10)){
//        ListNewsDataSource(ApiConfig.getApiService())
//    }.flow.cachedIn(viewModelScope)

    private var searchQuery: String = ""

    val testNews = repository.getTestNews().cachedIn(viewModelScope)
    val listNews = repository.getListNews().cachedIn(viewModelScope)

    fun setQuery(search: String){
        this.searchQuery = search
    }

    fun searchNews() = repository.getSearchNews(searchQuery).cachedIn(viewModelScope)

}