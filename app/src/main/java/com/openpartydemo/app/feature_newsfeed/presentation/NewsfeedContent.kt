package com.openparty.app.feature_newsfeed.presentation

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.openparty.app.R
import com.openparty.app.feature_newsfeed.domain.model.NewsfeedItem

@Composable
fun NewsfeedContent(
    newsfeedItems: List<NewsfeedItem>,
    onNewsfeedItemClick: (NewsfeedItem) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Newsfeed",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(16.dp)
        )
        LazyColumn {
            items(newsfeedItems.size) { index ->
                val item = newsfeedItems[index]
                NewsfeedCard(item, onNewsfeedItemClick)
            }
        }
    }
}

@Composable
fun NewsfeedCard(item: NewsfeedItem, onNewsfeedItemClick: (NewsfeedItem) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onNewsfeedItemClick(item) }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            val imageResource = getImageResource(item.image)
            if (imageResource != 0) {
                Image(
                    painter = painterResource(id = imageResource),
                    contentDescription = item.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                )
            } else {
                Icon(
                    imageVector = Icons.Default.BrokenImage,
                    contentDescription = "Default image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
            Text(text = item.title, style = MaterialTheme.typography.titleMedium)
            Text(text = item.subheader, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
fun getImageResource(imagePath: String): Int {
    val context = LocalContext.current
    val resourceId = context.resources.getIdentifier(
        imagePath,
        "drawable",
        context.packageName
    )

    return if (resourceId != 0) {
        resourceId
    } else {
        Log.e("NewsfeedImage", "Resource not found for imagePath: $imagePath. Using default image.")
        R.drawable.default_image
    }
}
