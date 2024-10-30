package com.openparty.app.feature_budget.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BudgetItem(
    @SerialName("type_of_spending") val typeOfSpending: String,
    val cost: String,
    val summary: String,
    @SerialName("spending_level_2") val subtypesLevel2: List<BudgetItem> = emptyList(),
    @SerialName("spending_level_3") val subtypesLevel3: List<BudgetItem> = emptyList(),
    val groupedItems: List<BudgetItem>? = null
)
