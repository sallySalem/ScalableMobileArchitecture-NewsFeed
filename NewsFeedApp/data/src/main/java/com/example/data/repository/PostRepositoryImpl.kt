package com.example.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.data.mapper.toDomain
import com.example.data.mapper.toRequest
import com.example.data.paging.PostsPagingSource
import com.example.data.remote.ApiResult
import com.example.data.remote.api.PostService
import com.example.data.remote.dto.PostDetailApiResponse
import com.example.data.remote.safeApiCall
import com.example.domain.model.CreatePost
import com.example.domain.model.PostDetail
import com.example.domain.model.PostInteraction
import com.example.domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val api: PostService
) : PostRepository {

    override suspend fun getPostDetail(postId: Long): PostDetail {
        return when (val res = safeApiCall<PostDetailApiResponse> { api.getPostDetail(postId) }) {
            is ApiResult.Success -> res.data.post.toDomain()
            is ApiResult.Error -> throw res.exception
        }
    }

    override suspend fun interact(interaction: PostInteraction) {
        when (val res = safeApiCall { api.interactWithPost(interaction.toRequest()) }) {
            is ApiResult.Success -> Unit
            is ApiResult.Error -> throw res.exception
        }
    }

    override suspend fun createPost(post: CreatePost) {
        val request = post.toRequest()
        when (val res = safeApiCall { api.createPost(request) }) {
            is ApiResult.Success -> Unit
            is ApiResult.Error -> throw res.exception
        }
    }

    override fun getPosts(): Flow<PagingData<PostDetail>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { PostsPagingSource(api, 10) }
        ).flow

//        val response = api.getPosts(limit = 10, cursor = null)
//        emit(
//            PaginatedPosts(
//                posts = response.posts.map { it.toDomain() },
//                paging = PaginationMetaData(
//                    nextCursor = response.paging.nextCursor,
//                    hasMore = response.paging.hasMore
//                )
//            )
//        )
    }
}
