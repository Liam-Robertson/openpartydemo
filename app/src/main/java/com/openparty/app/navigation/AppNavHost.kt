package com.openparty.app.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.openparty.app.feature_budget.presentation.BudgetScreen
import com.openparty.app.feature_budget.presentation.Level2BudgetScreen
import com.openparty.app.feature_budget.presentation.Level3BudgetScreen
import com.openparty.app.feature_budget.presentation.SharedBudgetViewModel
import com.openparty.app.feature_issues.presentation.IssuesScreen
import com.openparty.app.feature_issues.presentation.RaiseIssueScreen
import com.openparty.app.feature_issues.presentation.ViewIssueScreen
import com.openparty.app.feature_newsfeed.presentation.NewsfeedItemScreen
import com.openparty.app.feature_newsfeed.presentation.NewsfeedScreen

@Composable
fun AppNavHost(navController: NavHostController) {
    val sharedViewModel: SharedBudgetViewModel = hiltViewModel()
    NavHost(navController, startDestination = Screen.Budget.route) {
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
            NewsfeedScreen(navController)
        }
        composable("newsfeed_item") {
            NewsfeedItemScreen(navController)
        }
        composable(Screen.Budget.route) {
            BudgetScreen(navController, sharedViewModel)
        }
        composable(Screen.Issues.route) {
            IssuesScreen(navController)
        }
        composable(Screen.Newsfeed.route) {
            NewsfeedScreen(navController)
        }
        composable("level2") {
            Level2BudgetScreen(navController, sharedViewModel)
        }
        composable("level3") {
            Level3BudgetScreen(navController, sharedViewModel)
        }
    }
}
