package com.example.newsfeedapp.domain.usecase

import com.example.newsfeedapp.di.DispatcherProvider
import com.example.newsfeedapp.domain.model.PaginatedPosts
import com.example.newsfeedapp.domain.model.Resource
import com.example.newsfeedapp.domain.repository.PostRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(
    private val repository: PostRepository,
    private val dispatchers: DispatcherProvider
) {
    suspend operator fun invoke(limit: Int, cursor: Int?): Resource<PaginatedPosts> {
        return withContext(dispatchers.io) {
            try {
                val data = repository.getPosts(limit = limit, cursor = cursor)
                Resource.Success(data)
            } catch (t: Throwable) {
                Resource.Error(t, t.message)
            }
        }
    }
}
