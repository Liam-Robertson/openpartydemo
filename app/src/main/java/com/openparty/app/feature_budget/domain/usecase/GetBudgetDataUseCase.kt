// File: feature-budget/src/main/java/com/openparty/feature_budget/domain/usecase/GetBudgetDataUseCase.kt
package com.openparty.app.feature_budget.domain.usecase

import com.openparty.app.feature_budget.domain.model.BudgetItem
import com.openparty.app.feature_budget.domain.repository.BudgetRepository
import javax.inject.Inject

class GetBudgetDataUseCase @Inject constructor(
    private val repository: BudgetRepository
) {
    suspend operator fun invoke(): List<BudgetItem> = repository.getBudgetData()
}
