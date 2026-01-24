package com.example.domain.usecase

import com.example.domain.model.PostInteraction
import com.example.domain.repository.PostRepository
import javax.inject.Inject

class InteractWithPostUseCase @Inject constructor(
    private val repository: PostRepository
) {
    suspend operator fun invoke(request: PostInteraction) {
        repository.interact(request)
    }
}