package com.layanan.jurusan.ui.civitas

import androidx.lifecycle.ViewModel
import com.layanan.jurusan.data.DataRepository

class MahasiswaViewModel(private val repository: DataRepository) : ViewModel()  {
    private var id: Int = 0

    fun setId(id: Int){
        this.id = id
    }

    fun getDetailMahasiswa() = repository.getDetailMahasiswa(id)
}