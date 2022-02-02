package com.layanan.jurusan.ui.profile

import androidx.lifecycle.ViewModel
import com.layanan.jurusan.data.DataRepository

class ProfileViewModel(private val repository: DataRepository): ViewModel() {
    fun getUserProfile(jwtToken: String) = repository.getUserProfile(jwtToken)

    fun logout(jwtToken: String) = repository.logout(jwtToken)
}