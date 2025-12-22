package com.example.newsfeedapp.data.remote.api

import com.example.newsfeedapp.data.remote.dto.PostDetailApiResponse
import com.example.newsfeedapp.data.remote.dto.PostDetailDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PostService {

    @GET("posts/{id}")
    suspend fun getPostDetail(
        @Path("id") postId: Long
    ): PostDetailApiResponse

    @GET("posts")
    suspend fun getPosts(
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 10
    ): List<PostDetailDto>
}