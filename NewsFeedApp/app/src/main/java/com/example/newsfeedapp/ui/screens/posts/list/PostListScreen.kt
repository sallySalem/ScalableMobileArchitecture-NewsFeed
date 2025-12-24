package com.example.newsfeedapp.ui.screens.posts.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.ui.Alignment
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel

@Composable
fun PostListScreen(
    viewModel: PostListViewModel = hiltViewModel(),
    onCreatePostClick: () -> Unit,
    onPostClick: (String) -> Unit
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
                    Button(onClick = { viewModel.loadMorePosts() }) {
                        Text("Retry")
                    }
                }
            }
        }

        state.posts.isNotEmpty() -> {
            val samplePosts = state.posts
            Scaffold(
                floatingActionButton = {
                    FloatingActionButton(onClick = onCreatePostClick) {
                        Icon(Icons.Default.Add, contentDescription = "Create Post")
                    }
                }
            ) { padding ->
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .padding(vertical = 8.dp)
                ) {
                    items(items = samplePosts) { post ->
                        PostItem(
                            post = post,
                            onClick = { onPostClick(post.postId.toString()) }
                        )
                    }
                }
            }
        }
    }

}
