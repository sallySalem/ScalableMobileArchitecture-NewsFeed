package com.example.newsfeedapp.ui.screens.posts.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Image
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.newsfeedapp.domain.model.PostDetail

@Composable
fun PostItem(
    post: PostDetail,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        AsyncImage(
            model = post.author.avatarUrl,
            contentDescription = null, //TODO: Add A11y support
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
        )

        Spacer(Modifier.width(12.dp))

        Column(modifier = Modifier.fillMaxWidth()) {

            Text(
                text = post.content,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(Modifier.height(6.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {

                // LIKE
                Icon(
                    imageVector = if (post.liked) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "Like",
                    tint = if (post.liked) Color.Red else Color.Gray,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(Modifier.width(4.dp))
                Text(
                    text = "${post.likedCount}",
                    fontSize = 12.sp,
                    color = Color.Gray
                )

                Spacer(Modifier.width(16.dp))


                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = "Share",
                    tint = Color.Gray,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(Modifier.width(4.dp))
                Text(
                    text = "${post.shareCount}",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
        }


        if (post.attachments.isNotEmpty()) {
            Spacer(Modifier.width(8.dp))
            Icon(
                imageVector = Icons.Default.Image,
                contentDescription = "Attachment",
                tint = Color(0xFFFFA500), // orange color as in your mock
                modifier = Modifier.size(24.dp)
            )
        }
    }
}
