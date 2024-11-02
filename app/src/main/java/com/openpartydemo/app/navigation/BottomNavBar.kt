package com.openparty.app.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Article
import androidx.compose.material.icons.filled.BugReport
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.PieChart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavBar(navController: NavHostController) {
    val items = listOf(
        Screen.Budget,
        Screen.Issues,
        Screen.Newsfeed
    )
    NavigationBar {
        val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
        items.forEach { screen ->
            val icon = when (screen) {
                is Screen.Budget -> Icons.Default.PieChart
                is Screen.Issues -> Icons.Default.Lightbulb
                is Screen.Newsfeed -> Icons.Default.Article
            }
            NavigationBarItem(
                icon = { Icon(imageVector = icon, contentDescription = screen.route) },
                label = { Text(screen.route.capitalize()) },
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}
