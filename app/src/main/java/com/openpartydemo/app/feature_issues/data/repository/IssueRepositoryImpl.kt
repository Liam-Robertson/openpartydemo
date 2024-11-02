// File: feature_issues/src/main/java/com/openparty/feature_issues/data/repository/IssueRepositoryImpl.kt
package com.openparty.app.feature_issues.data.repository

import com.openparty.app.feature_issues.data.datasource.IssueDataSource
import com.openparty.app.feature_issues.domain.model.Issue
import com.openparty.app.feature_issues.domain.repository.IssueRepository
import javax.inject.Inject

class IssueRepositoryImpl @Inject constructor(
    private val dataSource: IssueDataSource
) : IssueRepository {
    override suspend fun getIssues(): List<Issue> {
        return dataSource.fetchIssues()
    }
}
