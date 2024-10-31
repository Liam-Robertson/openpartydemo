package com.openparty.app.feature_newsfeed.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Composable
fun NewsfeedScreen(
    navController: NavController,
    viewModel: NewsfeedViewModel = hiltViewModel()
) {
    val newsfeedItems = viewModel.newsfeed.collectAsState().value
    NewsfeedContent(
        newsfeedItems = newsfeedItems,
        onNewsfeedItemClick = { item ->
            val jsonItem = Json.encodeToString(item) // Convert item to JSON string
            navController.currentBackStackEntry?.savedStateHandle?.set("newsfeedItemJson", jsonItem)
            navController.navigate("newsfeed_item")
        }
    )
}
