package com.layanan.jurusan.data.datasource

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.layanan.jurusan.data.model.NewsModel
import com.layanan.jurusan.data.remote.api.Api

class ListNewsDataSource(private val apiService: Api) : PagingSource<Int, NewsModel>() {

    override suspend fun load(params: LoadParams<Int>):
            LoadResult<Int, NewsModel> {

        return try {
            val currentPage = params.key ?: 1
            val response = apiService.getListNews(currentPage)
            val responseData = mutableListOf<NewsModel>()
            val data = response.body()?.news ?: emptyList()
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
        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    }

//    override fun getRefreshKey(state: PagingState<Int, NewsModel>): Int? {
//        return null
//    }

    override fun getRefreshKey(state: PagingState<Int, NewsModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                    ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

}