package com.example.newsfeedapp.domain.repository

import com.example.newsfeedapp.domain.model.PostDetail

interface PostRepository {
    suspend fun getPostDetail(postId: Long): PostDetail
}