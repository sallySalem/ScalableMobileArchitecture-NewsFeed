package com.msd.domain.usecase

import com.msd.domain.model.PostInteraction
import com.msd.domain.repository.PostRepository
import javax.inject.Inject

class InteractWithPostUseCase @Inject constructor(
    private val repository: PostRepository
) {
    suspend operator fun invoke(request: PostInteraction) {
        repository.interact(request)
    }
}
