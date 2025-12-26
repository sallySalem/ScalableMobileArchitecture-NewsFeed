package com.example.newsfeedapp.domain.model

//TODO : Rename class
data class PaginatedPosts(
    val posts: List<PostDetail>,
    val paging: PaginationMetaData,
)

data class PaginationMetaData(
    val nextCursor: Int?,
    val hasMore: Boolean
)