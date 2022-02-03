package com.layanan.jurusan.ui.civitas

import androidx.lifecycle.ViewModel
import com.layanan.jurusan.data.DataRepository

class CivitasViewModel(private val repository: DataRepository) : ViewModel()  {
    fun getAngkatan() = repository.getAngkatan()
    fun getMahasiswa(prodi: String, angkatan: String, status: String) = repository.getMahasiswa(prodi, angkatan, status)
}