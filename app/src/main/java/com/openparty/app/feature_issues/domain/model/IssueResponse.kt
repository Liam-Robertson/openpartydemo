package com.openparty.app.feature_issues.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class IssueResponse(
    val issues: List<Issue>
)