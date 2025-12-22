package com.example.newsfeedapp.domain.repository

import com.example.newsfeedapp.domain.model.PostDetail

interface PostRepository {
    suspend fun getPostDetail(postId: Long): PostDetail

    // Return a single page (list) of posts. Backend currently returns an array.
    suspend fun getPosts(page: Int = 1, limit: Int = 10): List<PostDetail>
}