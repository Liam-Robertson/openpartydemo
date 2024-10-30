// File: feature_budget/src/main/java/com/openparty/feature_budget/presentation/BudgetScreen.kt
package com.openparty.app.feature_budget.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun BudgetScreen(
    viewModel: BudgetViewModel = hiltViewModel()
) {
    val isLoading = viewModel.isLoading.collectAsState().value
    val budgetData = viewModel.budgetData.collectAsState().value
    if (isLoading) {
        LoadingScreen()
    } else {
        BudgetContent(budgetData)
    }
}
