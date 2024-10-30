// File: app/src/main/java/com/openparty/app/navigation/AppNavHost.kt
package com.openparty.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.openparty.app.feature_budget.presentation.BudgetScreen
import com.openparty.app.feature_issues.presentation.IssuesScreen
import com.openparty.app.feature_issues.presentation.RaiseIssueScreen
import com.openparty.app.feature_issues.presentation.ViewIssueScreen
import com.openparty.app.feature_newsfeed.presentation.NewsfeedScreen

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController, startDestination = Screen.Budget.route) {
        composable(Screen.Budget.route) {
            BudgetScreen()
        }
        composable(Screen.Issues.route) {
            IssuesScreen(navController)
        }
        composable("view_issue") {
            ViewIssueScreen(navController)
        }
        composable("raise_issue") {
            RaiseIssueScreen(navController)
        }
        composable(Screen.Newsfeed.route) {
            NewsfeedScreen()
        }
    }
}
