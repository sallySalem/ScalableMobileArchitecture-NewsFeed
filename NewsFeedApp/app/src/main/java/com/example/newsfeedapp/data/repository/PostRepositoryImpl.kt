package com.example.newsfeedapp.data.repository

import com.example.newsfeedapp.data.mapper.toDomain
import com.example.newsfeedapp.data.remote.api.PostService
import com.example.newsfeedapp.data.remote.safeApiCall
import com.example.newsfeedapp.data.remote.ApiResult
import com.example.newsfeedapp.domain.model.PostDetail
import com.example.newsfeedapp.domain.repository.PostRepository
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val api: PostService
) : PostRepository {

    override suspend fun getPostDetail(postId: Long): PostDetail {
        return when (val res = safeApiCall { api.getPostDetail(postId) }) {
            is ApiResult.Success -> res.data.post.toDomain()
            is ApiResult.Error -> throw res.exception
        }
    }
}
