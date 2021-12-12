package com.layanan.jurusan.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.layanan.jurusan.data.DataRepository
import com.layanan.jurusan.data.datasource.RickyMortyPagingSource
import com.layanan.jurusan.data.remote.api.ApiConfig

class CharacterViewModel(private val repository: DataRepository) : ViewModel() {
    val listData = repository.listCharacter().cachedIn(viewModelScope)

}