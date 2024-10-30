package com.openparty.app.feature_newsfeed.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
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
            Image(
                painter = painterResource(id = getImageResource(item.image)),
                contentDescription = item.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = item.title, style = MaterialTheme.typography.titleMedium)
            Text(text = item.subheader, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

// Helper function to get image resource ID from the image path
@Composable
fun getImageResource(imagePath: String): Int {
    val context = LocalContext.current
    return context.resources.getIdentifier(
        imagePath.substringAfterLast("/").substringBeforeLast("."),
        "drawable",
        context.packageName
    )
}
