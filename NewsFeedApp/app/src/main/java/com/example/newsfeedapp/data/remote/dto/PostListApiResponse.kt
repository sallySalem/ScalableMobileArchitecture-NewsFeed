package com.example.newsfeedapp.data.remote.dto

data class PostListApiResponse(
    val posts: List<PostDetailDto>,
    val paging: PaginationMetaData
)

data class PaginationMetaData(
    val nextCursor: Int?,
    val hasMore: Boolean
)
