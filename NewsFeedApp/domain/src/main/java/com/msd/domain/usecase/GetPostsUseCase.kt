package com.msd.domain.usecase

import androidx.paging.PagingData
import com.msd.domain.model.PostDetail
import com.msd.domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(
    private val repository: PostRepository
) {
    operator fun invoke(): Flow<PagingData<PostDetail>> {
        return repository.getPosts()
    }
}
