package com.layanan.jurusan.data.datasource

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.layanan.jurusan.data.model.Mahasiswa
import com.layanan.jurusan.data.model.NewsModel
import com.layanan.jurusan.data.remote.api.Api
import com.layanan.jurusan.data.remote.response.civitas.MahasiswaResponse
import retrofit2.Response

class ListAlumniDataSource(private val apiService: Api, private val prodi: String,private val angkatan: String, private val search: String? = null) : PagingSource<Int, Mahasiswa>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Mahasiswa> {
        return try {
            val currentPage = params.key ?: 1
            var response : Response<MahasiswaResponse>? = null
            if(search == null){
                response = apiService.getAlumni(prodi, angkatan, currentPage)
            }else{
                response = apiService.getSearchAlumni(prodi,angkatan,search,currentPage)
            }
            val responseData = mutableListOf<Mahasiswa>()
            val data = response.body()?.data ?: emptyList()
            responseData.addAll(data)
            var nextKey: Int?
            if(currentPage == response.body()?.meta?.page || currentPage > response.body()?.meta?.page!!){
                nextKey = null
            }else{
                nextKey = currentPage.plus(1)
            }

            val res = LoadResult.Page(
                data = responseData,
                prevKey = if (currentPage == 1) null else -1,
                nextKey = nextKey
            )

            return res
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Mahasiswa>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}