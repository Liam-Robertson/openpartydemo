// File: feature_budget/src/main/java/com/openparty/feature_budget/presentation/getColorForItem.kt
package com.openparty.app.feature_budget.presentation

import androidx.compose.ui.graphics.Color
import com.openparty.app.feature_budget.domain.model.BudgetItem
import com.openparty.app.ui.theme.BudgetColors
import com.openparty.app.ui.theme.DefaultColor

private val assignedColors = mutableMapOf<String, Color>()
private var colorIndex = 0

fun getColorForItem(item: BudgetItem): Color {
    assignedColors[item.typeOfSpending]?.let { return it }

    val color = BudgetColors.getOrElse(colorIndex) { DefaultColor }
    assignedColors[item.typeOfSpending] = color

    colorIndex = (colorIndex + 1) % BudgetColors.size
    return color
}
