package com.example.domain.repository

import androidx.paging.PagingData
import com.example.domain.model.CreatePost
import com.example.domain.model.PostDetail
import com.example.domain.model.PostInteraction
import kotlinx.coroutines.flow.Flow

interface PostRepository {
    suspend fun getPostDetail(postId: Long): PostDetail
    suspend fun createPost(post: CreatePost)
    suspend fun interact(interaction: PostInteraction)
    fun getPosts(): Flow<PagingData<PostDetail>>
}
