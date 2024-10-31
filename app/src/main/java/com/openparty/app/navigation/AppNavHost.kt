package com.openparty.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.NavType
import com.openparty.app.feature_budget.presentation.BudgetScreen
import com.openparty.app.feature_budget.presentation.Level2BudgetScreen
import com.openparty.app.feature_budget.presentation.Level3BudgetScreen
import com.openparty.app.feature_budget.domain.model.BudgetItem
import com.openparty.app.feature_issues.presentation.IssuesScreen
import com.openparty.app.feature_issues.presentation.RaiseIssueScreen
import com.openparty.app.feature_issues.presentation.ViewIssueScreen
import com.openparty.app.feature_newsfeed.presentation.NewsfeedItemScreen
import com.openparty.app.feature_newsfeed.presentation.NewsfeedScreen
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController, startDestination = Screen.Budget.route) {
        composable(Screen.Budget.route) {
            BudgetScreen(navController)
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
            NewsfeedScreen(navController)
        }
        composable("newsfeed_item") {
            NewsfeedItemScreen(navController)
        }
        composable(
            "level2/{budgetItem}",
            arguments = listOf(navArgument("budgetItem") { type = NavType.StringType })
        ) { backStackEntry ->
            val budgetItemJson = backStackEntry.arguments?.getString("budgetItem")
            val budgetItem = parseBudgetItem(budgetItemJson)
            if (budgetItem != null) {
                Level2BudgetScreen(navController, budgetItem)
            }
        }
        composable(
            "level3/{budgetItem}",
            arguments = listOf(navArgument("budgetItem") { type = NavType.StringType })
        ) { backStackEntry ->
            val budgetItemJson = backStackEntry.arguments?.getString("budgetItem")
            val budgetItem = parseBudgetItem(budgetItemJson)
            if (budgetItem != null) {
                Level3BudgetScreen(navController, budgetItem)
            }
        }
    }
}

fun NavHostController.navigateToLevel2(budgetItem: BudgetItem) {
    val budgetItemJson = Json.encodeToString(budgetItem)
    this.navigate("level2/$budgetItemJson")
}

fun NavHostController.navigateToLevel3(budgetItem: BudgetItem) {
    val budgetItemJson = Json.encodeToString(budgetItem)
    this.navigate("level3/$budgetItemJson")
}

fun parseBudgetItem(json: String?): BudgetItem? {
    return json?.let { Json.decodeFromString(it) }
}
