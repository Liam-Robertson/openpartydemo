// File: app/src/main/java/com/openparty/app/navigation/AppNavHost.kt
package com.openparty.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.openparty.app.feature_budget.presentation.BudgetScreen
import com.openparty.app.feature_proposals.presentation.ProposalsScreen
import com.openparty.app.feature_newsfeed.presentation.NewsfeedScreen

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController, startDestination = Screen.Budget.route) {
        composable(Screen.Budget.route) {
            BudgetScreen()
        }
        composable(Screen.Proposals.route) {
            ProposalsScreen()
        }
        composable(Screen.Newsfeed.route) {
            NewsfeedScreen()
        }
    }
}
