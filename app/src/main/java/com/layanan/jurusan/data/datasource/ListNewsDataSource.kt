package com.layanan.jurusan.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.layanan.jurusan.data.model.NewsModel
import com.layanan.jurusan.data.remote.api.Api

class ListNewsDataSource(private val apiService: Api) : PagingSource<Int, NewsModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NewsModel> {
        try {
            val currentLoadingPageKey = params.key ?: 1
            val response = apiService.getListNews(currentLoadingPageKey)
            val responseData = mutableListOf<NewsModel>()
            val data = response.body()?.news ?: emptyList()
            responseData.addAll(data)

            return LoadResult.Page(
                data = responseData,
                prevKey = if (currentLoadingPageKey == 1) null else currentLoadingPageKey - 1,
                nextKey = currentLoadingPageKey.plus(1)
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, NewsModel>): Int? {
        TODO("Not yet implemented")
    }

}