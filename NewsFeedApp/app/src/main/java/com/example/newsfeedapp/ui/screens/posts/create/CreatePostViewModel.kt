package com.example.newsfeedapp.ui.screens.posts.create

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.AttachmentType
import com.example.domain.model.CreatePost
import com.example.domain.model.CreatePostAttachment
import com.example.domain.usecase.CreatePostUseCase
import com.example.newsfeedapp.ui.PostEvent
import com.example.newsfeedapp.ui.PostEventBus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreatePostViewModel @Inject constructor(
    private val createPostUseCase: CreatePostUseCase,
    private val postEventBus: PostEventBus
) : ViewModel() {

    var uiState by mutableStateOf(CreatePostUiState())
        private set

    fun onContentChange(value: String) {
        uiState = uiState.copy(content = value)
    }

    fun onAttachmentSelected(uri: Uri, attachmentType: AttachmentType) {
        uiState = uiState.copy(
            attachmentUri = uri,
            attachmentType = attachmentType
        )
    }

    fun submitPost() {
        if ( uiState.content.isBlank()) {
            uiState = uiState.copy(errorMessage = "Title and details are required")
            return
        }

        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true, errorMessage = null)

            try {
                createPostUseCase(
                    CreatePost(
                        content = uiState.content,
                        attachment = uiState.attachmentUri?.let {
                            CreatePostAttachment(
                                type = uiState.attachmentType ?: AttachmentType.IMAGE,
                                uri = it.toString()
                            )
                        }
                    )
                )
                postEventBus.post(PostEvent.PostCreated)

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
    val content: String = "",
    val attachmentUri: Uri? = null,
    val attachmentType: AttachmentType? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val success: Boolean = false
)
