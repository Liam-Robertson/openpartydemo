// File: feature-budget/src/main/java/com/openparty/feature_budget/domain/model/BudgetResponse.kt
package com.openparty.app.feature_budget.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BudgetResponse(
    @SerialName("areas_of_spending") val areasOfSpending: List<BudgetItem>
)
