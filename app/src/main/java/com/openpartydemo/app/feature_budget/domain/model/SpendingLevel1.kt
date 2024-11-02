package com.openparty.app.feature_budget.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SpendingLevel1(
    @SerialName("spending_level_1") val level1: BudgetItem
)