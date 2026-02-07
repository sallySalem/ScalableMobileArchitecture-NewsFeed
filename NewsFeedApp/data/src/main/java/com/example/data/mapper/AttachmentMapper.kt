package com.example.data.mapper

import com.example.data.local.entity.AttachmentEntity
import com.example.data.remote.dto.AttachmentDto
import com.example.data.remote.dto.toAttachmentType
import com.example.domain.model.Attachment

// --- From Local to Domain ---

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

internal fun AttachmentDto.toDomain(): Attachment {
    return Attachment(
        id = id,
        type = type.toAttachmentType(),
        contentUrl = contentUrl,
        previewImageUrl = previewImageUrl,
        caption = caption
    )
}
