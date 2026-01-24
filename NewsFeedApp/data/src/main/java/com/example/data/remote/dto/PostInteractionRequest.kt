package com.example.data.remote.dto

data class PostInteractionRequest(
    val postId: Long,
    val interactionType: String // LIKED, REMOVED_LIKE, SHARED
)