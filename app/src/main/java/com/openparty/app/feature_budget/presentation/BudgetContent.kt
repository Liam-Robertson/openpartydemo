// File: feature-budget/src/main/java/com/openparty/feature_budget/presentation/BudgetContent.kt
package com.openparty.app.feature_budget.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.*
import com.openparty.app.feature_budget.domain.model.BudgetItem
import com.openparty.app.feature_budget.presentation.components.Legend

@Composable
fun BudgetContent(budgetItems: List<BudgetItem>) {
    var currentItems by remember { mutableStateOf(budgetItems) }

    Column {
        BudgetPieChart(
            budgetItems = currentItems,
            onItemClick = { selectedItem ->
                currentItems = when {
                    selectedItem.subtypesLevel1.isNotEmpty() -> selectedItem.subtypesLevel1
                    selectedItem.subtypesLevel2.isNotEmpty() -> selectedItem.subtypesLevel2
                    else -> listOf(selectedItem)
                }
            }
        )
        Legend(
            budgetItems = currentItems,
            onItemClick = { selectedItem ->
                currentItems = when {
                    selectedItem.subtypesLevel1.isNotEmpty() -> selectedItem.subtypesLevel1
                    selectedItem.subtypesLevel2.isNotEmpty() -> selectedItem.subtypesLevel2
                    else -> listOf(selectedItem)
                }
            }
        )
    }
}
