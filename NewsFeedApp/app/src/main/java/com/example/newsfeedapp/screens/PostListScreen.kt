package com.example.newsfeedapp.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PostListScreen(
    onCreatePostClick: () -> Unit,
    onPostClick: (String) -> Unit
) {
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
            items(10) { index ->
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable { onPostClick(index.toString()) }
                ) {
                    Text("Post Title $index", style = MaterialTheme.typography.titleMedium)
                    Text("Short details about post $index")
                }
            }
        }
    }
}
