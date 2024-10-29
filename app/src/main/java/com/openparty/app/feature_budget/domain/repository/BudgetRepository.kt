// File: feature-budget/src/main/java/com/openparty/feature_budget/domain/repository/BudgetRepository.kt
package com.openparty.app.feature_budget.domain.repository

import com.openparty.app.feature_budget.domain.model.BudgetItem

interface BudgetRepository {
    suspend fun getBudgetData(): List<BudgetItem>
}
