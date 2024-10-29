// File: feature-budget/src/main/java/com/openparty/feature_budget/data/datasource/BudgetDataSource.kt
package com.openparty.app.feature_budget.data.datasource

import android.content.Context
import com.openparty.app.feature_budget.domain.model.BudgetItem
import com.openparty.app.feature_budget.domain.model.BudgetResponse
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

class BudgetDataSource @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val jsonFormat = Json { ignoreUnknownKeys = true }

    suspend fun fetchBudgetData(): List<BudgetItem> {
        val jsonString = withContext(Dispatchers.IO) {
            context.assets.open("financeDataV1.json").bufferedReader().use { it.readText() }
        }
        val response = jsonFormat.decodeFromString<BudgetResponse>(jsonString)
        return response.areasOfSpending
    }
}
