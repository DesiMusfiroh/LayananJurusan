package com.layanan.jurusan.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.layanan.jurusan.data.model.NewsModel
import com.layanan.jurusan.data.model.TujuanSuratModel
import com.layanan.jurusan.data.remote.api.Api

class ListRiwayatSuratDosenDataSource(private val apiService: Api, private val jwtToken: String) : PagingSource<Int, TujuanSuratModel>() {
    override fun getRefreshKey(state: PagingState<Int, TujuanSuratModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TujuanSuratModel> {
        return try{
            val currentPage = params.key ?: 1
            val response = apiService.getRiwayatSuratDosen(jwtToken,currentPage)
            val responseData = mutableListOf<TujuanSuratModel>()
            val data = response.body()?.data ?: emptyList()
            responseData.addAll(data)
            var nextKey: Int?
            if(currentPage == response.body()?.meta?.page || currentPage > response.body()?.meta?.page!!){
                nextKey = null
            }else{
                nextKey = currentPage.plus(1)
            }

            LoadResult.Page(
                data = responseData,
                prevKey = if (currentPage == 1) null else -1,
                nextKey = nextKey
            )
        }catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}