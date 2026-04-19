package com.msd.domain.usecase

import com.msd.domain.model.PostDetail
import com.msd.domain.model.Resource
import com.msd.domain.repository.PostRepository
import javax.inject.Inject

class GetPostDetailUseCase @Inject constructor(
    private val repository: PostRepository
) {
    suspend operator fun invoke(postId: Long): Resource<PostDetail> {
        return try {
            val data = repository.getPostDetail(postId)
            Resource.Success(data)
        } catch (t: Throwable) {
            Resource.Error(t)
        }
    }
}
