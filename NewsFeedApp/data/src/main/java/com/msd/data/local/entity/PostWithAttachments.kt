package com.msd.data.local.entity

import androidx.room.Embedded
import androidx.room.Relation

data class PostWithAttachments(
    @Embedded val post: PostEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "postId"
    )
    val attachments: List<AttachmentEntity>
)
