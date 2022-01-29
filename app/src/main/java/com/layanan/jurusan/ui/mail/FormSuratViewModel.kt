package com.layanan.jurusan.ui.mail

import androidx.lifecycle.ViewModel
import com.layanan.jurusan.data.DataRepository
import com.loopj.android.http.RequestParams

class FormSuratViewModel(private val repository: DataRepository): ViewModel() {
    private var jenisSuratId: Int = 0
    fun getKeywordSurat() = repository.getKeywordSurat(jenisSuratId)

    fun setJenisSuratId(jenisSuratId: Int){
        this.jenisSuratId = jenisSuratId
    }

    fun storePermohonanSurat(params: RequestParams, jwtToken: String) = repository.storePermohonanSurat(params,jwtToken)
}