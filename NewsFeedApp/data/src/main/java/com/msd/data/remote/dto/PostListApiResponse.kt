package com.msd.data.remote.dto

data class PostListApiResponse(
    val posts: List<PostDetailDto>,
    val paging: PaginationMetaData
)
