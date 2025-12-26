package com.example.newsfeedapp.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.newsfeedapp.data.mapper.toDomain
import com.example.newsfeedapp.data.remote.api.PostService
import com.example.newsfeedapp.domain.model.PostDetail

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
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.nextKey?.minus(limit)
        }
    }
}
