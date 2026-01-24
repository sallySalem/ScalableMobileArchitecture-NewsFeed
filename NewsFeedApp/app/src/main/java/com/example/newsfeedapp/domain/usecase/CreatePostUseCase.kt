package com.example.newsfeedapp.domain.usecase

import com.example.newsfeedapp.data.remote.dto.CreatePostRequest
import com.example.newsfeedapp.domain.repository.PostRepository
import javax.inject.Inject

class CreatePostUseCase @Inject constructor(
    private val repository: PostRepository
) {
    suspend operator fun invoke(request: CreatePostRequest) {
        repository.createPost(request)
    }
}