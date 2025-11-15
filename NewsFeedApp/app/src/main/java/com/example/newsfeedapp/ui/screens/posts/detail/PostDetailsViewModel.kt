package com.example.newsfeedapp.ui.screens.posts.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsfeedapp.domain.model.PostDetail
import com.example.newsfeedapp.domain.repository.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostDetailsViewModel @Inject constructor(
    private val repository: PostRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val postId: Long = savedStateHandle["postId"] ?: 0L

    var uiState by mutableStateOf(PostDetailsUiState())
        private set

    init {
        loadPostDetails()
    }

    fun loadPostDetails() {
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true)

            try {
                val post = repository.getPostDetail(postId)
                uiState = uiState.copy(
                    isLoading = false,
                    post = post,
                    error = null
                )
            } catch (e: Exception) {
                uiState = uiState.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }

    fun toggleLike() {
        val current = uiState.post ?: return
        val updated = current.copy(
            liked = !current.liked,
            likedCount = if (!current.liked) current.likedCount + 1 else current.likedCount - 1
        )
        uiState = uiState.copy(post = updated)
    }
}

data class PostDetailsUiState(
    val isLoading: Boolean = false,
    val post: PostDetail? = null,
    val error: String? = null
)
