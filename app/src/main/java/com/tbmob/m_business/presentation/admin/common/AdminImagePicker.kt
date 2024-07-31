package com.tbmob.m_business.presentation.admin.common

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@Composable
fun AdminImagePicker(
    modifier: Modifier = Modifier,
    image: ByteArray?,
    onImageSelected: (ByteArray?) -> Unit
) {
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        selectedImageUri = uri
    }
    val context = LocalContext.current


    Box(
        modifier = modifier
            .clickable { launcher.launch("image/*") },
        contentAlignment = Alignment.Center
    ) {
        if (selectedImageUri == null) {
            Log.d("TAG", "AdminImagePicker: $image")

            Image(
                imageVector = Icons.Outlined.Image,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
            )
        } else {
            selectedImageUri?.let { uri ->
                val painter = rememberAsyncImagePainter(uri)
                val inputStream = context.contentResolver.openInputStream(uri)
                onImageSelected(inputStream?.readBytes())

                Image(
                    painter = painter,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                )

                inputStream?.close()
            }
        }
    }
}