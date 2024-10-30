package com.openparty.app.feature_newsfeed.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import com.openparty.app.feature_newsfeed.domain.model.NewsfeedItem
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun NewsfeedItemScreen(
    navController: NavController
) {
    val newsfeedItem = navController.previousBackStackEntry?.savedStateHandle?.get<NewsfeedItem>("newsfeedItem")
    newsfeedItem?.let {
        NewsfeedItemContent(it)
    } ?: run {
        // Handle error or navigate back
    }
}

@Composable
fun NewsfeedItemContent(item: NewsfeedItem) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = item.title,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.fillMaxWidth(),
            maxLines = 2
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = item.subheader,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(id = getImageResource(item.image)),
            contentDescription = item.title,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = item.content,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
