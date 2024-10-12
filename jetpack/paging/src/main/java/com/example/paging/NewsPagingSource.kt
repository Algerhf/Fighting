package com.example.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.paging.net.INewsService

class NewsPagingSource(
    val service: INewsService,
) : PagingSource<Int, NewsBean>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NewsBean> {
        try {
            val nextPageNumber = params.key ?: 1
            val response = service.getUser(nextPageNumber)

            val nextKey = if(nextPageNumber == 1)
            return LoadResult.Page(
                data = response.newsList,
                prevKey = params.key ?:nextPageNumber,
                nextKey = if(response.hasMore) ?: (nextPageNumber + 1)
            )
        } catch (e: Exception) {

        }
    }

    override fun getRefreshKey(state: PagingState<Int, NewsBean>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

}