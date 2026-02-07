package com.example.newsfeedapp.ui.screens.posts.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostDetailScreen( viewModel: PostDetailsViewModel = hiltViewModel(),
                       onBack: () -> Unit = {}
) {
    val state = viewModel.uiState
    val snackBarHostState = remember { SnackbarHostState() }

    when {
        state.isLoading -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        state.error != null -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Error: ${state.error}")
                    Spacer(Modifier.height(8.dp))
                    Button(onClick = { viewModel.loadPostDetails() }) {
                        Text("Retry")
                    }
                }
            }
        }

        state.post != null -> {
            Scaffold(
                snackbarHost = { SnackbarHost(snackBarHostState) }
            ) { padding ->
                PostDetailContent(
                    modifier = Modifier.padding(padding),
                    post = state.post,
                    onLikeClick = viewModel::toggleLike,
                    onShareClick = viewModel::sharePost,
                    onBack = onBack
                )
            }
        }
    }

    LaunchedEffect(state.message) {
        state.message?.let { msg ->
            val text = when (msg) {
                is UiMessage.Success -> msg.text
                is UiMessage.Error -> msg.text
            }

            snackBarHostState.showSnackbar(text)
            viewModel.onMessageShown()
        }
    }
}
