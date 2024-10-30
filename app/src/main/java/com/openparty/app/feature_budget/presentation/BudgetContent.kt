// File: feature_budget/src/main/java/com/openparty/feature_budget/presentation/BudgetContent.kt
package com.openparty.app.feature_budget.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.openparty.app.feature_budget.domain.model.BudgetItem
import com.openparty.app.feature_budget.presentation.components.BudgetPieChart
import com.openparty.app.feature_budget.presentation.components.Legend

import androidx.compose.material3.Button

@Composable
fun BudgetContent(budgetItems: List<BudgetItem>) {
    var currentItems by remember(budgetItems) { mutableStateOf(groupSmallBudgetItems(budgetItems)) }
    val navigationStack = remember { mutableStateListOf<List<BudgetItem>>() }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Text(
            text = "West Lothian Budget",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.align(Alignment.Start)
        )

        if (navigationStack.isNotEmpty()) {
            Button(
                onClick = {
                    if (navigationStack.isNotEmpty()) {
                        currentItems = navigationStack.removeLast()
                    }
                },
                modifier = Modifier.padding(vertical = 16.dp)
            ) {
                Text("Back")
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            BudgetPieChart(
                budgetItems = currentItems,
                onItemClick = { selectedItem ->
                    val nextLevelItems = when {
                        selectedItem.subtypesLevel2.isNotEmpty() -> groupSmallBudgetItems(selectedItem.subtypesLevel2)
                        selectedItem.subtypesLevel3.isNotEmpty() -> groupSmallBudgetItems(selectedItem.subtypesLevel3)
                        else -> listOf(selectedItem)
                    }

                    if (nextLevelItems != currentItems) {
                        navigationStack.add(currentItems)
                        currentItems = nextLevelItems
                    }
                }
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Legend(
            budgetItems = currentItems,
            onItemClick = { selectedItem ->
                val nextLevelItems = when {
                    selectedItem.subtypesLevel2.isNotEmpty() -> groupSmallBudgetItems(selectedItem.subtypesLevel2)
                    selectedItem.subtypesLevel3.isNotEmpty() -> groupSmallBudgetItems(selectedItem.subtypesLevel3)
                    else -> listOf(selectedItem)
                }

                if (nextLevelItems != currentItems) {
                    navigationStack.add(currentItems)
                    currentItems = nextLevelItems
                }
            }
        )
    }
}

fun groupSmallBudgetItems(budgetItems: List<BudgetItem>): List<BudgetItem> {
    val totalCost = budgetItems.sumOf { parseCost(it.cost) }
    val threshold = totalCost * 0.05

    val (largeItems, smallItems) = budgetItems.partition { parseCost(it.cost) >= threshold }

    return if (smallItems.isNotEmpty()) {
        val miscellaneousCost = smallItems.sumOf { parseCost(it.cost) }
        largeItems + BudgetItem(
            typeOfSpending = "Miscellaneous",
            cost = formatCost(miscellaneousCost),
            summary = "Grouped small items",
            groupedItems = smallItems
        )
    } else {
        budgetItems
    }
}

fun parseCost(costString: String): Double {
    return costString
        .replace("£", "")
        .replace(",", "")
        .replace(" million", "")
        .trim()
        .toDoubleOrNull() ?: 0.0
}

fun formatCost(cost: Double): String {
    return "£${"%.3f".format(cost)} million"
}
