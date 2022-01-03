package com.layanan.jurusan.ui.jurusan

import androidx.lifecycle.ViewModel
import com.layanan.jurusan.data.DataRepository

class JurusanViewModel(private val repository: DataRepository) : ViewModel() {
    fun getProfileJurusan() = repository.getProfileJurusan()
}