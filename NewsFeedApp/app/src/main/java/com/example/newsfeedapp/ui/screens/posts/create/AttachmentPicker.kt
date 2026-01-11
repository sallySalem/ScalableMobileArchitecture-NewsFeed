package com.example.newsfeedapp.ui.screens.posts.create

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachFile
import androidx.compose.material3.Text


@Composable
fun AttachmentPicker(
    attachmentUri: Uri?,
    onAttachmentSelected: (Uri, String) -> Unit
) {
    val imagePicker =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            uri?.let { onAttachmentSelected(it, "IMAGE") }
        }

    Row(verticalAlignment = Alignment.CenterVertically) {

        FloatingActionButton(
            onClick = { imagePicker.launch("image/*") },
            modifier = Modifier.size(48.dp)
        ) {
            Icon(Icons.Default.AttachFile, contentDescription = null)
        }

        Spacer(Modifier.width(12.dp))

        attachmentUri?.let {
            Text(
                text = it.lastPathSegment ?: "Attachment selected",
                maxLines = 1
            )
        }
    }
}
