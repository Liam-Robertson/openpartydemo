package com.openparty.app.feature_budget.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.openparty.app.feature_budget.presentation.BudgetContent
import com.openparty.app.feature_budget.presentation.SharedBudgetViewModel
import com.openparty.app.feature_budget.presentation.BudgetViewModel

@Composable
fun BudgetScreen(
    navController: NavHostController,
    sharedViewModel: SharedBudgetViewModel,
    viewModel: BudgetViewModel = hiltViewModel()
) {
    val budgetData = viewModel.budgetData.collectAsState().value
    if (budgetData.isEmpty()) {
        LoadingScreen()
    } else {
        BudgetContent(budgetData) { selectedItem ->
            println("BudgetScreen - Selected item: ${selectedItem.typeOfSpending}")
            if (selectedItem.subtypesLevel2.isNotEmpty()) {
                sharedViewModel.setSelectedItem(selectedItem)
                println("BudgetScreen - Navigating to level2")
                navController.navigate("level2")
            }
        }
    }
}
