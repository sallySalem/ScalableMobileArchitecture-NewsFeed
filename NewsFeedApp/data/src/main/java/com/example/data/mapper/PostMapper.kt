package com.example.data.mapper

import com.example.data.remote.dto.AttachmentDto
import com.example.data.remote.dto.AttachmentRequest
import com.example.data.remote.dto.AuthorPreviewDto
import com.example.data.remote.dto.CreatePostRequest
import com.example.data.remote.dto.PostDetailDto
import com.example.data.remote.dto.PostInteractionRequest
import com.example.data.remote.dto.PostListApiResponse
import com.example.data.remote.dto.toAttachmentType
import com.example.domain.model.Attachment
import com.example.domain.model.AuthorPreview
import com.example.domain.model.CreatePost
import com.example.domain.model.PaginatedPosts
import com.example.domain.model.PaginationMetaData
import com.example.domain.model.PostDetail
import com.example.domain.model.PostInteraction

private inline fun <T, R> Iterable<T>?.mapOrEmpty(transform: (T) -> R): List<R> = this?.map(transform).orEmpty()

internal fun PostListApiResponse.toDomain(): PaginatedPosts {
    return PaginatedPosts(
        posts = posts.mapOrEmpty { it.toDomain() },
        paging = PaginationMetaData(
            nextCursor = paging.nextCursor,
            hasMore = paging.hasMore
        )
    )
}

internal fun PostDetailDto.toDomain(): PostDetail {
    return PostDetail(
        postId = postId,
        content = content,
        author = author.toDomain(),
        createdAt = createdAt,
        liked = liked,
        likedCount = likedCount,
        shareCount = shareCount,
        attachments = attachments.mapOrEmpty { it.toDomain() }
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

fun CreatePost.toRequest(): CreatePostRequest {
    return CreatePostRequest(
        title = title,
        content = content,
        attachment = attachment?.let {
            AttachmentRequest(
                type = it.type.name,
                uri = it.uri
            )
        }
    )
}

fun PostInteraction.toRequest(): PostInteractionRequest {
    return PostInteractionRequest(
        postId = postId,
        interactionType = type.name
    )
}