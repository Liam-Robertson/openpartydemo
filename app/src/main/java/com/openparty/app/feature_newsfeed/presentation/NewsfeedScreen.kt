package com.openparty.app.feature_newsfeed.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun NewsfeedScreen(
    navController: NavController,
    viewModel: NewsfeedViewModel = hiltViewModel()
) {
    val newsfeedItems = viewModel.newsfeed.collectAsState().value
    NewsfeedContent(
        newsfeedItems = newsfeedItems,
        onNewsfeedItemClick = { item ->
            navController.currentBackStackEntry?.savedStateHandle?.set("newsfeedItem", item)
            navController.navigate("newsfeed_item")
        }
    )
}
