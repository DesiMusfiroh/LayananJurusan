package com.layanan.jurusan.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.layanan.jurusan.data.DataRepository
import com.layanan.jurusan.data.remote.response.SaveFcmTokenResponse
import com.layanan.jurusan.data.remote.response.login.LoginDataResponse
import com.layanan.jurusan.data.remote.response.login.LoginResponse

class LoginViewModel(private val repository: DataRepository): ViewModel() {

    fun login(username: String, password: String): LiveData<LoginDataResponse> = repository.login(username, password)

    fun saveFcmToken(fcmToken: String, jwtToken: String): LiveData<SaveFcmTokenResponse> = repository.saveFcmToken(fcmToken, jwtToken)
}