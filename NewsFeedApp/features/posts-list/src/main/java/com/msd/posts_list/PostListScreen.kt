package com.msd.posts_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems

@Composable
fun PostListScreen(
    onCreatePostClick: () -> Unit,
    onPostClick: (String) -> Unit,
    viewModel: PostListViewModel = hiltViewModel()
) {
    val posts = viewModel.posts.collectAsLazyPagingItems()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onCreatePostClick) {
                Icon(Icons.Default.Add, contentDescription = "Create Post")
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(
                    count = posts.itemCount,
                    key = { index -> posts[index]?.postId ?: "loading-$index" }
                ) { index ->
                    val post = posts[index]
                    post?.let {
                        PostItem(
                            post = it,
                            onClick = { onPostClick(it.postId.toString()) }
                        )
                    }
                }

                item {
                    when (val appendState = posts.loadState.append) {
                        is LoadState.Loading -> {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator()
                            }
                        }

                        is LoadState.Error -> {
                            Column(
                                modifier = Modifier.align(Alignment.Center),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "Error: ${appendState.error.localizedMessage}",
                                    color = Color.Red
                                )
                                Spacer(Modifier.height(8.dp))
                                Button(onClick = { posts.retry() }) {
                                    Text("Retry")
                                }
                            }
                        }

                        is LoadState.NotLoading -> Unit
                    }
                }
            }
            when (val refreshState = posts.loadState.refresh) {
                is LoadState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }

                is LoadState.Error -> {
                    Column(
                        modifier = Modifier.align(Alignment.Center),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Error: ${refreshState.error.localizedMessage}",
                            color = Color.Red
                        )
                        Spacer(Modifier.height(8.dp))
                        Button(onClick = { posts.retry() }) {
                            Text("Retry")
                        }
                    }
                }

                is LoadState.NotLoading -> {
                    if (posts.itemCount == 0) {
                        Text(
                            text = "No posts available.",
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }
        }
    }
}
