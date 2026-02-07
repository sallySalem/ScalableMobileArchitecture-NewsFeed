package com.example.domain.model

data class CreatePost(
    val content: String,
    val attachment: CreatePostAttachment?
)

data class CreatePostAttachment(
    val type: AttachmentType,
    val uri: String
)