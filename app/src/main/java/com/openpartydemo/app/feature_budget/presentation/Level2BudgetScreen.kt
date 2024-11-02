package com.openparty.app.feature_budget.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import com.openparty.app.feature_budget.presentation.SharedBudgetViewModel
import com.openparty.app.feature_budget.presentation.BudgetContent

@Composable
fun Level2BudgetScreen(
    navController: NavHostController,
    sharedViewModel: SharedBudgetViewModel
) {
    val budgetItem = sharedViewModel.selectedItem.collectAsState().value
    println("Level2BudgetScreen - Observing selected item: ${budgetItem?.typeOfSpending}")

    if (budgetItem != null) {
        BudgetContent(budgetItem.subtypesLevel2) { selectedItem ->
            println("Level2BudgetScreen - Selected item: ${selectedItem.typeOfSpending}")
            if (selectedItem.subtypesLevel3.isNotEmpty()) {
                sharedViewModel.setSelectedItem(selectedItem)
                println("Level2BudgetScreen - Navigating to level3")
                navController.navigate("level3")
            }
        }
    }
}
