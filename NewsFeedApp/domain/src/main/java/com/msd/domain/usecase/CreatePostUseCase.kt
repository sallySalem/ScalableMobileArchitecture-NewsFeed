package com.msd.domain.usecase

import com.msd.domain.model.CreatePost
import com.msd.domain.repository.PostRepository
import javax.inject.Inject

class CreatePostUseCase @Inject constructor(
    private val repository: PostRepository
) {
    suspend operator fun invoke(post: CreatePost) {
        repository.createPost(post)
    }
}
