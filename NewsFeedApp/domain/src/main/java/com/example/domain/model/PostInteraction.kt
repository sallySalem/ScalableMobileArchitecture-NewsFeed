package com.example.domain.model

data class PostInteraction(
    val postId: Long,
    val type: InteractionType
)

enum class InteractionType {
    LIKED,
    REMOVED_LIKE,
    SHARED
}
