package com.layanan.jurusan.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.layanan.jurusan.data.model.AnnouncementModel
import com.layanan.jurusan.data.remote.api.Api

class ListAnnouncementDataSource(private val apiService: Api): PagingSource<Int, AnnouncementModel>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AnnouncementModel> {
        return try {
            val currentPage = params.key ?: 1
            val response = apiService.getListAnnouncement(currentPage)
            val responseData = mutableListOf<AnnouncementModel>()
            val data = response.body()?.announcement ?: emptyList()
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

    override fun getRefreshKey(state: PagingState<Int, AnnouncementModel>): Int? {
        TODO("Not yet implemented")
    }
}