package com.example.domain.model

enum class AttachmentType {
    VIDEO,
    IMAGE,
    UNKNOWN
}

data class PostDetail(
    val postId: Long,
    val content: String,
    val author: AuthorPreview,
    val createdAt: String,
    val liked: Boolean,
    val likedCount: Int,
    val shareCount: Int,
    val attachments: List<Attachment>
)

data class AuthorPreview(
    val id: Long,
    val name: String,
    val avatarUrl: String?
)

data class Attachment(
    val id: Long,
    val type: AttachmentType,
    val contentUrl: String,
    val previewImageUrl: String?,
    val caption: String?
)
