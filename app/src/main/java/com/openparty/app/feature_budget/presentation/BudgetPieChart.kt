// File: feature-budget/src/main/java/com/openparty/feature_budget/presentation/components/BudgetPieChart.kt
package com.openparty.app.feature_budget.presentation

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.openparty.app.feature_budget.domain.model.BudgetItem
import io.github.devssrsouza.compose.charts.piechart.PieChart
import io.github.devssrsouza.compose.charts.piechart.PieChartData

@Composable
fun BudgetPieChart(
    budgetItems: List<BudgetItem>,
    onItemClick: (BudgetItem) -> Unit
) {
    val chartData = budgetItems.map {
        PieChartData.Slice(
            value = it.cost.removePrefix("£").removeSuffix(" million").toFloat(),
            color = getColorForItem(it),
            label = it.typeOfSpending
        )
    }

    PieChart(
        pieChartData = PieChartData(chartData),
        modifier = Modifier.size(200.dp),
        onSliceClicked = { index ->
            onItemClick(budgetItems[index])
        }
    )
}

fun getColorForItem(item: BudgetItem): Color {
    // Implement a method to consistently get colors for items
    // For example, map item types to specific colors
    return Color.Gray // Placeholder
}
