package com.layanan.jurusan.ui.iku

import androidx.lifecycle.ViewModel
import com.layanan.jurusan.data.DataRepository

class Iku2ViewModel(private val repository: DataRepository) : ViewModel(){
    fun getIku2(year: String) = repository.getIku2(year)
}