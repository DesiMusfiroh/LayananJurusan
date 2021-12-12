package com.layanan.jurusan.data.datasource

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.layanan.jurusan.data.model.NewsModel
import com.layanan.jurusan.data.remote.api.Api
import com.layanan.jurusan.data.remote.response.RickMorty

class TestNewsDataSource(private val apiService: Api) : PagingSource<Int, NewsModel>() {

    override fun getRefreshKey(state: PagingState<Int, NewsModel>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>):
            LoadResult<Int, NewsModel> {

        return try {
            val currentPage = params.key ?: 1
            val response = apiService.getListNews(currentPage)
            val responseData = mutableListOf<NewsModel>()
            val data = response.body()?.news ?: emptyList()
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