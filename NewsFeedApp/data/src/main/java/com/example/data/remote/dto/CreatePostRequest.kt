package com.example.data.remote.dto

data class CreatePostRequest(
    val content: String,
    val attachment: AttachmentRequest?
)

data class AttachmentRequest(
    val type: String, // IMAGE | VIDEO
    val uri: String   // local uri or uploaded url
)
