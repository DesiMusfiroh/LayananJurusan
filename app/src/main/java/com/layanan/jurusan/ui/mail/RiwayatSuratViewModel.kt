package com.layanan.jurusan.ui.mail

import androidx.lifecycle.ViewModel
import com.layanan.jurusan.data.DataRepository

class RiwayatSuratViewModel(private val repository: DataRepository): ViewModel()  {
    private var riwayatSuratId: Int = 0

    fun setSelectedData(id: Int){
        this.riwayatSuratId = id
    }

    fun showRiwayatSurat(jwtToken: String) = repository.showRiwayatSurat(jwtToken,riwayatSuratId)
}