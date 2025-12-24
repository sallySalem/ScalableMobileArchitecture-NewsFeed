package com.example.newsfeedapp.data.mapper

import com.example.newsfeedapp.data.remote.dto.AttachmentDto
import com.example.newsfeedapp.data.remote.dto.AuthorPreviewDto
import com.example.newsfeedapp.data.remote.dto.PostDetailDto
import com.example.newsfeedapp.data.remote.dto.PostListApiResponse
import com.example.newsfeedapp.data.remote.dto.toAttachmentType
import com.example.newsfeedapp.domain.model.Attachment
import com.example.newsfeedapp.domain.model.AuthorPreview
import com.example.newsfeedapp.domain.model.PaginatedPosts
import com.example.newsfeedapp.domain.model.PostDetail

private inline fun <T, R> Iterable<T>?.mapOrEmpty(transform: (T) -> R): List<R> = this?.map(transform).orEmpty()

internal fun PostListApiResponse.toDomain(): PaginatedPosts {
    return PaginatedPosts(
        posts = posts.mapOrEmpty(PostDetailDto::toDomain),
        nextCursor = paging.nextCursor,
        hasMore = paging.hasMore
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
        attachments = attachments.mapOrEmpty(AttachmentDto::toDomain)
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
