package com.layanan.jurusan.ui.mail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.layanan.jurusan.data.DataRepository

class MailViewModel(private val repository: DataRepository): ViewModel()  {
    fun getJenisSurat(tipe: String) = repository.getJenisSurat(tipe)
    fun getRiwayatSurat(userId: String) = repository.getRiwayatSurat(userId)

    fun getRiwayatSuratDosen(jwtToken: String) = repository.getRiwayatSuratDosen(jwtToken).cachedIn(viewModelScope)
}