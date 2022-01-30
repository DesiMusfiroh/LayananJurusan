package com.layanan.jurusan.ui.mail

import androidx.lifecycle.ViewModel
import com.layanan.jurusan.data.DataRepository

class MailViewModel(private val repository: DataRepository): ViewModel()  {
    fun getJenisSurat(tipe: String) = repository.getJenisSurat(tipe)
    fun getRiwayatSurat(userId: String) = repository.getRiwayatSurat(userId)
}