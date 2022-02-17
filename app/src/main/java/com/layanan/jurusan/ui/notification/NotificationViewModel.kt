package com.layanan.jurusan.ui.notification

import androidx.lifecycle.ViewModel
import com.layanan.jurusan.data.DataRepository

class NotificationViewModel(private val repository: DataRepository) : ViewModel() {
    fun getNotifikasi(jwtToken: String) = repository.getNotifikasi(jwtToken)

    fun getCountNotifikasi(jwtToken: String) = repository.getCountNotifikasi(jwtToken)
}