package com.example.newsfeedapp.data.remote.dto

data class PostInteractionRequest(
    val postId: Long,
    val interactionType: String // LIKED, REMOVED_LIKE, SHARED
)