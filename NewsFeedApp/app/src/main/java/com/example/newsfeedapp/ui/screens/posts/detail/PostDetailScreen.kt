package com.example.newsfeedapp.ui.screens.posts.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostDetailScreen( viewModel: PostDetailsViewModel = hiltViewModel(),
                       onBack: () -> Unit = {}
) {
    val state = viewModel.uiState

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
            PostDetailContent(
                post = state.post,
                onLikeClick = viewModel::toggleLike,
                onShareClick = { /* share logic */ },
                onBack = onBack
            )
        }
    }
}
