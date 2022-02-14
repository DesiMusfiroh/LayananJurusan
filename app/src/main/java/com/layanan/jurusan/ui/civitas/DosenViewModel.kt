package com.layanan.jurusan.ui.civitas

import androidx.lifecycle.ViewModel
import com.layanan.jurusan.data.DataRepository

class DosenViewModel(private val repository: DataRepository) : ViewModel() {
    fun getDetailDosen(id: Int) = repository.getDetailDosen(id)
}