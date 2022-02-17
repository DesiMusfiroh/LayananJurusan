package com.layanan.jurusan.ui.himpunan

import androidx.lifecycle.ViewModel
import com.layanan.jurusan.data.DataRepository

class HimpunanViewModel(private val repository: DataRepository): ViewModel(){
    private var himpunanName: String = "himasi"

    fun setSelectedHimpunan(name: String) {
        this.himpunanName = name
    }

    fun getProfileHimpunan() = repository.getProfileHimpunan(himpunanName)
}