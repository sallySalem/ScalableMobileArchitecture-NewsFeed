package com.example.newsfeedapp.domain.usecase

import com.example.newsfeedapp.domain.model.PostDetail
import com.example.newsfeedapp.domain.model.Resource
import com.example.newsfeedapp.domain.repository.PostRepository
import com.example.newsfeedapp.di.DispatcherProvider
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(
    private val repository: PostRepository,
    private val dispatchers: DispatcherProvider
) {
    suspend operator fun invoke(): Resource<List<PostDetail>> {
        return withContext(dispatchers.io) {
            try {
                val data = repository.getPosts()
                Resource.Success(data)
            } catch (t: Throwable) {
                Resource.Error(t, t.message)
            }
        }
    }
}
