package com.example.newsfeedapp.ui.screens.posts.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.InteractionType
import com.example.domain.model.PostDetail
import com.example.domain.model.PostInteraction
import com.example.domain.model.Resource
import com.example.domain.usecase.GetPostDetailUseCase
import com.example.domain.usecase.InteractWithPostUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostDetailsViewModel @Inject constructor(
    private val getPostDetailUseCase: GetPostDetailUseCase,
    private val interactWithPostUseCase: InteractWithPostUseCase,
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

            when (val res = getPostDetailUseCase(postId)) {
                is Resource.Success -> {
                    uiState = uiState.copy(
                        isLoading = false,
                        post = res.data,
                        error = null
                    )
                }

                is Resource.Error -> {
                    uiState = uiState.copy(
                        isLoading = false,
                        error = res.throwable.message
                    )
                }

                is Resource.Loading -> {
                    uiState = uiState.copy(isLoading = true)
                }
            }
        }
    }

    fun toggleLike() {
        val current = uiState.post ?: return
        val isLiking = !current.liked
        val interactionType =
            if (isLiking) InteractionType.LIKED else InteractionType.REMOVED_LIKE

        val updated = current.copy(
            liked = isLiking,
            likedCount = if (isLiking) current.likedCount + 1 else (current.likedCount - 1).coerceAtLeast(
                0
            )
        )

        uiState = uiState.copy(post = updated)

        viewModelScope.launch {
            try {
                interactWithPostUseCase(
                    PostInteraction(
                        postId = current.postId,
                        type = interactionType
                    )
                )
                uiState = uiState.copy(
                    message = UiMessage.Success(
                        if (isLiking) "Post liked" else "Like removed"
                    )
                )
            } catch (e: Exception) {
                uiState = uiState.copy(
                    post = current,
                    message = UiMessage.Error("Failed to update like")
                )
            }
        }
    }

    fun sharePost() {
        val current = uiState.post ?: return

        uiState = uiState.copy(
            post = current.copy(shareCount = current.shareCount + 1)
        )

        viewModelScope.launch {
            try {
                interactWithPostUseCase(
                    PostInteraction(
                        postId = current.postId,
                        type = InteractionType.SHARED
                    )
                )
                uiState = uiState.copy(
                    message = UiMessage.Success("Post shared")
                )
            } catch (e: Exception) {
                uiState = uiState.copy(
                    post = current,
                    message = UiMessage.Error("Failed to share post")
                )
            }
        }
    }

    fun onMessageShown() {
        uiState = uiState.copy(message = null)
    }
}

data class PostDetailsUiState(
    val isLoading: Boolean = false,
    val post: PostDetail? = null,
    val error: String? = null,
    val message: UiMessage? = null
)

sealed class UiMessage {
    data class Success(val text: String) : UiMessage()
    data class Error(val text: String) : UiMessage()
}
