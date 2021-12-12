package com.layanan.jurusan.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.layanan.jurusan.data.remote.api.Api
import com.layanan.jurusan.data.remote.response.RickMorty

class RickyMortyPagingSource
    (
    private val apiService: Api
) : PagingSource<Int, RickMorty>() {

    override fun getRefreshKey(state: PagingState<Int, RickMorty>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>):
            LoadResult<Int, RickMorty> {

        return try {
            val currentPage = params.key ?: 1
            val response = apiService.getAllCharacters(currentPage)
            val responseData = mutableListOf<RickMorty>()
            val data = response.body()?.results ?: emptyList()
            responseData.addAll(data)

            LoadResult.Page(
                data = responseData,
                prevKey = if (currentPage == 1) null else -1,
                nextKey = currentPage.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    }
}