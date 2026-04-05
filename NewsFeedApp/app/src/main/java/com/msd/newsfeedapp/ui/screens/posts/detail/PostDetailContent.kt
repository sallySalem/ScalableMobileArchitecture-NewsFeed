package com.msd.newsfeedapp.ui.screens.posts.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.msd.domain.model.Attachment
import com.msd.domain.model.AttachmentType
import com.msd.domain.model.AuthorPreview
import com.msd.domain.model.PostDetail
import com.msd.newsfeedapp.ui.util.SimpleDateFormatter
import com.msd.newsfeedapp.ui.util.VideoPlayer

@Composable
fun PostDetailContent(
    post: PostDetail,
    onLikeClick: () -> Unit,
    onShareClick: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier
) {
    Column(
        modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        PostDetailTopBar(onBack, onLikeClick, onShareClick, post.liked)
        Spacer(Modifier.height(4.dp))
        PostStats(post.likedCount, post.shareCount)
        Spacer(Modifier.height(4.dp))
        PostTimestamp(post.createdAt)
        Spacer(Modifier.height(28.dp))
        AuthorInfo(post.author)
        Spacer(Modifier.height(16.dp))
        PostContent(post.content)
        Spacer(Modifier.height(20.dp))
        post.attachments.firstOrNull()?.let { AttachmentContent(it) }
    }
}

@Composable
private fun PostDetailTopBar(
    onBack: () -> Unit,
    onLikeClick: () -> Unit,
    onShareClick: () -> Unit,
    liked: Boolean
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = onBack) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
        }
        Row {
            IconButton(onClick = onLikeClick) {
                Icon(
                    imageVector = if (liked) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "Like",
                    tint = if (liked) Color.Red else Color.Gray
                )
            }
            IconButton(onClick = onShareClick) {
                Icon(Icons.Default.Share, contentDescription = "Share")
            }
        }
    }
}

@Composable
private fun PostStats(likedCount: Int, shareCount: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 12.dp)
    ) {
        Text(
            text = "$likedCount likes",
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Gray
        )
        Spacer(Modifier.width(16.dp))
        Text(
            text = "$shareCount shares",
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Gray
        )
    }
}

@Composable
private fun PostTimestamp(createdAt: String) {
    Text(
        text = SimpleDateFormatter.formatDateTime(createdAt),
        fontSize = 14.sp,
        color = Color.Gray,
        modifier = Modifier.padding(start = 12.dp)
    )
}

@Composable
private fun AuthorInfo(author: AuthorPreview) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        AsyncImage(
            model = author.avatarUrl,
            contentDescription = "Avatar",
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
        )
        Spacer(Modifier.width(12.dp))
        Text(
            text = author.name,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun PostContent(content: String) {
    Text(
        text = content,
        fontSize = 16.sp,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun AttachmentContent(attachment: Attachment) {
    when (attachment.type) {
        AttachmentType.VIDEO -> {
            VideoPlayer(
                url = attachment.contentUrl,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .clip(RoundedCornerShape(12.dp))
            )
        }

        AttachmentType.IMAGE -> {
            AsyncImage(
                model = attachment.contentUrl,
                contentDescription = attachment.caption,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )
        }

        AttachmentType.UNKNOWN -> {}
    }
}
