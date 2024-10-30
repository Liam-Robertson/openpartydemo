// File: feature_issues/src/main/java/com/openparty/feature_issues/domain/usecase/GetIssuesUseCase.kt
package com.openparty.app.feature_issues.domain.usecase

import com.openparty.app.feature_issues.domain.model.Issue
import com.openparty.app.feature_issues.domain.repository.IssueRepository
import javax.inject.Inject

class GetIssuesUseCase @Inject constructor(
    private val repository: IssueRepository
) {
    suspend operator fun invoke(): List<Issue> {
        return repository.getIssues()
    }
}
