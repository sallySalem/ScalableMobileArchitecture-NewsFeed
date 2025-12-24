package com.example.newsfeedapp.ui.screens.posts.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsfeedapp.domain.model.PostDetail
import com.example.newsfeedapp.domain.model.Resource
import com.example.newsfeedapp.domain.usecase.GetPostsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostListViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase
) : ViewModel() {

    var uiState by mutableStateOf(PostsUiState())
        private set

    init {
        loadMorePosts()
    }

    fun loadMorePosts() {
        if (uiState.isLoading || !uiState.pagination.hasMore) return

        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true)

            when (val res = getPostsUseCase(limit = 10, cursor = uiState.pagination.nextCursor)) {
                is Resource.Success -> {
                    val newPosts = res.data?.posts ?: emptyList()
                    uiState = uiState.copy(
                        isLoading = false,
                        posts = uiState.posts + newPosts,
                        error = null,
                        pagination = uiState.pagination.copy(
                            nextCursor = res.data?.nextCursor,
                            hasMore = res.data?.hasMore ?: false
                        )
                    )
                }
                is Resource.Error -> {
                    uiState = uiState.copy(
                        isLoading = false,
                        error = res.message
                    )
                }
                is Resource.Loading -> {
                    uiState = uiState.copy(isLoading = true)
                }
            }
        }
    }
}

data class PostsUiState(
    val isLoading: Boolean = false,
    val posts: List<PostDetail> = emptyList(),
    val error: String? = null,
    val pagination: PaginationUiData = PaginationUiData()
)

data class PaginationUiData(
    val nextCursor: Int? = null,
    val hasMore: Boolean = true
)
