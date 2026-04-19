package com.msd.data.mapper

import com.msd.data.local.entity.AttachmentEntity
import com.msd.data.remote.dto.AttachmentDto
import com.msd.data.remote.dto.toAttachmentType
import com.msd.domain.model.Attachment

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
