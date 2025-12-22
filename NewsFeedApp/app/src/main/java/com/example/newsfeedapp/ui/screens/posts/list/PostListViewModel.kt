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
        loadPostDetails()
    }

    fun loadPostDetails() {
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true)

            when (val res = getPostsUseCase()) {
                is Resource.Success -> {
                    uiState = uiState.copy(
                        isLoading = false,
                        posts = res.data,
                        error = null
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
    val posts: List<PostDetail>? = null,
    val error: String? = null
)
