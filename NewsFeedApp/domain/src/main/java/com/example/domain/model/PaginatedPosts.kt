package com.example.domain.model

data class PaginatedPosts(
    val posts: List<PostDetail>,
    val paging: PaginationMetaData,
)

data class PaginationMetaData(
    val nextCursor: Long?,
    val hasMore: Boolean
)
