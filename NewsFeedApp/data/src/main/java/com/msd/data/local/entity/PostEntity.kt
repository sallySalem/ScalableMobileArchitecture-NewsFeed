package com.msd.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts")
data class PostEntity(
    @PrimaryKey val id: Long,
    val content: String,
    val authorId: Long,
    val authorName: String,
    val authorAvatarUrl: String?,
    val createdAt: String,
    val liked: Boolean,
    val likedCount: Int,
    val shareCount: Int
)
