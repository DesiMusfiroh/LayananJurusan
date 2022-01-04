package com.layanan.jurusan.ui.prodi

import androidx.lifecycle.ViewModel
import com.layanan.jurusan.data.DataRepository

class ProdiViewModel(private val repository: DataRepository): ViewModel(){
    private var prodiName: String = "sistem_informasi"

    fun setSelectedProdi(name: String) {
        this.prodiName = name
    }

    fun getProfileProdi() = repository.getProfileProdi(prodiName)
}