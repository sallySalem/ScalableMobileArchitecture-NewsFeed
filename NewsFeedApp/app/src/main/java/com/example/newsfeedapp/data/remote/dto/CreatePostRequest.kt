package com.example.newsfeedapp.data.remote.dto

data class CreatePostRequest(
    val title: String,
    val content: String,
    val attachment: AttachmentRequest?
)

data class AttachmentRequest(
    val type: String, // IMAGE | VIDEO
    val uri: String   // local uri or uploaded url
)
