package com.example.newsfeedapp.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.newsfeedapp.data.mapper.toDomain
import com.example.newsfeedapp.data.paging.PostsPagingSource
import com.example.newsfeedapp.data.remote.api.PostService
import com.example.newsfeedapp.domain.model.PaginatedPosts
import com.example.newsfeedapp.domain.model.PostDetail
import com.example.newsfeedapp.domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val api: PostService
) : PostRepository {

    override suspend fun getPostDetail(postId: Long): PostDetail {
        // Not going to implement safeApiCall for this one as it is not requested
        return api.getPostDetail(postId).post.toDomain()
    }

    override fun getPosts(): Flow<PagingData<PostDetail>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                PostsPagingSource(api, limit = 10)
            }
        ).flow
    }

//    override suspend fun getPosts(limit: Int, cursor: Int?): PaginatedPosts {
//        val response = api.getPosts(limit = limit, cursor = cursor)
//        val posts = response.posts.mapNotNull { dto ->
//            try {
//                dto.toDomain()
//            } catch (e: Exception) {
//                Log.e("PostRepositoryImpl", "Error mapping post dto", e)
//                null
//            }
//        }
//        return PaginatedPosts(
//            posts = posts,
//            nextCursor = response.paging.nextCursor,
//            hasMore = response.paging.hasMore
//        )
//    }
}
