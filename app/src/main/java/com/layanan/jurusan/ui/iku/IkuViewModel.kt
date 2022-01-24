package com.layanan.jurusan.ui.iku

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.layanan.jurusan.data.DataRepository
import com.layanan.jurusan.data.model.Iku1Model

class IkuViewModel(private val repository: DataRepository) : ViewModel() {
    fun getIku1(year: String): LiveData<List<Iku1Model>> = repository.getIku1(year)
    fun getIku2(year: String) = repository.getIku2(year)
}