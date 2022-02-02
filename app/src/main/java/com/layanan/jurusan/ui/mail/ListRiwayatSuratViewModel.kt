package com.layanan.jurusan.ui.mail

import androidx.lifecycle.ViewModel
import com.layanan.jurusan.data.DataRepository

class ListRiwayatSuratViewModel(private val repository: DataRepository): ViewModel(){
    fun getRiwayatSurat(jwtToken: String) = repository.getRiwayatSurat(jwtToken)
}