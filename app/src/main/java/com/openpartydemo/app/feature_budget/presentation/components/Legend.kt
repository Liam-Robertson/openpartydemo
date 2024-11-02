package com.openparty.app.feature_budget.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.openparty.app.feature_budget.domain.model.BudgetItem

@Composable
fun Legend(
    budgetItems: List<BudgetItem>,
    colorMapping: Map<BudgetItem, Color>,
    onItemClick: (BudgetItem) -> Unit
) {
    Column {
        budgetItems.forEach { item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onItemClick(item) }
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                val color = colorMapping[item] ?: MaterialTheme.colorScheme.primary
                Box(
                    modifier = Modifier
                        .size(16.dp)
                        .background(color)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "${item.typeOfSpending} - ${item.cost}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}
