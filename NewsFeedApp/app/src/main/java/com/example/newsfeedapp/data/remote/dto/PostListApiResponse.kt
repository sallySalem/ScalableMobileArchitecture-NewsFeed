package com.example.newsfeedapp.data.remote.dto

data class PostListApiResponse(
    val posts: List<PostDetailDto>,
    val paging: PaginationMetaData
)

data class PaginationMetaData(
    val page: Int,
    val limit: Int,
    val totalItems: Int,
    val totalPages: Int
)
