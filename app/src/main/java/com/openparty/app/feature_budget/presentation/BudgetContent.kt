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

@Composable
fun BudgetContent(budgetItems: List<BudgetItem>) {
    var currentItems by remember { mutableStateOf(budgetItems) }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Text(
            text = "West Lothian Budget",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(32.dp))

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
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
        }

        Spacer(modifier = Modifier.height(32.dp))

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
