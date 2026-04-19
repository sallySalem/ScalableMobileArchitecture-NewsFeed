package com.msd.data.mapper

import com.msd.data.local.entity.PostEntity
import com.msd.data.local.entity.PostWithAttachments
import com.msd.data.remote.dto.AttachmentRequest
import com.msd.data.remote.dto.CreatePostRequest
import com.msd.data.remote.dto.PostDetailDto
import com.msd.data.remote.dto.PostInteractionRequest
import com.msd.domain.model.AuthorPreview
import com.msd.domain.model.CreatePost
import com.msd.domain.model.PostDetail
import com.msd.domain.model.PostInteraction

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

// --- From Remote to Local (for DB) ---

internal fun PostDetailDto.toEntity(): PostEntity {
    return PostEntity(
        id = postId,
        content = content,
        authorId = author.id,
        authorName = author.name,
        authorAvatarUrl = author.avatarUrl,
        createdAt = createdAt,
        liked = liked,
        likedCount = likedCount,
        shareCount = shareCount
    )
}


// --- From Remote to Domain ---

internal fun PostDetailDto.toDomain(): PostDetail {
    return PostDetail(
        postId = postId,
        content = content,
        author = author.toDomain(),
        createdAt = createdAt,
        liked = liked,
        likedCount = likedCount,
        shareCount = shareCount,
        attachments = attachments.map { it.toDomain() }
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
