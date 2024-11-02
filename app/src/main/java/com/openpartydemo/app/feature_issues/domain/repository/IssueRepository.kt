// File: feature_issues/src/main/java/com/openparty/feature_issues/domain/repository/IssueRepository.kt
package com.openparty.app.feature_issues.domain.repository

import com.openparty.app.feature_issues.domain.model.Issue

interface IssueRepository {
    suspend fun getIssues(): List<Issue>
}
