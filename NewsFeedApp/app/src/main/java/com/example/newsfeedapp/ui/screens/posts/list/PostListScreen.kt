package com.example.newsfeedapp.ui.screens.posts.list


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
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems


@Composable
fun PostListScreen(
    viewModel: PostListViewModel = hiltViewModel(),
    onCreatePostClick: () -> Unit,
    onPostClick: (String) -> Unit
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
                    key = { index ->
                        val post = posts.peek(index)
                        post?.postId ?: "loading-$index"
                    }
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
                    if (posts.loadState.append is LoadState.Loading) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
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
                is LoadState.NotLoading -> Unit
            }
        }
    }
}


//    val state = viewModel.uiState
//
//    when {
//        state.isLoading -> {
//            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//                CircularProgressIndicator()
//            }
//        }
//
//        state.error != null -> {
//            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//                Column(horizontalAlignment = Alignment.CenterHorizontally) {
//                    Text("Error: ${state.error}")
//                    Spacer(Modifier.height(8.dp))
//                    Button(onClick = { viewModel.loadMorePosts() }) {
//                        Text("Retry")
//                    }
//                }
//            }
//        }
//
//        state.posts.isNotEmpty() -> {
//            val samplePosts = state.posts
//            Scaffold(
//                floatingActionButton = {
//                    FloatingActionButton(onClick = onCreatePostClick) {
//                        Icon(Icons.Default.Add, contentDescription = "Create Post")
//                    }
//                }
//            ) { padding ->
//                LazyColumn(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .padding(padding)
//                        .padding(vertical = 8.dp)
//                ) {
//                    items(items = samplePosts) { post ->
//                        PostItem(
//                            post = post,
//                            onClick = { onPostClick(post.postId.toString()) }
//                        )
//                    }
//                }
//            }
//        }
//    }


