package com.layanan.jurusan.data.datasource

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.layanan.jurusan.data.model.NewsModel
import com.layanan.jurusan.data.remote.api.Api

class ListSearchNewsDataSource(private val apiService: Api, private val search: String) : PagingSource<Int, NewsModel>() {
    override fun getRefreshKey(state: PagingState<Int, NewsModel>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>):
            LoadResult<Int, NewsModel> {

        return try {
            val currentPage = params.key ?: 1
            val response = apiService.getSearchNews(search,currentPage)
            val responseData = mutableListOf<NewsModel>()
            val data = response.body()?.news ?: emptyList()
            responseData.addAll(data)

            var nextKey: Int?
            if(currentPage == response.body()?.meta?.page || currentPage > response.body()?.meta?.page!!){
                nextKey = null
            }else{
                nextKey = currentPage.plus(1)
            }

            val loadData = LoadResult.Page(
                data = responseData,
                prevKey = if (currentPage == 1) null else -1,
                nextKey = nextKey
            )

            Log.d("LoadData",loadData.toString())
            return loadData
        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    }
}