package com.example.newsfeedapp.data.remote.api

import com.example.newsfeedapp.data.remote.dto.PostDetailApiResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface PostService {

    @GET("posts/{id}")
    suspend fun getPostDetail(
        @Path("id") postId: Long
    ): PostDetailApiResponse
}