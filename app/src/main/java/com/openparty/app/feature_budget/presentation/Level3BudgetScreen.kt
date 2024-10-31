package com.openparty.app.feature_budget.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import com.openparty.app.feature_budget.presentation.SharedBudgetViewModel
import com.openparty.app.feature_budget.presentation.BudgetContent

@Composable
fun Level3BudgetScreen(
    navController: NavHostController,
    sharedViewModel: SharedBudgetViewModel
) {
    val budgetItem = sharedViewModel.selectedItem.collectAsState().value
    println("Level3BudgetScreen - Observing selected item: ${budgetItem?.typeOfSpending}")

    if (budgetItem != null) {
        BudgetContent(budgetItem.subtypesLevel3) { /* Handle item click if needed */ }
    }
}
