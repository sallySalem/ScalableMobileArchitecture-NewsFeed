package com.example.newsfeedapp.domain.model

data class PaginatedPosts(
    val posts: List<PostDetail>,
    val nextCursor: Int?,
    val hasMore: Boolean
)
