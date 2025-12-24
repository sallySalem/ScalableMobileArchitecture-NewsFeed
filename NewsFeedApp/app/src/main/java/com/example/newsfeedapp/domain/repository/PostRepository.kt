package com.example.newsfeedapp.domain.repository

import com.example.newsfeedapp.domain.model.PaginatedPosts
import com.example.newsfeedapp.domain.model.PostDetail

interface PostRepository {
    suspend fun getPostDetail(postId: Long): PostDetail

    suspend fun getPosts(limit: Int = 10, cursor: Int? = null): PaginatedPosts
}