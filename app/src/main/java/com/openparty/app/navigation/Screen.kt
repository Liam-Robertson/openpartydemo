// File: navigation/src/main/java/com/openparty/navigation/Screen.kt
package com.openparty.app.navigation

sealed class Screen(val route: String) {
    object Budget : Screen("budget")
    object Proposals : Screen("proposals")
    object Newsfeed : Screen("newsfeed")
}
