package com.openparty.app.di

import com.openparty.app.feature_issues.data.datasource.IssueDataSource
import com.openparty.app.feature_issues.data.repository.IssueRepositoryImpl
import com.openparty.app.feature_issues.domain.repository.IssueRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object IssueModule {

    @Provides
    @Singleton
    fun provideIssueRepository(
        dataSource: IssueDataSource
    ): IssueRepository = IssueRepositoryImpl(dataSource)
}