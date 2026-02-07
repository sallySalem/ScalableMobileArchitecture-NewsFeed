package com.example.domain.usecase

import com.example.domain.model.CreatePost
import com.example.domain.repository.PostRepository
import javax.inject.Inject

class CreatePostUseCase @Inject constructor(
    private val repository: PostRepository
) {
    suspend operator fun invoke(post: CreatePost) {
        repository.createPost(post)
    }
}
