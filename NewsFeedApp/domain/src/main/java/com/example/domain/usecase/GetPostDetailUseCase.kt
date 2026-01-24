package com.example.domain.usecase

import com.example.domain.model.PostDetail
import com.example.domain.model.Resource
import com.example.domain.repository.PostRepository
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
