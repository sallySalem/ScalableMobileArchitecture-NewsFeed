package com.msd.posts_list

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.msd.domain.model.AttachmentType
import com.msd.domain.model.PostDetail

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
            contentDescription = null,
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
        )

        Spacer(Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {

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
                    tint = if (post.liked) androidx.compose.ui.graphics.Color.Red else androidx.compose.ui.graphics.Color.Gray,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(Modifier.width(4.dp))
                Text(
                    text = "${post.likedCount}",
                    fontSize = 12.sp,
                    color = androidx.compose.ui.graphics.Color.Gray
                )

                Spacer(Modifier.width(16.dp))

                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = "Share",
                    tint = androidx.compose.ui.graphics.Color.Gray,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(Modifier.width(4.dp))
                Text(
                    text = "${post.shareCount}",
                    fontSize = 12.sp,
                    color = androidx.compose.ui.graphics.Color.Gray
                )
            }
        }

        if (post.attachments.isNotEmpty()) {
            val first = post.attachments.first()
            val icon = when (first.type) {
                AttachmentType.VIDEO -> Icons.Default.Videocam
                AttachmentType.IMAGE -> Icons.Default.Image
                AttachmentType.UNKNOWN -> null
            }

            if (icon != null) {
                Spacer(Modifier.width(8.dp))
                Icon(
                    imageVector = icon,
                    contentDescription = when (first.type) {
                        AttachmentType.IMAGE -> "Image"
                        AttachmentType.VIDEO -> "Video"
                        else -> null
                    },
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}
