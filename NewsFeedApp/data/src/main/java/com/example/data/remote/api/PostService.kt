package com.example.data.remote.api

import com.example.data.remote.dto.CreatePostRequest
import com.example.data.remote.dto.PostDetailApiResponse
import com.example.data.remote.dto.PostInteractionRequest
import com.example.data.remote.dto.PostListApiResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface PostService {

    @GET("posts/{postId}")
    suspend fun getPostDetail(
        @Path("postId") postId: Long
    ): Response<PostDetailApiResponse>

    @GET("posts")
    suspend fun getPosts(
        @Query("limit") limit: Int = 10,
        @Query("cursor") cursor: Long? = null,
    ): PostListApiResponse

    @POST("posts/interaction")
    suspend fun interactWithPost(
        @Body request: PostInteractionRequest
    ): Response<Unit>

    @POST("posts")
    suspend fun createPost(
        @Body request: CreatePostRequest
    ): Response<Unit>
}