package com.openparty.app.navigation

sealed class Screen(val route: String) {
    object Budget : Screen("budget")
    object Issues : Screen("issues")
    object Newsfeed : Screen("newsfeed")
}
