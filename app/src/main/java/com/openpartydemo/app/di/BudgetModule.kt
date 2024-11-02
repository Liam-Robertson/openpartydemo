// File: feature-budget/src/main/java/com/openparty/feature_budget/di/BudgetModule.kt
package com.openparty.app.di

import com.openparty.app.feature_budget.data.datasource.BudgetDataSource
import com.openparty.app.feature_budget.data.repository.BudgetRepositoryImpl
import com.openparty.app.feature_budget.domain.repository.BudgetRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BudgetModule {

    @Provides
    @Singleton
    fun provideBudgetRepository(
        dataSource: BudgetDataSource
    ): BudgetRepository = BudgetRepositoryImpl(dataSource)
}
