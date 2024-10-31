package com.openparty.app.feature_budget.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.openparty.app.navigation.navigateToLevel2

@Composable
fun BudgetScreen(
    navController: NavHostController,
    viewModel: BudgetViewModel = hiltViewModel()
) {
    val isLoading = viewModel.isLoading.collectAsState().value
    val budgetData = viewModel.budgetData.collectAsState().value
    if (isLoading) {
        LoadingScreen()
    } else {
        BudgetContent(budgetData) { selectedItem ->
            if (selectedItem.subtypesLevel2.isNotEmpty()) {
                navController.navigateToLevel2(selectedItem)
            }
        }
    }
}
