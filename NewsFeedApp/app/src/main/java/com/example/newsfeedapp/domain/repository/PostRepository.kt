package com.example.newsfeedapp.domain.repository

import androidx.paging.PagingData
import com.example.newsfeedapp.data.remote.dto.CreatePostRequest
import com.example.newsfeedapp.data.remote.dto.PostInteractionRequest
import com.example.newsfeedapp.domain.model.PostDetail
import kotlinx.coroutines.flow.Flow

interface PostRepository {
    suspend fun getPostDetail(postId: Long): PostDetail

//    suspend fun getPosts(limit: Int = 10, cursor: Int? = null): PaginatedPosts
    fun getPosts(): Flow<PagingData<PostDetail>>

    suspend fun interact(request: PostInteractionRequest)

    suspend fun createPost(request: CreatePostRequest)
}