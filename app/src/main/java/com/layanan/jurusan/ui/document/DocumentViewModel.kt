package com.layanan.jurusan.ui.document

import androidx.lifecycle.ViewModel
import com.layanan.jurusan.data.DataRepository

class DocumentViewModel(private val repository: DataRepository) : ViewModel() {
    fun getListDocument() = repository.getListDocument()
}