package com.example.newsfeedapp.domain.usecase

import com.example.newsfeedapp.data.remote.dto.PostInteractionRequest
import com.example.newsfeedapp.domain.repository.PostRepository
import javax.inject.Inject

class InteractWithPostUseCase @Inject constructor(
    private val repository: PostRepository
) {
    suspend operator fun invoke(request: PostInteractionRequest) {
        repository.interact(request)
    }
}