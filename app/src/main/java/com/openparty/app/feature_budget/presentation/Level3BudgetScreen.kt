package com.openparty.app.feature_budget.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.openparty.app.feature_budget.domain.model.BudgetItem

@Composable
fun Level3BudgetScreen(
    navController: NavController,
    budgetItem: BudgetItem
) {
    BudgetContent(budgetItem.subtypesLevel3) { /* No further navigation */ }
}
