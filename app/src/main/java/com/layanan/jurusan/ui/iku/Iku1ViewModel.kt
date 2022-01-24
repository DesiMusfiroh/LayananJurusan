package com.layanan.jurusan.ui.iku

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.layanan.jurusan.data.DataRepository
import com.layanan.jurusan.data.model.Iku1Model

class Iku1ViewModel(private val repository: DataRepository) : ViewModel() {
    fun getIku1(year: String): LiveData<List<Iku1Model>> = repository.getIku1(year)
}