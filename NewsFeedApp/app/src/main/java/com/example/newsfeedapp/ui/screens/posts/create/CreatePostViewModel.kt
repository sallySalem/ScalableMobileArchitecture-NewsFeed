package com.example.newsfeedapp.ui.screens.posts.create

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.example.newsfeedapp.domain.usecase.CreatePostUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.example.newsfeedapp.data.remote.dto.AttachmentRequest
import com.example.newsfeedapp.data.remote.dto.CreatePostRequest
import kotlinx.coroutines.launch

@HiltViewModel
class CreatePostViewModel @Inject constructor(
    private val createPostUseCase: CreatePostUseCase
) : ViewModel() {

    var uiState by mutableStateOf(CreatePostUiState())
        private set

    fun onTitleChange(value: String) {
        uiState = uiState.copy(title = value)
    }

    fun onContentChange(value: String) {
        uiState = uiState.copy(content = value)
    }

    fun onAttachmentSelected(uri: Uri, type: String) {
        uiState = uiState.copy(
            attachmentUri = uri,
            attachmentType = type
        )
    }

    fun submitPost() {
        if (uiState.title.isBlank() || uiState.content.isBlank()) {
            uiState = uiState.copy(errorMessage = "Title and details are required")
            return
        }

        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true, errorMessage = null)

            try {
                createPostUseCase(
                    CreatePostRequest(
                        title = uiState.title,
                        content = uiState.content,
                        attachment = uiState.attachmentUri?.let {
                            AttachmentRequest(
                                type = uiState.attachmentType ?: "IMAGE",
                                uri = it.toString()
                            )
                        }
                    )
                )

                uiState = uiState.copy(
                    isLoading = false,
                    success = true
                )
            } catch (e: Exception) {
                uiState = uiState.copy(
                    isLoading = false,
                    errorMessage = "Failed to create post"
                )
            }
        }
    }
}

data class CreatePostUiState(
    val title: String = "",
    val content: String = "",
    val attachmentUri: Uri? = null,
    val attachmentType: String? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val success: Boolean = false
)

