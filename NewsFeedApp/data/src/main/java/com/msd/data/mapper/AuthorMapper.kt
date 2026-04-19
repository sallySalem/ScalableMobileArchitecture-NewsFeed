package com.msd.data.mapper

import com.msd.data.remote.dto.AuthorPreviewDto
import com.msd.domain.model.AuthorPreview

// --- From Remote to Domain ---

internal fun AuthorPreviewDto.toDomain(): AuthorPreview {
    return AuthorPreview(
        id = id,
        name = name,
        avatarUrl = avatarUrl
    )
}
