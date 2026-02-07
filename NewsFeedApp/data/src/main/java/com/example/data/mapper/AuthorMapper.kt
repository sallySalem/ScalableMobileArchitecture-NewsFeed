package com.example.data.mapper

import com.example.data.remote.dto.AuthorPreviewDto
import com.example.domain.model.AuthorPreview

// --- From Remote to Domain ---

internal fun AuthorPreviewDto.toDomain(): AuthorPreview {
    return AuthorPreview(
        id = id,
        name = name,
        avatarUrl = avatarUrl
    )
}
