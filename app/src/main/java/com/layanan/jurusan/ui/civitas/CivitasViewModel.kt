package com.layanan.jurusan.ui.civitas

import androidx.lifecycle.ViewModel
import com.layanan.jurusan.data.DataRepository

class CivitasViewModel(private val repository: DataRepository) : ViewModel()  {
    private var searchMahasiswaQuery: String = ""
    private var searchDosenQuery: String = ""
    private var searchAlumniQuery: String = ""

    fun getAngkatan() = repository.getAngkatan()
    fun getMahasiswa(prodi: String, angkatan: String, status: String) = repository.getMahasiswa(prodi, angkatan, status)
    fun getDosen(prodi: String,status: String) = repository.getDosen(prodi,status)
    fun getStatusDosen() = repository.getStatusDosen()


    /*
        @param type = mahasiswa, dosen, alumni

     */
    fun setSearchQuery(search: String, type: String){
        when(type){
            "mahasiswa"-> this.searchMahasiswaQuery = search
            "dosen" -> this.searchDosenQuery = search
            "alumni" -> this.searchAlumniQuery = search
        }
    }

    fun getSearchMahasiswa(prodi: String, angkatan: String, status: String) = repository.getSearchMahasiswa(prodi,angkatan,status,searchMahasiswaQuery)
    fun getSearchDosen(prodi: String, status: String) = repository.getSearchDosen(prodi,status,searchDosenQuery)
}