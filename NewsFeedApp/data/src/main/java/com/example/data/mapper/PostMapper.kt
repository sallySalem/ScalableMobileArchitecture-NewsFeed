package com.example.data.mapper

import com.example.data.local.entity.AttachmentEntity
import com.example.data.local.entity.PostEntity
import com.example.data.local.entity.PostWithAttachments
import com.example.data.remote.dto.AttachmentDto
import com.example.data.remote.dto.AttachmentRequest
import com.example.data.remote.dto.AuthorPreviewDto
import com.example.data.remote.dto.CreatePostRequest
import com.example.data.remote.dto.PostDetailDto
import com.example.data.remote.dto.PostInteractionRequest
import com.example.data.remote.dto.toAttachmentType
import com.example.domain.model.Attachment
import com.example.domain.model.AuthorPreview
import com.example.domain.model.CreatePost
import com.example.domain.model.PostDetail
import com.example.domain.model.PostInteraction

// --- From Local to Domain ---

internal fun PostWithAttachments.toDomain(): PostDetail {
    return PostDetail(
        postId = post.id,
        content = post.content,
        author = AuthorPreview(
            id = post.authorId,
            name = post.authorName,
            avatarUrl = post.authorAvatarUrl
        ),
        createdAt = post.createdAt,
        liked = post.liked,
        likedCount = post.likedCount,
        shareCount = post.shareCount,
        attachments = attachments.map { it.toDomain() }
    )
}

internal fun AttachmentEntity.toDomain(): Attachment {
    return Attachment(
        id = id,
        type = type.toAttachmentType(),
        contentUrl = contentUrl,
        previewImageUrl = previewImageUrl,
        caption = caption
    )
}


// --- From Remote to Local (for DB) ---

internal fun PostDetailDto.toEntity(): PostEntity {
    return PostEntity(
        id = postId,
        content = content ?: "",
        authorId = author.id,
        authorName = author.name,
        authorAvatarUrl = author.avatarUrl,
        createdAt = createdAt ?: "",
        liked = liked,
        likedCount = likedCount,
        shareCount = shareCount
    )
}

internal fun AttachmentDto.toEntity(postId: Long): AttachmentEntity {
    return AttachmentEntity(
        id = id,
        postId = postId,
        type = type,
        contentUrl = contentUrl,
        previewImageUrl = previewImageUrl,
        caption = caption
    )
}


// --- From Remote to Domain ---

internal fun PostDetailDto.toDomain(): PostDetail {
    return PostDetail(
        postId = postId,
        content = content ?: "",
        author = author.toDomain(),
        createdAt = createdAt ?: "",
        liked = liked,
        likedCount = likedCount,
        shareCount = shareCount,
        attachments = attachments.map { it.toDomain() }
    )
}

internal fun AuthorPreviewDto.toDomain(): AuthorPreview {
    return AuthorPreview(
        id = id,
        name = name,
        avatarUrl = avatarUrl
    )
}

internal fun AttachmentDto.toDomain(): Attachment {
    return Attachment(
        id = id,
        type = type.toAttachmentType(),
        contentUrl = contentUrl,
        previewImageUrl = previewImageUrl,
        caption = caption
    )
}

// --- From Domain to Remote ---

internal fun PostInteraction.toRequest(): PostInteractionRequest {
    return PostInteractionRequest(
        postId = postId,
        interactionType = type.name
    )
}

internal fun CreatePost.toRequest(): CreatePostRequest {
    return CreatePostRequest(
        content = content,
        attachment = attachment?.let {
            AttachmentRequest(
                type = it.type.name,
                uri = it.uri
            )
        }
    )
}
