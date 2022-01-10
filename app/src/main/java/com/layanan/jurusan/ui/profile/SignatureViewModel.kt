package com.layanan.jurusan.ui.profile

import androidx.lifecycle.ViewModel
import com.layanan.jurusan.data.DataRepository
import okhttp3.MultipartBody

class SignatureViewModel(private val repository: DataRepository) : ViewModel(){
    fun uploadSignature(image: MultipartBody.Part, jwtToken: String) = repository.uploadSignature(image,jwtToken)
}