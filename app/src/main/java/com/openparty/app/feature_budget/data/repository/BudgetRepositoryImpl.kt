// File: feature-budget/src/main/java/com/openparty/feature_budget/data/repository/BudgetRepositoryImpl.kt
package com.openparty.app.feature_budget.data.repository

import com.openparty.app.feature_budget.data.datasource.BudgetDataSource
import com.openparty.app.feature_budget.domain.model.BudgetItem
import com.openparty.app.feature_budget.domain.repository.BudgetRepository
import javax.inject.Inject

class BudgetRepositoryImpl @Inject constructor(
    private val dataSource: BudgetDataSource
) : BudgetRepository {
    override suspend fun getBudgetData(): List<BudgetItem> {
        return dataSource.fetchBudgetData()
    }
}
