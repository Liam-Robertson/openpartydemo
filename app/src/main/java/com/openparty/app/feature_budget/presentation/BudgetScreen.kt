// File: feature-budget/src/main/java/com/openparty/feature_budget/presentation/BudgetScreen.kt
package com.openparty.app.feature_budget.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun BudgetScreen(
    viewModel: BudgetViewModel = hiltViewModel()
) {
    val budgetData = viewModel.budgetData.collectAsState().value
    BudgetContent(budgetData)
}
