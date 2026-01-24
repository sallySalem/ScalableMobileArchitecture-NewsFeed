package com.example.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.mapper.toDomain
import com.example.data.remote.api.PostService
import com.example.domain.model.PostDetail

class PostsPagingSource(
    private val api: PostService,
    private val limit: Int
) : PagingSource<Int, PostDetail>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PostDetail> {
        return try {
            val cursor = params.key
            val response = api.getPosts(
                limit = limit,
                cursor = cursor
            ).toDomain()

            LoadResult.Page(
                data = response.posts, // TODO:  map { it.toPreview() },
                prevKey = null, // cursor pagination: no backward load - only paging forward.
                nextKey = if (response.paging.hasMore) response.paging.nextCursor else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PostDetail>): Int? {
        // This implementation always starts from the first page on refresh.
        return null
    }
}
