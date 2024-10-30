// File: feature_budget/src/main/java/com/openparty/feature_budget/presentation/components/BudgetPieChart.kt
package com.openparty.app.feature_budget.presentation.components

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import me.bytebeats.views.charts.pie.PieChart
import me.bytebeats.views.charts.pie.PieChartData
import me.bytebeats.views.charts.pie.render.SimpleSliceDrawer
import com.openparty.app.feature_budget.domain.model.BudgetItem
import com.openparty.app.feature_budget.presentation.getColorForItem
import com.openparty.app.feature_budget.presentation.parseCost

@Composable
fun BudgetPieChart(
    budgetItems: List<BudgetItem>,
    onItemClick: (BudgetItem) -> Unit
) {
    val pieSlices = budgetItems.mapNotNull { item ->
        val value = parseCost(item.cost).toFloat()

        PieChartData.Slice(
            value = value,
            color = getColorForItem(item)
        )
    }

    PieChart(
        pieChartData = PieChartData(pieSlices),
        modifier = Modifier.size(200.dp),
        sliceDrawer = SimpleSliceDrawer(sliceThickness = 50f)
    )
}
