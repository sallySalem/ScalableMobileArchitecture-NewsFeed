package com.msd.data.remote.dto

import com.msd.domain.model.AttachmentType

data class PostDetailApiResponse(
    val post: PostDetailDto
)

data class PostDetailDto(
    val postId: Long,
    val content: String,
    val author: AuthorPreviewDto,
    val createdAt: String,
    val liked: Boolean,
    val likedCount: Int,
    val shareCount: Int,
    val attachments: List<AttachmentDto>
)

data class AuthorPreviewDto(
    val id: Long,
    val name: String,
    val avatarUrl: String?
)

data class AttachmentDto(
    val id: Long,
    val type: String,
    val contentUrl: String,
    val previewImageUrl: String?,
    val caption: String?
)

fun String.toAttachmentType(): AttachmentType {
    return when (this.uppercase()) {
        "VIDEO" -> AttachmentType.VIDEO
        "IMAGE" -> AttachmentType.IMAGE
        else -> AttachmentType.UNKNOWN
    }
}
