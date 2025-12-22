package com.example.newsfeedapp.ui.screens.posts.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import com.example.newsfeedapp.domain.model.PostDetail
import com.example.newsfeedapp.domain.model.AuthorPreview
import com.example.newsfeedapp.domain.model.Attachment
import com.example.newsfeedapp.domain.model.AttachmentType

@Composable
fun PostListScreen(
    onCreatePostClick: () -> Unit,
    onPostClick: (String) -> Unit
) {
    // Simple static list of 2 posts for local testing of PostItem
    val samplePosts = listOf(
        PostDetail(
            postId = 1L,
            content = "Hello — this is the first sample post.",
            author = AuthorPreview(id = 1L, name = "Alice", avatarUrl = null),
            createdAt = "",
            liked = false,
            likedCount = 3,
            shareCount = 1,
            attachments = listOf()
        ),
        PostDetail(
            postId = 2L,
            content = "Second sample post with an attachment.",
            author = AuthorPreview(id = 2L, name = "Bob", avatarUrl = null),
            createdAt = "",
            liked = true,
            likedCount = 10,
            shareCount = 4,
            attachments = listOf(
                Attachment(
                    id = 1L,
                    type = AttachmentType.IMAGE,
                    contentUrl = "https://via.placeholder.com/150",
                    previewImageUrl = null,
                    caption = "Sample image"
                )
            )
        )
    )

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
