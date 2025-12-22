package com.example.newsfeedapp.data.remote.dto

data class PostListApiResponse(
    val posts: List<PostDetailDto>
//    paging: PaginationMetaData // TODO: implement this later
)
