// File: feature_budget/src/main/java/com/openparty/feature_budget/presentation/getColorForItem.kt
package com.openparty.app.feature_budget.presentation

import androidx.compose.ui.graphics.Color
import com.openparty.app.feature_budget.domain.model.BudgetItem

fun getColorForItem(item: BudgetItem): Color {
    return when (item.typeOfSpending) {
        "Housing Services" -> Color(0xFF42A5F5)
        "Transportation Services" -> Color(0xFFFFA726)
        "Education Services" -> Color(0xFF66BB6A)
        "Social Care Services" -> Color(0xFFAB47BC)
        "Healthcare Services" -> Color(0xFFEF5350)
        "Miscellaneous" -> Color(0xFF9E9E9E)
        else -> Color(0xFF9E9E9E)
    }
}
