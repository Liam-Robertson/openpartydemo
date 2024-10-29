// File: feature-budget/src/main/java/com/openparty/feature_budget/domain/model/BudgetItem.kt
package com.openparty.app.feature_budget.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BudgetItem(
    @SerialName("type_of_spending") val typeOfSpending: String,
    val cost: String,
    val summary: String,
    @SerialName("subtypes_level_1") val subtypesLevel1: List<BudgetItem> = emptyList(),
    @SerialName("subtypes_level_2") val subtypesLevel2: List<BudgetItem> = emptyList()
)
