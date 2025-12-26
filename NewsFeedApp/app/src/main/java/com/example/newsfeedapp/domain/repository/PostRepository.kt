package com.example.newsfeedapp.domain.repository

import androidx.paging.PagingData
import com.example.newsfeedapp.domain.model.PostDetail
import kotlinx.coroutines.flow.Flow

interface PostRepository {
    suspend fun getPostDetail(postId: Long): PostDetail

//    suspend fun getPosts(limit: Int = 10, cursor: Int? = null): PaginatedPosts
    fun getPosts(): Flow<PagingData<PostDetail>>
}