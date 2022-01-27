package com.layanan.jurusan.ui.iku

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.layanan.jurusan.data.DataRepository
import com.layanan.jurusan.data.model.Iku1Model

class IkuViewModel(private val repository: DataRepository) : ViewModel() {
    fun getIku1(year: String): LiveData<List<Iku1Model>> = repository.getIku1(year)
    fun getIku2(year: String) = repository.getIku2(year)


    fun getIku5(year: String) = repository.getIku5(year)
    fun getIku6(year: String) = repository.getIku6(year)
    fun getIku7(year: String) = repository.getIku7(year)
    fun getIku8(year: String) = repository.getIku8(year)
}