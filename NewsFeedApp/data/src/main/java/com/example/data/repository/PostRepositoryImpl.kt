package com.example.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.data.local.PostDatabase
import com.example.data.mapper.toDomain
import com.example.data.mapper.toRequest
import com.example.data.paging.PostRemoteMediator
import com.example.data.remote.ApiResult
import com.example.data.remote.api.PostService
import com.example.data.remote.dto.PostDetailApiResponse
import com.example.data.remote.safeApiCall
import com.example.domain.model.CreatePost
import com.example.domain.model.PostDetail
import com.example.domain.model.PostInteraction
import com.example.domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class PostRepositoryImpl @Inject constructor(
    private val api: PostService,
    private val db: PostDatabase
) : PostRepository {

    override suspend fun getPostDetail(postId: Long): PostDetail {
        val post = db.postDao().getPost(postId)
        if (post != null) {
            return post.toDomain()
        }

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
            remoteMediator = PostRemoteMediator(
                service = api,
                database = db
            ),
            pagingSourceFactory = {
                db.postDao().getPosts()
            }
        ).flow.map { pagingData ->
            pagingData.map { it.toDomain() }
        }
    }
}
