package com.openparty.app.feature_budget.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.openparty.app.feature_budget.domain.model.BudgetItem
import com.openparty.app.navigation.navigateToLevel3

@Composable
fun Level2BudgetScreen(
    navController: NavHostController,
    budgetItem: BudgetItem
) {
    BudgetContent(budgetItem.subtypesLevel2) { selectedItem ->
        if (selectedItem.subtypesLevel3.isNotEmpty()) {
            navController.navigateToLevel3(selectedItem)
        }
    }
}
